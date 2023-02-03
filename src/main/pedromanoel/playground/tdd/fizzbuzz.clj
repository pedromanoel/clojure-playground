(ns pedromanoel.playground.tdd.fizzbuzz)

(defn mul? [n div]
  (zero? (rem n div)))

(defn fizz? [n]
  (when (mul? n 3)
    "fizz"))

(defn buzz? [n]
  (when (mul? n 5)
    "buzz"))

(defn fizzbuzz [n]
  (let [fizz (fizz? n)
        buzz (buzz? n)]
    (if (or fizz buzz)
      (str fizz buzz)
      (str n))))
