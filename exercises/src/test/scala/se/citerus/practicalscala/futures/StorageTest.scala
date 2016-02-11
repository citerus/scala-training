package se.citerus.practicalscala.futures

import java.time.LocalDate

import org.scalatest.{Matchers, FunSuite}

class StorageTest extends FunSuite with Matchers {

  test("default store") {
    val storage = Storage()

    storage.findById("12300") should be (Some(Account("12300", LocalDate.parse("2016-01-01"), 76.50)))
    storage.ids should be (Set("12300", "99531", "70740"))
    val today: LocalDate = LocalDate.now()
    storage.save(Account("12345", today, 15))
    storage.findById("12345") should be (Some(Account("12345", today, 15)))
    storage.ids should be (Set("12300", "12345", "99531", "70740"))
  }

}