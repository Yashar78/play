package controllers

import javax.inject._

//import models.City
import play.api.Logger
import play.api.libs.json._
import play.api.mvc._
import play.modules.reactivemongo._
import reactivemongo.play.json.collection._
import utils.Errors
import models._
import scala.concurrent.{ExecutionContext, Future}
import play.modules.reactivemongo.json._

/**
  * Simple controller that directly stores and retrieves [models.City] instances into a MongoDB Collection
  * Input is first converted into a city and then the city is converted to JsObject to be stored in MongoDB
  */
@Singleton
class LocationController @Inject()(val reactiveMongoApi: ReactiveMongoApi)(implicit exec: ExecutionContext) extends Controller with MongoController with ReactiveMongoComponents {

  def locationFuture: Future[JSONCollection] = database.map(_.collection[JSONCollection]("location"))

  def create(lat: Double, long: Double) = Action.async {
    for {
      locations <- locationFuture
      lastError <- locations.insert(Location(lat, long))
    } yield
      Ok("Mongo LastError: %s".format(lastError))
  }

  def createFromJson = Action.async(parse.json) { request =>
    Json.fromJson[Location](request.body) match {
      case JsSuccess(location, _) =>
        for {
          locations <- locationFuture
          lastError <- locations.insert(location)
        } yield {
          Logger.debug(s"Successfully inserted with LastError: $lastError")
          Created("Created 1 location")
        }
      case JsError(errors) =>
        Future.successful(BadRequest("Could not build a location from the json provided. " + Errors.show(errors)))
    }
  }


}


