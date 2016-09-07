
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



}