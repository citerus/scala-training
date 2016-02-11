package se.citerus.practicalscala.log

import java.time.LocalDate

import se.citerus.practicalscala.state.MemberId

object Account {
  def apply(events: List[Event]) = {
    events.headOption match {
      case Some(_: AccountStarted) => // ok
      case x =>
        require(false, s"Account events must begin with an AccountStarted events, but the first event was $x")
    }
    new Account(events.reverse)
  }

  def apply(id: MemberId) =
    new Account(List(AccountStarted(LocalDate.now(),id)))
}

/**
  * Create an account from a list of events. NOTE: The events are supplied in
  * reverse chronological order. In other words, the first event in the list is
  * the last event to have occured
  * @param events the events to construct the Account from
  */
class Account private (val events: List[Event]) {
  /**
    * Returns the member id of the account
    *
    * @return the member id
    */
  def id: MemberId = ???

  /**
    * Return whether the account is/was active at the specified date
    *
    * @param date the date for which to check the account activity
    * @return true if the account was active, false otherwise
    */
  def isActive(date: LocalDate): Boolean = ???

  /**
    * Return the accumulated value the account had at the specified date
    *
    * @param date the date for which to check the accumulated value
    * @return the accumulated value for the active loyalty period, or 0 if not active
    */
  def accumulatedValue(date: LocalDate): BigDecimal = ???

  /**
    * EXTRA WORK:
    * Return a new account, with one or more events prepended to fulfill the business
    * logic of adding a purchase to the account.
    *
    * Will need to take into account whether a new loyalty period starts with this
    * purchase
    *
    */
  def purchase(date: LocalDate, value: BigDecimal, amountPaid: BigDecimal): Account = ???


}
