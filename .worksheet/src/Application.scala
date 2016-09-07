
import play.api._
import play.api.mvc._
import play.api.libs.json._
 

object Application extends Controller {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(155); 
 
  def getName = Action {
  	Ok("Jim")
  }
 
 case class Person(name: String, country: String, id: Int)
 
import play.api.libs.json._;System.out.println("""getName: => play.api.mvc.Action[play.api.mvc.AnyContent]""");$skip(381); 

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
""")
import play.api.libs.json.{JsNull,Json,JsString,JsValue};System.out.println("""json2  : play.api.libs.json.JsValue = """ + $show(json2 ));$skip(393); 

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
)
import play.api.libs.json._;System.out.println("""json  : play.api.libs.json.JsValue = """ + $show(json ));$skip(82); 

// basic types
val jsonString = Json.toJson("Fiver");System.out.println("""jsonString  : play.api.libs.json.JsValue = """ + $show(jsonString ));$skip(32); 
val jsonNumber = Json.toJson(4);System.out.println("""jsonNumber  : play.api.libs.json.JsValue = """ + $show(jsonNumber ));$skip(37); 
val jsonBoolean = Json.toJson(false);System.out.println("""jsonBoolean  : play.api.libs.json.JsValue = """ + $show(jsonBoolean ));$skip(82); 

// collections of basic types
val jsonArrayOfInts = Json.toJson(Seq(1, 2, 3, 4));System.out.println("""jsonArrayOfInts  : play.api.libs.json.JsValue = """ + $show(jsonArrayOfInts ));$skip(62); 
val jsonArrayOfStrings = Json.toJson(List("Fiver", "Bigwig"));System.out.println("""jsonArrayOfStrings  : play.api.libs.json.JsValue = """ + $show(jsonArrayOfStrings ));$skip(12); val res$0 = 

jsonString;System.out.println("""res0: play.api.libs.json.JsValue = """ + $show(res$0))}


 
}

 