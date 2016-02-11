package se.citerus.practicalscala.basic

import org.scalatest.{Matchers, FunSpec}

class CalculatorTest extends FunSpec with Matchers {

  describe("a Calculator") {
    describe("with 0 accumulated value") {
      val calculator = Calculator(accumulatedValue = 0)
      it("gives no discount to a purchase <= 100") {
        calculator.amountToPay(1) should be (1)
        calculator.amountToPay(100) should be (100)
      }
      it("gives discount to the part of the purchase > 100") {
        calculator.amountToPay(200) should be (100 + 100 * 0.9)
        calculator.amountToPay(110) should be (100 + 10 * 0.9)
      }
    }
    describe("with 40 accumulated value") {
      val calculator = Calculator(accumulatedValue = 40)
      it("gives no discount to purchases <= 60") {
        calculator.amountToPay(1) should be (1)
        calculator.amountToPay(60) should be (60)
      }
      it("gives discount to the part of the purchase > 60") {
        calculator.amountToPay(160) should be (60 + 100 * 0.9)
        calculator.amountToPay(70) should be (60 + 10 * 0.9)
      }
    }
    describe("with 100 accumulated value") {
      val calculator = Calculator(accumulatedValue = 100)
      it("gives discount to all purchases") {
        calculator.amountToPay(1) should be (0.9)
        calculator.amountToPay(100) should be (90)
      }
    }
  }

}
