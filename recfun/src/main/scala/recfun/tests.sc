package recfun

import scala.annotation.tailrec

object tests {

  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  def pascal(c: Int, r: Int): Int = {
    if (c == 0 || c == r)
      1
    else
      pascal(c, r - 1) + pascal(c - 1, r - 1)
  }                                               //> pascal: (c: Int, r: Int)Int
  
  pascal(1,2)                                     //> res0: Int = 2

}