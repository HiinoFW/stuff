# Brainfuck interpreter in Scala

## Definition

[Brainfuck](https://en.wikipedia.org/wiki/Brainfuck) is an esoteric language with a minimal amount
of different instructions. It is very simple to understand, but very hard to use.

Obviously this language is not made for being used.

However, coding brainfuck interpreters is a fun and somewhat challenging exercise. Coding the loops
in particular is not trivial.

## Features

- Takes at least one argument : a brainfuck file name (ends with <code>.bf</code>). Additional
arguments are converted into input for the brainfuck program.
- <code>bfParse</code> returns a set of <code>BFI</code> (BrainFuck Instruction) objects that can be worked on
- <code>bfExec</code> executes a set of <code>BFI</code> and directly prints the output
- Comments are allowed (ignored by the interpreter)

## About input and output

#### Input

There are two modes: dynamic and static input.
Static is when you add your input in the arguments of the program after the brainfuck file name, dynamic is when you don't (you only input the brainfuck file name).

In static mode, the interpreter reads the input character by character, then after it runs out, reads <code>null</code> characters.

In dynamic mode, the interpreter asks for line-by-line input from the user. Once a line is cleared, it will read a single <code>null</code> character, then ask for another line from the user if needed.

#### Output

Output is printed directly, character by character. There is no return value yet from the execution routine.

Newlines are printed everytime the interpreter asks for an input line from the user (so, only in dynamic input mode, and only if needed).

## Warnings

- Does not check (as of yet) if the brainfuck program is well-formed, unexpected results may appear
if you mess up loops.
- As stated earlier, the output is only printed and is not returned as a <code>String</code> (as of yet).

## Dependencies

This interpreter uses my [scala_utils](https://github.com/HiinoFW/scala_utils/tree/master) library
(namely <code>Ring</code> and <code>IO</code>). Do not forget to include it as well.
