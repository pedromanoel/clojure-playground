(ns pedromanoel.playground.code-arcade.add-two-digits)

(defn char->digit [char]
  (Character/digit ^Character char 10))

(defn solution [n]
  (transduce (map char->digit) + 0 (str n)))
