package week2

object session3 {
  val x = new Rational(1, 3)                      //> x  : week2.Rational = 1/3
  x.numer                                         //> res0: Int = 1
  x.denom                                         //> res1: Int = 3

  val y = new Rational(5, 7)                      //> y  : week2.Rational = 5/7
  val z = new Rational(3, 2)                      //> z  : week2.Rational = 3/2

  x + y                                           //> res2: week2.Rational = 22/21
  x - y - z                                       //> res3: week2.Rational = -79/42
  y + y                                           //> res4: week2.Rational = 10/7
  x < y                                           //> res5: Boolean = true
  x max y                                         //> res6: week2.Rational = 5/7
  //val strange = new Rational(1, 0)
  //strange.add(strange)
  new Rational(2)                                 //> res7: week2.Rational = 2/1
}

class Rational(x: Int, y: Int) {
  require(y != 0, "denominator must be nonzero")

  def this(x: Int) = this(x, 1)

  private def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)

  def numer = x
  def denom = y

  def < (that: Rational) = numer * that.denom < that.numer * denom

  def max(that: Rational) = if (this < that) that else this

  def + (that: Rational) =
    new Rational(
      numer * that.denom + that.numer * denom,
      denom * that.denom)

  def unary_- : Rational = new Rational(-numer, denom)

  def - (that: Rational) = this + -that

  override def toString = {
    val g = gcd(numer, denom)
    numer / g + "/" + denom / g
  }
}