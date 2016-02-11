package se.citerus.practicalscala.futures

import java.time.LocalDate

import scala.util.Random

case class Account(id: String, firstPurchase: LocalDate, accumulatedValue: BigDecimal)

class StorageException(m: String) extends RuntimeException(m)

trait Storage {
  /**
    * Save the account to storage
    * @param account the account to save
    * @return the saved account
    */
  def save(account: Account): Account

  /**
    * Find an account with the specified id
    * @param id
    * @return the account, or None
    */
  def findById(id: String): Option[Account]

  /**
    * Retrieve the full set of ids stored
    */
  def ids: Set[String]
}

object Storage {
  def apply(unstable: Boolean = false): Storage =
    new StorageImpl(unstable)
}

private class StorageImpl(unstable: Boolean) extends Storage {
  private val rand = new Random()
  private var store: Map[String, Account] = Map(
    "12300" -> Account("12300", LocalDate.parse("2016-01-01"), 76.50),
    "99531" -> Account("99531", LocalDate.parse("2015-01-01"), 113.10),
    "70740" -> Account("70740", LocalDate.parse("2015-10-30"), 126.50)
  )

  def save(account: Account): Account = {
    synchronized {
      delay()
      maybeFail(0.1)
      store += (account.id -> account)
      account
    }
  }

  def findById(id: String): Option[Account] = {
    delay()
    maybeFail(0.4)
    synchronized {
      store.get(id)
    }
  }


  def ids: Set[String] = {
    delay(50)
    synchronized {
      store.keySet
    }
  }

  private def delay(ms: Int = 300): Unit = {
    Thread.sleep(rand.nextInt(ms) + 50)
  }

  private def maybeFail(failChance: Double): Unit = {
    if (unstable && rand.nextDouble() < failChance) throw new StorageException("Operation failed")
  }
}