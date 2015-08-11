package rpn_fancy

import annotation.tailrec

object Rpn {  
  def main(args: Array[String]) {
    val tree = rpn("12 8comment- morecomments7 2*+") // gives the tree structure of the operation
    val res = tree.get // gives the result of the operation
    println(res)
  }
  
  val oprs = Map[Char, Option[(Int, Int) => Int]](
    '%' -> Some(_%_),
    '*' -> Some(_*_),
    '+' -> Some(_+_),
    '-' -> Some(_-_),
    '/' -> Some(_/_)
  ).withDefaultValue(None)

  def rpn(arg: String) = {
    @tailrec
    def rpn_rec(args: List[String], acc: List[Elt]): Elt = {
      args match {
        case List() => acc(0)
        case s :: ss =>
          if (isNumber(s))
            rpn_rec(ss, Num(s.toInt) :: acc)
          else oprs(s(0)) match {
            case None => rpn_rec(ss, acc)
            case Some(op) => {
              val (a :: b :: as) = acc
              rpn_rec(ss, Opr(op, b, a) :: as)
            }
          }
      }
    }
    val ss = "(\\d+|.)".r.findAllIn(arg)
    rpn_rec(ss.toList, List())
  }
  
  def isNumber(arg: String) = {
    val n = arg(0).toInt
    (n >= 48 && n < 58)
  }
}
