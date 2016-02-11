package se.citerus.practicalscala.log

import java.time.LocalDate

import org.scalatest.{Matchers, FunSpec}
import se.citerus.practicalscala.state.MemberId

class AccountTest extends FunSpec with Matchers {

  describe("Account") {
    describe("created from event list") {
      it("should throw if AccountStarted is not the first event") {
        an[IllegalArgumentException] should be thrownBy Account(List())

        an[IllegalArgumentException] should be thrownBy Account(List(
          PeriodStarted(LocalDate.now())
        ))

        an[IllegalArgumentException] should be thrownBy Account(List(
          PeriodStarted(LocalDate.now()),
          AccountStarted(LocalDate.now(), MemberId("foo"))
        ))
      }
      it("should create normally when AccountStarted is the first event") {
        Account(List(
          AccountStarted(LocalDate.now(), MemberId("foo"))
        ))

        Account(List(
          AccountStarted(LocalDate.now(), MemberId("foo")),
          PeriodStarted(LocalDate.now())
        ))
      }
    }

    describe("without any purchases") {
      val account = Account(MemberId("foo"))
      it("should return the member id") {
        account.id should be (MemberId("foo"))
      }
      it("should not be active") {
        account.isActive(LocalDate.now()) should be (false)
      }
      it("should have 0 accumulated value") {
        account.accumulatedValue(LocalDate.now()) should be (0)
      }
    }

    describe("with purchases in one period") {
      val accountStartDate = LocalDate.now().minusMonths(1)
      val lastDayOfPeriod = accountStartDate.plusYears(1).minusDays(1)

      val account = Account(List(
        AccountStarted(accountStartDate, MemberId("bar")),
        PeriodStarted(accountStartDate),
        Purchase(accountStartDate, 10, 10),
        Purchase(accountStartDate.plusDays(10), 20, 20)
      ))

      it("should return the member id") {
        account.id should be (MemberId("bar"))
      }
      it("should be active inside the period") {
        account.isActive(accountStartDate) should be (true)
        account.isActive(lastDayOfPeriod) should be (true)
      }
      it("should be inactive outside the period") {
        account.isActive(accountStartDate.minusDays(1)) should be (false)
        account.isActive(lastDayOfPeriod.plusDays(1)) should be (false)
      }
      it("should tally the correct accumulated value inside the period") {
        account.accumulatedValue(lastDayOfPeriod) should be (30)
        account.accumulatedValue(accountStartDate) should be (10)

        account.accumulatedValue(accountStartDate.plusDays(1)) should be (10)
        account.accumulatedValue(accountStartDate.plusDays(20)) should be (30)
      }
      it("should have 0 accumulated value outside the period") {
        account.accumulatedValue(accountStartDate.minusDays(1)) should be (0)
        account.accumulatedValue(lastDayOfPeriod.plusDays(1)) should be (0)
      }
    }
  }

}
