Counts every character in the input and returns how many times each character has been used



array format:   0~~~XC___0
starting position:    ^


++++++++[>++++++++[<<++++>>-]<-]<-   obtain the value 255 in a cell (C)

[[>]+[<]>-]   initialize char count array: put value 1 in each of the 255 cells directly to the right (___)

<,   get input value (X)

[   input processing loop

  [[<]+[>]<-]<[<]>    move to the left (~~~) as many times as the input value (X)

  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>+   move to the corresponding cell (___) and increment it

  [<]<<[<]>[->]   clean up left side of the array (~~~)
  
  ,    get another input (X)

]   end of input



array format: 0arro0FCn00___X0
current position:      ^


<++++[>++++<-]>[<++++[<++++>-]>-]    initialize char counter cell (C) to 256

<++++++++++   prepare newline character (n)

<-    set char counter cell (C) to 255

<+++++[<+++>-]<[<++<+++<++++<++>>>>-]<++<<++<++[>]>>   prepare arrow string (arro)

[   output displaying loop

  <+   next foreach char iteration (F)
  
  >>>>>    go to beginning of char count array (___)
  
  [>]<-    get to count cell of current char (X) and remove the extra value we added at the beginning
  
  [   if current char has at least one occurrence
  
    [<]<<<<.   go back and display current character

    <<[.<]>[>]    display arrow string (arro)

    >>>>>>   go to beginning of char count array (___)

    [>]   get to count cell of current char (X)
    
    ++++++++[<++++++>-]<.    display count for current char (X)

    [-]   reset cell (X)

    <[<]<<.>>   display newline character (n)
    
  ]   else write nothing
  
  <[<]<[>]    reset position

  <<-   decrement char counter cell (C)

]   end of char counter
