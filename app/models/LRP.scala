package models

/**
  * Created by uy57vx on 7-9-2016.
  */


case class Location(lat: Double, long: Double)
case class Resident(name: String, age: Int, role: Option[String])
case class Place(name: String, location: Location) //, residents: Seq[Resident])

import play.api.libs.json._
import play.api.libs.functional.syntax._

object Location {
/*
  implicit val locationWrites: Writes[Location] = (
    (JsPath \ "lat").write[Double] and
      (JsPath \ "long").write[Double]
    ) (unlift(Location.unapply))

  implicit val locationReads: Reads[Location] = (
    (JsPath \ "lat").read[Double] and
      (JsPath \ "long").read[Double]
    )(Location.apply _)
*/
  implicit val fomatter = Json.format[Location]
}

object Resident {
  /*
  implicit val residentWrites: Writes[Resident] = (
    (JsPath \ "name").write[String] and
      (JsPath \ "age").write[Int] and
      (JsPath \ "role").writeNullable[String]
    ) (unlift(Resident.unapply))

  implicit val residentReads: Reads[Resident] = (
    (JsPath \ "name").read[String] and
      (JsPath \ "age").read[Int] and
      (JsPath \ "role").readNullable[String]
    )(Resident.apply _)
*/
  implicit val fomatter = Json.format[Resident]
}

object Place {
  /*
  implicit val placeWrites: Writes[Place] = (
    (JsPath \ "name").write[String] and
      (JsPath \ "location").write[Location] and
      (JsPath \ "residents").write[Seq[Resident]]
    ) (unlift(Place.unapply))

  implicit val placeReads: Reads[Place] = (
    (JsPath \ "name").read[String] and
      (JsPath \ "location").read[Location] and
      (JsPath \ "residents").read[Seq[Resident]]
    )(Place.apply _)
*/
  implicit val fomatter = Json.format[Place]
}


