package brainfuck

import annotation.tailrec
import util.tool.IO
import util.collection.Ring

object Brainfuck {
  def main(args: Array[String]) {
    if (args == Array()) {
      Console.err.println("No file given. Please input a Brainfuck (.bf) file name.")
      sys.exit(1)
    }
    
    val (name, in) = (args.head, args.tail.mkString)
    
    if (!name.endsWith(".bf")) {
      Console.err.println("Wrong extension file (\"" + name + "\"). Please input Brainfuck (.bf) file names only.")
      sys.exit(1)
    }
    
    val code = IO.readFileToString(name)
    bf(code, in)
  }
  
  def bf(code: String, in: String) = {
    val input =
      if (in == "")
        in
      else
        in + '\0'
    
    val (instr, _) = bfParse(code.toSeq)
    bfExec(instr, input)
  }
  
  def bfParse(code: Seq[Char]): (List[BFI], Seq[Char]) = {
    @tailrec
    def bfParse_rec(code: Seq[Char], acc: List[BFI]): (List[BFI], Seq[Char]) = code match {
      case Seq() => (acc, Seq())
      case ']' +: cs => (acc, cs)
      case '[' +: cs => {
        val (instr, rest) = bfParse(cs)
        bfParse_rec(rest, acc :+ Loop(instr))
      }
      case c +: cs => {
        val newAcc = c match {
          case '>' => acc :+ Right
          case '<' => acc :+ Left
          case '+' => acc :+ Plus
          case '-' => acc :+ Minus
          case '.' => acc :+ Out
          case ',' => acc :+ In
          case _   => acc
        }
        bfParse_rec(cs, newAcc)
      }
    }
    bfParse_rec(code, List())
  }
  
  def bfExec(instr: List[BFI], in: Seq[Char]) = {
    @tailrec
    def bfExec_rec(instr: List[BFI], arr: Ring[Int], in: Seq[Char]): Unit =
      instr match {
        case List() => println()
        case i :: is => {
          val (nInstr, nArr, nIn) = i match {
            case Right => (is, arr.shift, in)
            case Left => (is, arr.unshift, in)
            case Plus => (is, arr.change(_+1), in)
            case Minus => (is, arr.change(_-1), in)
            case Out => {
              print(arr.get.toChar)
              (is, arr, in)
            }
            case In => {
              readInput(in) match {
                case Seq() => (is, arr.replace(0), Seq())
                case s +: ss => (is, arr.replace(s), ss)
              }
              
            }
            case Loop(ls) => {
              if (arr.get == 0)
                (is, arr, in)
              else
                (ls ::: instr, arr, in)
            }
          }
          bfExec_rec(nInstr, nArr, nIn)
        }
      }
    
    def readInput(in: Seq[Char]): Seq[Char] = 
      if (in == Seq()) {
        println()
        io.StdIn.readLine().toSeq :+ '\0'
      }
      else in
      
    bfExec_rec(instr, Ring(List.fill(15000)(0), List.fill(15000)(0)), in)
  }
}