package brainfuck

class BFI

case object Right extends BFI
case object Left extends BFI
case object Plus extends BFI
case object Minus extends BFI
case object Out extends BFI
case object In extends BFI

case class Loop(val loop: List[BFI]) extends BFI