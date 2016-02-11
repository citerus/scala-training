package se.citerus.practicalscala.futures

import java.time.LocalDate

import org.scalatest.concurrent.{IntegrationPatience, Eventually}
import org.scalatest.{Matchers, FunSuite}

import scala.util.Success

class ConsumerTest extends FunSuite with Matchers with Eventually with IntegrationPatience {

  test("increasing the accumulated value of an account") {
    val consumer = new Consumer()

    // Given an account with id 12345 and accumulated value 10
    consumer.storage.save(Account("12345", LocalDate.now(), 10))

    val result = consumer.increaseAccumulatedValue("12345", 20)

    eventually {
      result.value should be (Some(Success(BigDecimal(30))))
    }
  }

}