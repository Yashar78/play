package models

/**
  * Created by uy57vx on 7-9-2016.
  */

import play.api.libs.json._
import play.api.libs.json.Reads._
import play.api.libs.functional.syntax._
import play.modules.reactivemongo.json._

case class Location(lat: Double, long: Double)
case class Resident(name: String, age: Int, role: Option[String])
case class Place(name: String, location: Location, residents: Seq[Resident])

object JsonUtil {
  def oFormat[T](format:Format[T]) : OFormat[T] = {
    val oFormat: OFormat[T] = new OFormat[T](){
      override def writes(o: T): JsObject = format.writes(o).as[JsObject]
      override def reads(json: JsValue): JsResult[T] = format.reads(json)
    }
    oFormat
  }
}


object Location {

  implicit val locationWrites: Writes[Location] = (
    (JsPath \ "lat").write[Double] and
      (JsPath \ "long").write[Double]
    ) (unlift(Location.unapply))

  implicit val locationReads: Reads[Location] = (
    (JsPath \ "lat").read[Double](min(-90.0) keepAnd max(90.0)) and
      (JsPath \ "long").read[Double](min(-180.0) keepAnd max(180.0))
    )(Location.apply _)

 //implicit val fomatter = Json.format[Location]
  implicit val locationFormat: Format[Location] = Format(locationReads, locationWrites)
  implicit val oLocationFormat: OFormat[Location] = JsonUtil.oFormat(locationFormat)

}

object Resident {

  implicit val residentWrites: Writes[Resident] = (
    (JsPath \ "name").write[String] and
      (JsPath \ "age").write[Int] and
      (JsPath \ "role").writeNullable[String]
    ) (unlift(Resident.unapply))

  implicit val residentReads: Reads[Resident] = (
    (JsPath \ "name").read[String](minLength[String](2)) and
      (JsPath \ "age").read[Int](min(0) keepAnd max(150)) and
      (JsPath \ "role").readNullable[String]
    )(Resident.apply _)
  implicit val residentFormat: Format[Resident] = Format(residentReads, residentWrites)
  implicit val oResidentFormat: OFormat[Resident] = JsonUtil.oFormat(residentFormat)

}

object Place {
  implicit val placeWrites: Writes[Place] = (
    (JsPath \ "name").write[String] and
      (JsPath \ "location").write[Location] and
      (JsPath \ "residents").write[Seq[Resident]]
    ) (unlift(Place.unapply))

  implicit val placeReads: Reads[Place] = (
    (JsPath \ "name").read[String](minLength[String](2)) and
      (JsPath \ "location").read[Location] and
      (JsPath \ "residents").read[Seq[Resident]]
    )(Place.apply _)

  implicit val placeFormat: Format[Place] = Format(placeReads, placeWrites)
  implicit val oPlaceFormat: OFormat[Place] = JsonUtil.oFormat(placeFormat)
}

object user {
  case class User(name: String, friends: Seq[User])

  implicit lazy val userReads: Reads[User] = (
     (__ \ "name").read[String] and
     (__ \ "friends").lazyRead(Reads.seq[User](userReads))
    )(User)

  implicit lazy val userWrites: Writes[User] = (
    (__ \ "name").write[String] and
      (__ \ "friends").lazyWrite(Writes.seq[User](userWrites))
    )(unlift(User.unapply))

  implicit val userFormat: Format[User] = Format(userReads, userWrites)
}

