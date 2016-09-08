
import play.api.libs.json._
import models._
object test3 {

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
}
""")
  val latPath = JsPath \ "location" \ "lat"

  // Recursive path
  val namesPath = JsPath \\ "name"

  // Indexed path
  val firstResidentPath = (JsPath \ "residents")(0)

  val longPath = __ \ "location" \ "long"

  val nameReads: Reads[String] = (JsPath \ "name").read[String]

  val nameResult: JsResult[String] = json.validate[String](nameReads)

  nameResult match {
    case s: JsSuccess[String] => println("Name: " + s.get)
    case e: JsError => println("Errors: " + JsError.toJson(e).toString())
  }


  /*
  val place = Place(
    "Watership Down",
    Location(51.235685, -1.309197),
    Seq(
      Resident("Fiver", 4, None),
      Resident("Bigwig", 6, Some("Owsla"))
    )
  )

  val jp = Json.toJson(place)
  jp \ "name" get
  val lat = (jp \ "location" \ "lat").get

  val names = jp \\ "name"

  val name = (jp \ "name").as[String]

  val nameOption = (jp \ "name").asOpt[String]

  val jpv : JsResult[Place] = jp.validate[Place]

  jpv match {
    case s: JsSuccess[Place] => println("Name========: " + s.get.name)
    case e: JsError => println("Errors: " + JsError.toJson(e).toString())
  }
*/

}