exception Bad_expression

module Pn = struct
  type elt =
    | Val of int
    | Opr of (int -> int -> int)

  let parse s =
    let getAcc acc = function
      | "+" -> Opr ( + ) :: acc
      | "-" -> Opr ( - ) :: acc
      | "*" -> Opr ( * ) :: acc
      | "/" -> Opr ( / ) :: acc
      | _ -> acc
    in
    let rec parse acc = function
      | [] -> List.rev acc
      | x :: xs ->
	 match x with
	 | Str.Text n -> parse (Val (int_of_string n) :: acc) xs
	 | Str.Delim c ->
	    parse (getAcc acc c) xs
    in
    let r = Str.regexp "[^0-9]" in
    let spl = Str.full_split r s in
    parse [] spl

  let exec li =
    let rec exec = function
      | [] -> raise Bad_expression
      | (x :: xs) ->
	 match x with
	 | Val n -> (n, xs)
	 | Opr f ->
            let (a, rest1) = exec xs in
	    let (b, rest2) = exec rest1 in
	    (f a b, rest2)
    in
    let (res, rest) = exec li in
    if (rest != [])
    then raise Bad_expression
    else res

  let calc s = exec (parse s)
end


