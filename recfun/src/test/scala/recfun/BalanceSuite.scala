package recfun

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class BalanceSuite extends FunSuite {
  import Main.balance

  test("balance: '(if (zero? x) max (/ 1 x))' is balanced") {
    assert(balance("(if (zero? x) max (/ 1 x))".toList))
  }

  test("balance: '(if (zero? x) max / 1 x))' is unbalanced") {
    assert(!balance("(if (zero? x) max / 1 x))".toList))
  }

  test("balance: 'I told him (that it's not (yet) done).\n(But he wasn't listening)' is balanced") {
    assert(balance("I told him (that it's not (yet) done).\n(But he wasn't listening)".toList))
  }

  test("balance: 'I told him (that it's not (yet) done).\n(But he wasn't listening' is unbalanced") {
    assert(!balance("I told him (that it's not (yet) done).\nBut he wasn't listening)".toList))
  }

  test("balance: ':-)' is unbalanced") {
    assert(!balance(":-)".toList))
  }

  test("balance: '())(' counting is not enough: rigth parenthesis") {
    assert(!balance("())(".toList))
  }

  test("balance: '()(()' counting is not enough: left parenthesis") {
    assert(!balance("()(()".toList))
  }

  test("balance: '()()' is balanced") {
    assert(balance("()()".toList))
  }

  test("balance: '[c * (a + b)] / (c - b)' is balanced") {
    assert(balance("[c * (a + b)] / (c - b)".toList))
  }

  test("balance: '(c * (a + b)) / (c - b))' is balanced") {
    assert(balance("(c * ((a + b) / (c)) - b)".toList))
  }

}
