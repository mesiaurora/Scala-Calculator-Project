package calculus

import org.scalatest.{FlatSpec, Matchers}

import scala.collection.mutable.ListBuffer

class CalculatorModelSpec extends FlatSpec with Matchers {

  "A Calculator" should "evaluate correctly" in {
    val calculatorModel = new CalculatorModel
    // Test simple calculations
    calculatorModel.parseAndCalculate("1 + 2").get should be (3.0)
    // Test brackets
    calculatorModel.parseAndCalculate("8 - ( 2 + 4 )").get should be (2.0)
    // Test multiply
    calculatorModel.parseAndCalculate("6 * ( 2 + 2 )").get should be (24.0)
    calculatorModel.parseAndCalculate("6 * 2 + 2").get should be (14.0)
    // Test numbers with more than one digit
    calculatorModel.parseAndCalculate("16 + 2").get should be (18.0)
    // Test no calculation
    calculatorModel.parseAndCalculate("1").get should be (1.0)
    // Test negative numbers
    calculatorModel.parseAndCalculate("1 - 2").get should be (-1.0)
    // Test decimals
    calculatorModel.parseAndCalculate("6.2 - 4.1").get should be (2.1)

  }

  "Shunting Yard algorithm" should "correctly transform to reverse polish notation" in {
    val calculatorModel = new CalculatorModel
    // Test simple expression
    calculatorModel.shuntingYard("1 + 2") should be (ListBuffer("1", "2", "+"))
    calculatorModel.shuntingYard("1 + 2 + 3") should be (ListBuffer("1", "2", "+", "3", "+"))
    // Test brackets
    calculatorModel.shuntingYard("( 1 + 2 ) * ( 3 / 4 ) ") should be (ListBuffer("1", "2", "+", "3", "4", "/", "*"))
    calculatorModel.shuntingYard("8 - ( 2 + 4 )") should be (ListBuffer("8", "2", "4", "+", "-"))
    // Test long expressions with nested brackets
    calculatorModel.shuntingYard("1 + 2 + ( 6 + ( 4 * 8 ) + 9 )") should be (ListBuffer("1", "2", "+", "6", "4", "8", "*", "+", "9", "+", "+"))
    // Test decimals and negative numbers
    calculatorModel.shuntingYard("1.0 - 2.0") should be (ListBuffer("1.0", "2.0", "-"))
  }
}
