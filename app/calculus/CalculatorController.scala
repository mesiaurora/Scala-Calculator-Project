package calculus

import javax.inject.Inject
import play.api.libs.json.{JsValue, Json}
import play.api.mvc._

/**
  * Controller for calculus requests
  */
class CalculatorController @Inject() extends InjectedController {


  /**
    * Function for GET request
    * @param query mathematical expression
    * @return JSON object containing result or an error
    */
  def calculus(query: String): Action[AnyContent] = Action {
    println("PRINTING QUERY " + query)
    val calculator = new CalculatorModel
    val returnValue = calculator.parseAndCalculate(query)

    val returnString : JsValue = Json.obj(
      "error" -> "false",
      "result" -> returnValue.get)
    val errorString : JsValue = Json.obj(
      "error" -> "true",
      "message" -> "Something went wrong in the server side"
    )

    returnValue match {
      case Some(double : Double) => Ok(returnString)
      case None => InternalServerError(errorString)
    }
  }

}
