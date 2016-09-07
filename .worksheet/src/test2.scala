object test2 {
case class Location(lat: Double, long: Double)
case class Resident(name: String, age: Int, role: Option[String])
case class Place(name: String, location: Location, residents: Seq[Resident])

import play.api.libs.json._;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(393); 

implicit val locationWrites = new Writes[Location] {
  def writes(location: Location) = Json.obj(
    "lat" -> location.lat,
    "long" -> location.long
  )
};System.out.println("""locationWrites  : play.api.libs.json.Writes[test2.Location]{def writes(location: test2.Location): play.api.libs.json.JsObject} = """ + $show(locationWrites ));$skip(190); 


implicit val residentWrites = new Writes[Resident] {
  def writes(resident: Resident) = Json.obj(
    "name" -> resident.name,
    "age" -> resident.age,
    "role" -> resident.role
  )
};System.out.println("""residentWrites  : play.api.libs.json.Writes[test2.Resident]{def writes(resident: test2.Resident): play.api.libs.json.JsObject} = """ + $show(residentWrites ));$skip(185); 

implicit val placeWrites = new Writes[Place] {
  def writes(place: Place) = Json.obj(
    "name" -> place.name,
    "location" -> place.location,
    "residents" -> place.residents)
};System.out.println("""placeWrites  : play.api.libs.json.Writes[test2.Place]{def writes(place: test2.Place): play.api.libs.json.JsObject} = """ + $show(placeWrites ));$skip(160); 

val place = Place(
  "Watership Down",
  Location(51.235685, -1.309197),
  Seq(
    Resident("Fiver", 4, None),
    Resident("Bigwig", 6, Some("Owsla"))
  )
);System.out.println("""place  : test2.Place = """ + $show(place ));$skip(6); val res$0 = 
place;System.out.println("""res0: test2.Place = """ + $show(res$0));$skip(30); 
val json = Json.toJson(place);System.out.println("""json  : play.api.libs.json.JsValue = """ + $show(json ));$skip(44); 


val lat = (json \ "location" \ "lat").get;System.out.println("""lat  : play.api.libs.json.JsValue = """ + $show(lat ));$skip(4); val res$1 = 
lat;System.out.println("""res1: play.api.libs.json.JsValue = """ + $show(res$1));$skip(58); 
// returns JsNumber(51.235685)
val names = json \\ "name";System.out.println("""names  : Seq[play.api.libs.json.JsValue] = """ + $show(names ));$skip(37); 
val bigwig = (json \ "residents")(1);System.out.println("""bigwig  : play.api.libs.json.JsLookupResult = """ + $show(bigwig ));$skip(50); 
val minifiedString: String = Json.stringify(json);System.out.println("""minifiedString  : String = """ + $show(minifiedString ));$skip(52); 
val readableString: String = Json.prettyPrint(json);System.out.println("""readableString  : String = """ + $show(readableString ));$skip(39); 

val name = (json \ "name").as[String];System.out.println("""name  : String = """ + $show(name ));$skip(49); 

val nameOption = (json \ "name").asOpt[String];System.out.println("""nameOption  : Option[String] = """ + $show(nameOption ));$skip(76); 
// Some("Watership Down")

val bogusOption = (json \ "bogus").asOpt[String]
// None
import play.api.libs.json._
import play.api.libs.functional.syntax._;System.out.println("""bogusOption  : Option[String] = """ + $show(bogusOption ));$skip(215); 

implicit val locationReads: Reads[Location] = (
  (JsPath \ "lat").read[Double] and
  (JsPath \ "long").read[Double]
)(Location.apply _);System.out.println("""locationReads  : play.api.libs.json.Reads[test2.Location] = """ + $show(locationReads ));$skip(180); 

implicit val residentReads: Reads[Resident] = (
  (JsPath \ "name").read[String] and
  (JsPath \ "age").read[Int] and
  (JsPath \ "role").readNullable[String]
)(Resident.apply _);System.out.println("""residentReads  : play.api.libs.json.Reads[test2.Resident] = """ + $show(residentReads ));$skip(185); 

implicit val placeReads: Reads[Place] = (
  (JsPath \ "name").read[String] and
  (JsPath \ "location").read[Location] and
  (JsPath \ "residents").read[Seq[Resident]]
)(Place.apply _);System.out.println("""placeReads  : play.api.libs.json.Reads[test2.Place] = """ + $show(placeReads ));$skip(58); 


val placeResult: JsResult[Place] = json.validate[Place];System.out.println("""placeResult  : play.api.libs.json.JsResult[test2.Place] = """ + $show(placeResult ));$skip(184); 
// JsSuccess(Place(...),)

placeResult match {
  case s: JsSuccess[Place] => println("Name: " + s.toString())
  case e: JsError => println("Errors: " + JsError.toJson(e).toString())
};$skip(85); 

val residentResult: JsResult[Resident] = (json \ "residents")(1).validate[Resident];System.out.println("""residentResult  : play.api.libs.json.JsResult[test2.Resident] = """ + $show(residentResult ))}
// JsSuccess(Resident(Bigwig,6,Some(Owsla)),)

}
