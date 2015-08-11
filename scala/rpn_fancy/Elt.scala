package rpn_fancy

abstract class Elt {
  def get: Int
}

case class Num(n: Int) extends Elt {
  def get = n
}

case class Opr(opr: (Int, Int) => Int, a: Elt, b: Elt) extends Elt {
  def get = opr(a.get, b.get)
}
