package brainfuck

import annotation.tailrec
import util.tools.IO
import util.Ring

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
    println(bf(code, in))
  }
  
  def bf(code: String, in: String) = {
    val (instr, _) = bfParse(code.toSeq)
    bfExec(instr, in)
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
    def bfExec_rec(instr: List[BFI], arr: Ring[Int], in: Seq[Char], out: List[Char]): String =
      instr match {
        case List() => out.mkString
        case i :: is => {
          val (nInstr, nArr, nIn, nOut) = i match {
            case Right => (is, arr.shift, in, out)
            case Left => (is, arr.unshift, in, out)          
            case Plus => (is, arr.change(_+1), in, out)
            case Minus => (is, arr.change(_-1), in, out)
            case Out => (is, arr, in, out :+ (arr.get).toChar)
            case In => {
              val s :: ss = readInput(in)
              (is, arr.replace(s), ss, out)
            }
            case Loop(ls) => {
              if (arr.get == 0)
                (is, arr, in, out)
              else
                (ls ::: instr, arr, in, out)
            }
          }
          bfExec_rec(nInstr, nArr, nIn, nOut)
        }
      }
    
    def readInput(in: Seq[Char]): Seq[Char] = 
      if (in == Seq()) {
        val newin = io.StdIn.readLine().toSeq
        if (newin == Seq())
          sys.exit(0)
        newin
      }
      else in
      
    bfExec_rec(instr, Ring(List.fill(30000)(0)), in, List())
  }
}