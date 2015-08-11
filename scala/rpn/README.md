# Sort-of-basic RPN interpreter in Scala

## Definition
RPN means [Reverse Polish Notation](https://en.wikipedia.org/wiki/Reverse_Polish_notation).
It's a way to write calculations without parentheses, where numbers are put before the operator.

**Example**: instead of <code>(3 + 4) * 5</code>, in RPN we write <code>3 4 + 5 *</code> or <code>5 3 4 + *</code>

## Features

- Single-string input
- Spaces between elements of the input are optional
- Comments are allowed (ignored by the interpreter)
- Throws <code>IllegalArgumentException</code> when the input is malformed
