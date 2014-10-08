package week3

object overrides {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(77); 
  println("Welcome to the Scala worksheet")}
}

abstract class Base {
	def foo = 1
	def bar: Int
}

class Sub extends Base {
	override def foo = 2
	// Methods that implements an inherited method the word "overrride" is optional
	def bar = 3
}
