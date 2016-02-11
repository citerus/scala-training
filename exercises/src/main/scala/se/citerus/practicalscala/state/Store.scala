package se.citerus.practicalscala.state

import java.time.LocalDate

object Store {
  def apply() = new Store()
}

class Store {

  /**
    * Records a purchase for the member, with the purchased value, calculates
    * discount if eligible and returns the amount to pay for the purchase.
    *
    * @param id Member ID for the customer
    * @param value value of the purchase
    * @param dateOfPurchase the date for this purchase
    * @return amount for customer to pay for this purchase, after eventual discount
    */
  def recordPurchase(id: MemberId, value: BigDecimal, dateOfPurchase: LocalDate): BigDecimal = ???

}
