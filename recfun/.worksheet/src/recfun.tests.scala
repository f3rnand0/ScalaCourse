package recfun

import scala.annotation.tailrec

object tests {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(111); 

  println("Welcome to the Scala worksheet");$skip(133); 

  def pascal(c: Int, r: Int): Int = {
    if (c == 0 || c == r)
      1
    else
      pascal(c, r - 1) + pascal(c - 1, r - 1)
  };System.out.println("""pascal: (c: Int, r: Int)Int""");$skip(17); val res$0 = 
  
  pascal(1,2);System.out.println("""res0: Int = """ + $show(res$0))}

}
