package controllers

import javax.inject._

import models._
import play.api.Logger
import play.api.libs.json._
import play.api.mvc._
import play.modules.reactivemongo._
import reactivemongo.api.ReadPreference
import reactivemongo.play.json.collection._
import utils.Errors
import play.modules.reactivemongo.json._
import scala.concurrent.{ExecutionContext, Future}


/**
  * Simple controller that directly stores and retrieves [models.City] instances into a MongoDB Collection
  * Input is first converted into a city and then the city is converted to JsObject to be stored in MongoDB
  */
@Singleton
class PlaceController @Inject()(val reactiveMongoApi: ReactiveMongoApi)(implicit exec: ExecutionContext) extends Controller with MongoController with ReactiveMongoComponents {

  def placeFuture: Future[JSONCollection] = database.map(_.collection[JSONCollection]("place"))

  def create(name: String, location: Location, residents: Seq[Resident] ) = Action.async {
    for {
      places <- placeFuture
      lastError <- places.insert(Place(name, location, residents))
    } yield
      Ok("Mongo LastError: %s".format(lastError))
  }

  def createFromJson = Action.async(parse.json) { request =>
    Json.fromJson[Place](request.body) match {
      case JsSuccess(place, _) =>
        for {
          places <- placeFuture
          lastError <- places.insert(place)
        } yield {
          Logger.debug(s"Successfully inserted with LastError: $lastError")
          Created("Created 1 place")
        }
      case JsError(errors) =>
        Future.successful(BadRequest("Could not build a place from the json provided. " + Errors.show(errors)))
    }
  }

  def findPlace(name: String) = Action.async {
    val futurePlacesList: Future[List[Place]] = placeFuture.flatMap{
        _.find(Json.obj("name" -> name)).
          cursor[Place](ReadPreference.primary).
          collect[List]()
    }
    futurePlacesList.map { places =>
      Ok(Json.prettyPrint(Json.toJson(places))) }
  }


}


