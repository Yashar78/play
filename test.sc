
import play.api._
import play.api.mvc._
import play.api.libs.json._
 

object Application extends Controller {
 
  def getName = Action {
  	Ok("Jim")
  }                                               //> getName: => play.api.mvc.Action[play.api.mvc.AnyContent]
 
 case class Person(name: String, country: String, id: Int)
 
import play.api.libs.json._

val json2: JsValue = Json.parse("""
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
""")                                              //> json2  : play.api.libs.json.JsValue = {"name":"Watership Down","location":{"
                                                  //| lat":51.235685,"long":-1.309197},"residents":[{"name":"Fiver","age":4,"role"
                                                  //| :null},{"name":"Bigwig","age":6,"role":"Owsla"}]}
import play.api.libs.json.{JsNull,Json,JsString,JsValue}

val json: JsValue = Json.obj(
  "name" -> "Watership Down",
  "location" -> Json.obj("lat" -> 51.235685, "long" -> -1.309197),
  "residents" -> Json.arr(
    Json.obj(
      "name" -> "Fiver",
      "age" -> 4,
      "role" -> JsNull
    ),
    Json.obj(
      "name" -> "Bigwig",
      "age" -> 6,
      "role" -> "Owsla"
    )
  )
)                                                 //> json  : play.api.libs.json.JsValue = {"name":"Watership Down","location":{"l
                                                  //| at":51.235685,"long":-1.309197},"residents":[{"name":"Fiver","age":4,"role":
                                                  //| null},{"name":"Bigwig","age":6,"role":"Owsla"}]}
import play.api.libs.json._

// basic types
val jsonString = Json.toJson("Fiver")             //> jsonString  : play.api.libs.json.JsValue = "Fiver"
val jsonNumber = Json.toJson(4)                   //> jsonNumber  : play.api.libs.json.JsValue = 4
val jsonBoolean = Json.toJson(false)              //> jsonBoolean  : play.api.libs.json.JsValue = false

// collections of basic types
val jsonArrayOfInts = Json.toJson(Seq(1, 2, 3, 4))//> jsonArrayOfInts  : play.api.libs.json.JsValue = [1,2,3,4]
val jsonArrayOfStrings = Json.toJson(List("Fiver", "Bigwig"))
                                                  //> jsonArrayOfStrings  : play.api.libs.json.JsValue = ["Fiver","Bigwig"]

jsonString                                        //> res0: play.api.libs.json.JsValue = "Fiver"


 
}

 