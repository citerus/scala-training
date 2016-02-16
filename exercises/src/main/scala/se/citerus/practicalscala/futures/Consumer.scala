package se.citerus.practicalscala.futures

import java.time.LocalDate

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class Consumer(val storage: Storage = Storage()) {

  def accumulatedValue(id: String): Future[BigDecimal] = Future {
    val account = storage.findById(id)
    account match {
      case Some(a) => a.accumulatedValue
      case None => 0
    }
  }

  // Implement in exercise 5
  def increaseAccumulatedValue(id: String, amount: BigDecimal): Future[BigDecimal] = ???

  // Implement in exercise 7
  def allAccounts(): Future[List[Account]] = ???

  // Implement in exercise 8
  def activeAccounts(today: LocalDate): Future[List[Account]] = ???

  // Async wrappers for storage access
  def findById(id: String): Future[Option[Account]] = Future { storage.findById(id) }

  def save(account: Account): Future[Account] = Future { storage.save(account) }

  def ids: Future[Set[String]] = Future { storage.ids }

}