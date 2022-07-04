(ns pedromanoel.playground.hacker-rank.between-two-sets
  (:require
   [clojure.set :refer [intersection]]))

(defn factor? [divisor dividend]
  (zero? (rem dividend divisor)))

(defn multiples [max n]
  (->> (iterate inc 1)
       (map (partial * n))
       (take-while (partial >= max))
       (apply sorted-set)))

(defn factor-of-every [numbers factor]
  (every? (partial factor? factor) numbers))

(defn common-factors [largest numbers]
  (->> numbers
       (map (partial multiples largest))
       (apply intersection)))

(defn getTotalX [a b]
  (let [largest (first (sort > b))]
    (->> (common-factors largest a)
         (filter (partial factor-of-every b)))))
