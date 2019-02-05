package calculus

import scala.collection.mutable._
import scala.collection.mutable.ListBuffer


/**
  * Represents a calculator. Contains methods for shunting yard algorithm and to execute the calculation
  */
class CalculatorModel {

  // Map to hold operators and their precedence
  val operators = Map[String, Int]("+" -> 2, "-" -> 2, "/" -> 3, "*" -> 3)

  /**
    * Entrypoint method which takes a string representation of calculation, calls other methods and returns an option
    * @param calculation string calculation in the form of "1 + 2 + 3"
    * @return None if not valid calculation, Some if valid
    */
  def parseAndCalculate(calculation: String): Option[Double] = {

    // Use shunting yard algorithm to transform string from infix notation to a list of strings in RPN
    val numbersAndOperators = shuntingYard(calculation)

    // Returns option rounded up
    Some(Math.round(numbersAndOperators.foldLeft(List[Double]())(evaluateCalculation).head * 100.00) / 100.00)
  }

  /**
    * Method that evaluates a mathematical expression
    * @param stack list of doubles
    * @param a string number
    * @return a double result of the evaluation
    */
  private[calculus] def evaluateCalculation(stack: List[Double], a: String): List[Double] = stack match {
    case List() => a.toDouble :: stack
    case List(_) => a.toDouble :: stack
    case x::y::ys => a match {
      case "*" => x * y :: ys
      case "+" => x + y :: ys
      case "-" => y - x :: ys
      case "/" => y / x :: ys
      case s: String => s.toDouble :: stack
    }
  }

  /**
    * Private method to transform a calculation from infix notation to reverse polish notation
    * @param infixNotation a list of strings that represent a calculation in infix notation
    * @return a list of strings that represent a calculation in reverse polish notation
    */
  private[calculus] def shuntingYard(infixNotationString : String) : ListBuffer[String] ={
    // Remove spaces
    val infixNotationStringWithoutSpace = infixNotationString.replaceAll("\\s", "")
    // Tokenise string
    val infixNotationList= infixNotationStringWithoutSpace.split("(?<=[-+*/()])|(?=[-+*/()])").toList

    val prefixList = ListBuffer[String]()
    val operatorStack = ListBuffer[String]()

    infixNotationList.foreach(elem =>  {
      // Numbers
      if (elem.matches("\\d+\\.?\\d?")) {
        prefixList.append(elem)
      }
      // Operators
      if (operators.contains(elem)) {
        while (operatorStack.nonEmpty && operators.contains(operatorStack.head) && operators(elem) <= operators(operatorStack.head)) {
          prefixList.append(operatorStack.remove(0))
        }
        operatorStack.prepend(elem)
      }
      // Opening bracket
      if ("(".equals(elem)) {
        operatorStack.prepend(elem)
      }
      // Closing bracket
      if (")".equals(elem)) {
        while (!"(".equals(operatorStack.head)) {
          prefixList.append(operatorStack.remove(0))

        }
        operatorStack.remove(0)
      }
    })
    // Add all the rest from stack to return list
    operatorStack.foreach(elem => prefixList.append(elem))
    prefixList
  }

}
