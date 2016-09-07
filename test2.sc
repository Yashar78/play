object test2 {
case class Location(lat: Double, long: Double)
case class Resident(name: String, age: Int, role: Option[String])
case class Place(name: String, location: Location, residents: Seq[Resident])

import play.api.libs.json._


  val json: JsValue = Json.parse("""
{
  "name" : "Watership Down",
  "location" : {
    "lat" : 51.235685,
    "long" : -1.309197
  },
  "residents" : [ {
    "name" : "Fiver",
    "age" : 4,
    "role" : null
  }, {
    "name" : "Bigwig",
    "age" : 6,
    "role" : "Owsla"
  } ]
} """)







  implicit val locationWrites = new Writes[Location] {
  def writes(location: Location) = Json.obj(
    "lat" -> location.lat,
    "long" -> location.long
  )
}                                                 //> locationWrites  : play.api.libs.json.Writes[test2.Location]{def writes(locat
                                                  //| ion: test2.Location): play.api.libs.json.JsObject} = test2$$anonfun$main$1$$
                                                  //| anon$1@5ecddf8f


implicit val residentWrites = new Writes[Resident] {
  def writes(resident: Resident) = Json.obj(
    "name" -> resident.name,
    "age" -> resident.age,
    "role" -> resident.role
  )
}                                                 //> residentWrites  : play.api.libs.json.Writes[test2.Resident]{def writes(resid
                                                  //| ent: test2.Resident): play.api.libs.json.JsObject} = test2$$anonfun$main$1$$
                                                  //| anon$2@29ee9faa

implicit val placeWrites = new Writes[Place] {
  def writes(place: Place) = Json.obj(
    "name" -> place.name,
    "location" -> place.location,
    "residents" -> place.residents)
}                                                 //> placeWrites  : play.api.libs.json.Writes[test2.Place]{def writes(place: test
                                                  //| 2.Place): play.api.libs.json.JsObject} = test2$$anonfun$main$1$$anon$3@c0382
                                                  //| 03

val place = Place(
  "Watership Down",
  Location(51.235685, -1.309197),
  Seq(
    Resident("Fiver", 4, None),
    Resident("Bigwig", 6, Some("Owsla"))
  )
)                                                 //> place  : test2.Place = Place(Watership Down,Location(51.235685,-1.309197),Li
                                                  //| st(Resident(Fiver,4,None), Resident(Bigwig,6,Some(Owsla))))
place                                             //> res0: test2.Place = Place(Watership Down,Location(51.235685,-1.309197),List(
                                                  //| Resident(Fiver,4,None), Resident(Bigwig,6,Some(Owsla))))
val json = Json.toJson(place)                     //> json  : play.api.libs.json.JsValue = {"name":"Watership Down","location":{"l
                                                  //| at":51.235685,"long":-1.309197},"residents":[{"name":"Fiver","age":4,"role":
                                                  //| null},{"name":"Bigwig","age":6,"role":"Owsla"}]}


val lat = (json \ "location" \ "lat").get         //> lat  : play.api.libs.json.JsValue = 51.235685
lat                                               //> res1: play.api.libs.json.JsValue = 51.235685
// returns JsNumber(51.235685)
val names = json \\ "name"                        //> names  : Seq[play.api.libs.json.JsValue] = List("Watership Down", "Fiver", 
                                                  //| "Bigwig")
val bigwig = (json \ "residents")(1)              //> bigwig  : play.api.libs.json.JsLookupResult = JsDefined({"name":"Bigwig","a
                                                  //| ge":6,"role":"Owsla"})
val minifiedString: String = Json.stringify(json) //> minifiedString  : String = {"name":"Watership Down","location":{"lat":51.23
                                                  //| 5685,"long":-1.309197},"residents":[{"name":"Fiver","age":4,"role":null},{"
                                                  //| name":"Bigwig","age":6,"role":"Owsla"}]}
val readableString: String = Json.prettyPrint(json)
                                                  //> readableString  : String = {
                                                  //|   "name" : "Watership Down",
                                                  //|   "location" : {
                                                  //|     "lat" : 51.235685,
                                                  //|     "long" : -1.309197
                                                  //|   },
                                                  //|   "residents" : [ {
                                                  //|     "name" : "Fiver",
                                                  //|     "age" : 4,
                                                  //|     "role" : null
                                                  //|   }, {
                                                  //|     "name" : "Bigwig",
                                                  //|     "age" : 6,
                                                  //|     "role" : "Owsla"
                                                  //|   } ]
                                                  //| }

val name = (json \ "name").as[String]             //> name  : String = Watership Down

val nameOption = (json \ "name").asOpt[String]    //> nameOption  : Option[String] = Some(Watership Down)
// Some("Watership Down")

val bogusOption = (json \ "bogus").asOpt[String]  //> bogusOption  : Option[String] = None
// None
import play.api.libs.json._
import play.api.libs.functional.syntax._

implicit val locationReads: Reads[Location] = (
  (JsPath \ "lat").read[Double] and
  (JsPath \ "long").read[Double]
)(Location.apply _)                               //> locationReads  : play.api.libs.json.Reads[test2.Location] = play.api.libs.j
                                                  //| son.Reads$$anon$8@2a4fb17b

implicit val residentReads: Reads[Resident] = (
  (JsPath \ "name").read[String] and
  (JsPath \ "age").read[Int] and
  (JsPath \ "role").readNullable[String]
)(Resident.apply _)                               //> residentReads  : play.api.libs.json.Reads[test2.Resident] = play.api.libs.j
                                                  //| son.Reads$$anon$8@a1153bc

implicit val placeReads: Reads[Place] = (
  (JsPath \ "name").read[String] and
  (JsPath \ "location").read[Location] and
  (JsPath \ "residents").read[Seq[Resident]]
)(Place.apply _)                                  //> placeReads  : play.api.libs.json.Reads[test2.Place] = play.api.libs.json.Re
                                                  //| ads$$anon$8@55141def


val placeResult: JsResult[Place] = json.validate[Place]
                                                  //> placeResult  : play.api.libs.json.JsResult[test2.Place] = JsSuccess(Place(W
                                                  //| atership Down,Location(51.235685,-1.309197),List(Resident(Fiver,4,None), Re
                                                  //| sident(Bigwig,6,Some(Owsla)))),)
// JsSuccess(Place(...),)

placeResult match {
  case s: JsSuccess[Place] => println("Name: " + s.toString())
  case e: JsError => println("Errors: " + JsError.toJson(e).toString())
}                                                 //> Name: JsSuccess(Place(Watership Down,Location(51.235685,-1.309197),List(Res
                                                  //| ident(Fiver,4,None), Resident(Bigwig,6,Some(Owsla)))),)

val residentResult: JsResult[Resident] = (json \ "residents")(1).validate[Resident]
                                                  //> residentResult  : play.api.libs.json.JsResult[test2.Resident] = JsSuccess(R
                                                  //| esident(Bigwig,6,Some(Owsla)),)
// JsSuccess(Resident(Bigwig,6,Some(Owsla)),)

}