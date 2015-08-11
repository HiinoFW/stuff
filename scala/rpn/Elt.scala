package rpn

abstract class Elt
case class Num(n: Int) extends Elt
case class Opr(opr: (Int, Int) => Int) extends Elt
