(ns code-arcade.largest)

(defn solution [n]
  (Integer/parseInt (clojure.string/join (repeat n 9))))

(defn solution [n]
  (reduce + 0 (take n (iterate (partial * 10) 9))))

(defn solution [n]
  (dec (int (Math/pow 10 n))))

(def solution
  (comp dec int #(Math/pow 10 %)))

(solution 3)