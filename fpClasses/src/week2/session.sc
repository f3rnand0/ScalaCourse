package week2

object session {

// Sum of squares
  def sum(f: Int => Int, a: Int, b: Int): Int = {
    def loop(a: Int, acc: Int): Int = {
      if (a > b) acc
      else loop(a + 1, f(a) + acc)
    }
    loop(a, 0)
  }                                               //> sum: (f: Int => Int, a: Int, b: Int)Int
	sum (x => x * x, 3, 5)                    //> res0: Int = 50
	
	def product(f: Int => Int)(a: Int, b: Int): Int = {
		if (a > b) 1
		else f(a) * product(f)(a + 1, b)
	}                                         //> product: (f: Int => Int)(a: Int, b: Int)Int
	product(x => x * x)(3, 4)                 //> res1: Int = 144
	
	def fact(n: Int) = product(x => x)(1, n)  //> fact: (n: Int)Int
	fact(5)                                   //> res2: Int = 120
	
	def mapReduce(f: Int => Int, combine: (Int, Int) => Int, zero: Int)(a: Int, b: Int): Int =
		if (a > b) zero
		else combine(f(a), mapReduce(f, combine, zero)(a+1, b))
                                                  //> mapReduce: (f: Int => Int, combine: (Int, Int) => Int, zero: Int)(a: Int, b:
                                                  //|  Int)Int
		
	def productMapR(f: Int => Int)(a: Int, b: Int): Int = mapReduce(f, (x, y) => x * y, 1)(a, b)
                                                  //> productMapR: (f: Int => Int)(a: Int, b: Int)Int
	productMapR(x => x * x)(3, 4)             //> res3: Int = 144
	
}