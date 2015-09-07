open Pn
let () =
  if (Array.length Sys.argv) < 2
  then print_endline "Add an argument!"
  else Pn.calc Sys.argv.(1)
       |> string_of_int
       |> print_endline
