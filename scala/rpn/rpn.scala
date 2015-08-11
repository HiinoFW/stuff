import scala.annotation.tailrec

abstract class Elt
case class Num(n: Int) extends Elt
case class Opr(opr: (Int, Int) => Int) extends Elt

object Rpn {
  def main(args: Array[String]) {
    println(rpn("35 1 2 + 4 x+13 −random comment2*5%"))
  }

  def rpn(s: String) = {
    val parse = rpnParse(s)
    rpnSolve(parse)
  }

  def rpnParse(s: String) = {
    @tailrec
    def rpnParse_rec(s: List[Char], curNum: Option[Int], nbNum: Int, acc: Seq[Option[Elt]]): Seq[Elt] = {
      s match {
        case List() =>
          if (nbNum == 1) acc.flatten
          else throw new IllegalArgumentException
        case x :: xs => {
          val elt = eltParse(x)
          elt match {
            case Some(Num(n)) => curNum match {
              case None    => rpnParse_rec(xs, Some(n), nbNum + 1, acc)
              case Some(m) => rpnParse_rec(xs, Some(m * 10 + n), nbNum, acc)
            }
            case Some(Opr(_)) => {
              if (nbNum >= 2)
                rpnParse_rec(xs, None, nbNum - 1, acc :+ (curNum map (Num(_))) :+ elt)
              else throw new IllegalArgumentException
            }
            case _ => rpnParse_rec(xs, None, nbNum, acc :+ (curNum map (Num(_))))
          }
        }
      }
    }
    rpnParse_rec(s.toList, None, 0, List())
  }

  def eltParse(arg: Char): Option[Elt] = arg match {
    case '+'                                  => Some(Opr(_ + _))
    case '-' | '−'                            => Some(Opr(_ - _))
    case 'x' | '*'                            => Some(Opr(_ * _))
    case '/'                                  => Some(Opr(_ / _))
    case '%'                                  => Some(Opr(_ % _))
    case n if (n.toInt >= 48 && n.toInt < 58) => Some(Num(n.toInt - 48))
    case _                                    => None
  }

  def rpnSolve(args: Seq[Elt]) = {
    @tailrec
    def rpnSolve_rec(args: Seq[Elt], acc: List[Int]): Int = args match {
      case List() => acc(0)
      case x :: xs => {
        x match {
          case Num(n) => rpnSolve_rec(xs, n :: acc)
          case Opr(op) => {
            val (a :: b :: as) = acc
            rpnSolve_rec(xs, op(b, a) :: as)
          }
        }
      }
    }
    rpnSolve_rec(args, List())
  }
}
