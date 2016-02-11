package se.citerus.practicalscala.basic

case class Calculator(accumulatedValue: BigDecimal) {
  val discountLimit: BigDecimal = 100
  val discountPercentage: BigDecimal = 10

  def amountToPay(value: BigDecimal): BigDecimal = ???

}
