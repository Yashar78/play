package controllers

import javax.inject._

import models._
import play.api.mvc._
import play.modules.reactivemongo._
import reactivemongo.play.json.collection._

import scala.concurrent.{ExecutionContext, Future}


/**
  * Simple controller that directly stores and retrieves [models.City] instances into a MongoDB Collection
  * Input is first converted into a city and then the city is converted to JsObject to be stored in MongoDB
  */
@Singleton
class ResidentController @Inject()(val reactiveMongoApi: ReactiveMongoApi)(implicit exec: ExecutionContext) extends Controller with MongoController with ReactiveMongoComponents {

  def residentFuture: Future[JSONCollection] = database.map(_.collection[JSONCollection]("location"))

  def create(name: String, age: Int, role:Option[String]) = Action.async {
    for {
      locations <- residentFuture
      lastError <- locations.insert(Resident(name, age, role))
    } yield
      Ok("Mongo LastError: %s".format(lastError))
  }


}


