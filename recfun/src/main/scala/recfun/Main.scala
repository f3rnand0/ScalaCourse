package recfun
import common._
import scala.annotation.tailrec

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = {
    if (c == 0 || c == r)
      1
    else
      pascal(c, r - 1) + pascal(c - 1, r - 1)
  }

  /**
   * Exercise 2
   */
  def balance(chars: List[Char]): Boolean = {
    if (chars.isEmpty) true
    else {
      def countPars(chars: List[Char], openPars: Int): Boolean = {
        if (chars.isEmpty) {
          openPars == 0
        } else {
          val h = chars.head
          val n = {
            if (h == '(') openPars + 1
            else if (h == ')') openPars - 1
            else openPars
          }
          if (n >= 0) countPars(chars.tail, n)
          else false
        }
      }
      countPars(chars, 0)
    }
  }

  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int = {
    def count(capacity: Int, coinsForChange: List[Int]): Int = {
      if (capacity == 0)
        1
      else if (capacity < 0)
        0
      else if (coinsForChange.isEmpty && capacity >= 1)
        0
      else
        count(capacity, coinsForChange.tail) + count(capacity - coinsForChange.head, coinsForChange)
    }
    //count(money, coins.sortWith(_.compareTo(_) < 0))
    count(money, coins)
  }

}
