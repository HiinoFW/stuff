# Fancy RPN interpreter in Scala

## Definition
RPN means [Reverse Polish Notation](https://en.wikipedia.org/wiki/Reverse_Polish_notation).
It's a way to write calculations without parentheses, where numbers are put before the operator.

**Example**: instead of <code>(3 + 4) * 5</code>, in RPN we write <code>3 4 + 5 *</code> or <code>5 3 4 + *</code>

## Features

- Returns the data tree of the operation instead of just the result (use <code>.get</code> to obtain the result)
- Single-string input
- Spaces between elements of the input are optional
- Comments are allowed (ignored by the interpreter)

## Changes from the [other RPN solution](https://github.com/HiinoFW/stuff/tree/master/scala/rpn)

- Shorter
- **Does not** throw <code>IllegalArgumentException</code> when the input is malformed, make sure the amount of numbers and operators is correct or the result may be false!
- Uses a regex for parsing
- Uses a Map instead of pattern matching for operators
- Returns a data tree representing the entire operation without calculating it
- Calculations are made within the operation objects, call the <code>.get</code> method
