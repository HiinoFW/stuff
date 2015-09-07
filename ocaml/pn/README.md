# Basic PN interpreter in OCaml

## Definition
PN means [Polish Notation](https://en.wikipedia.org/wiki/Polish_notation).
It's a way to write calculations without parentheses, where numbers are put after the operator.

**Example**: instead of <code>5 * (3 + 4)</code>, in PN we write <code>* 5 + 3 4</code> or <code>* + 3 4 5</code>

## Features

- Single-string input
- Spaces between elements of the input are optional
- Comments are allowed (ignored by the interpreter)
- Throws <code>Pn.Bad_expression</code> when the input is malformed

## Compile (for Windows)

1. Compile the module: <code>ocamlc -c pn.mli pn.ml</code>
2. Compile the test file as an executable: <code>ocamlc -o pn_test.exe str.cma pn.ml pn_test.ml</code>
3. Run the executable: <code>pn_test.exe "* 5 + 3 4"</code>

This example should output <code>35</code>.