(ns hacker-rank.divisible-sum-pairs)

(defn divisible? [dividend divisor]
  (zero? (rem dividend divisor)))

(defn divisible-sum-pair
  [[ix x] [iy y] divisor]
  (and (< ix iy)
       (divisible? (+ x y) divisor)))

(defn divisibleSumPairs
  [_array-size divisor numbers]
  (let [indexed-numbers (map-indexed vector numbers)]
    (for [x indexed-numbers
          y indexed-numbers
          :when (divisible-sum-pair x y divisor)]
      [(second x) (second y)])))
