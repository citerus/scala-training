package se.citerus.practicalscala.state

import java.time.LocalDate

import org.scalatest.{FunSpec, Matchers}

class StoreTest extends FunSpec with Matchers {

  describe("Store") {
    it("should keep track of purchases made for the same member id") {
      val store = Store()
      val id = MemberId("test-member")
      val today = LocalDate.now()

      // Take us up to the discount level, pay full price
      store.recordPurchase(id, 100, today) should be (100)
      // Next purchase is discounted
      store.recordPurchase(id, 100, today) should be (90)
    }

    it("should separate purchases made for different members") {
      val store = Store()
      val id1 = MemberId("test-member-1")
      val id2 = MemberId("test-member-2")
      val today = LocalDate.now()

      store.recordPurchase(id1, 100, today) should be (100)
      store.recordPurchase(id2, 100, today) should be (100)
    }

    it("should accumulate purchases done within one year of the first purchase") {
      val store = Store()
      val id = MemberId("test-member")
      val today = LocalDate.now()
      val oneYearButOneDay = today.plusYears(1).minusDays(1)

      store.recordPurchase(id, 100, today) should be (100)
      store.recordPurchase(id, 100, oneYearButOneDay) should be (90)
    }

    it("should start a new loyalty period if one year has passed since first purchase") {
      val store = Store()
      val id = MemberId("test-member")
      val today = LocalDate.now()
      val oneYearLater = today.plusYears(1)

      store.recordPurchase(id, 100, today) should be (100)
      store.recordPurchase(id, 100, oneYearLater) should be (100)
    }

  }

}
