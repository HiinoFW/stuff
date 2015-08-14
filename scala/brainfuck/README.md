# Brainfuck interpreter in Scala

## Definition

[Brainfuck](https://en.wikipedia.org/wiki/Brainfuck) is an esoteric language with a minimal amount
of different instructions. It is very simple to understand, but very hard to use.

Obviously it's not made for being used.

However, coding brainfuck interpreters is a fun and somewhat challenging exercise. Coding the loops
in particular is not trivial.

## Features

- Takes at least one argument : a brainfuck file name (ends with <code>.bf</code>). Additional
arguments are converted into input for the brainfuck program.
- <code>bfParse</code> returns a set of <code>BFI</code> (BrainFuck Instruction) objects that can be worked on
- <code>bfExec</code> executes a set of <code>BFI</code> and returns a string output
- Buffers multi-character inputs (including the starting arguments), only asks for more when it runs out
- Comments are allowed (ignored by the interpreter)

## Warnings

- Does not check (as of yet) if the brainfuck program is well-formed, unexpected results may appear
if you mess up loops.

## Dependencies

This interpreter uses my [scala_utils](https://github.com/HiinoFW/scala_utils/tree/master) library
(namely Ring and IO). Do not forget to include it as well.
