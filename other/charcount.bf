Counts every character in the input and returns how many times each character has been used



array format:   ~~~X___
starting position:  ^


++++[>++++<-]>[<++++[<++++>-]>-]<<-   obtain the value 255 in a cell (X)

[[>]+[<]>-]   initialize char count array: put value 1 in each of the 255 cells directly to the right (___)

,   get input value (X)

[   input processing loop

  [[<+>-]<-]    move to the left (~~~) as many times as the input value (X)
  

  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>+   move to the corresponding cell (___) and increment it

  [<]
  
  ,    go back to the input value cell (X) and get another input

]   end of input



array format: 0arro0FCn0___X
current position:      ^


<++++[>++++<-]>[<++++[<++++>-]>-]    initialize char counter cell (C) to 256

<++++++++++   prepare newline character (n)

<-    set char counter cell (C) to 255

<+++++[<+++>-]<[<++<+++<++++<++>>>>-]<++<<++<++[>]>>   prepare arrow string (arro)

[   output diplaying loop

  <   go to foreach char cell (F)

  +.    display current character (F)

  <<[.<]>[>]    display arrow string (arro)

  >>>>>   go to beginning of char count array (___)

  [>]<   get to count cell of current char (X)

  +++++++ ++++++++++ ++++++++++ ++++++++++ ++++++++++.    display count for current char (X)

  [-]   reset cell (X)

  <[<]<.   display newline character (n)

  <-   decrement char counter cell (C)

]   end of char counter
