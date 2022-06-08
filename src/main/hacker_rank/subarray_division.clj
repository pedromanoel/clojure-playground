(ns hacker-rank.subarray-division)

(defn subarray-division [arr sum size]
  (->> arr
       (partition size 1)
       (filter (comp (partial = sum)
                     (partial reduce + 0)))))

(defn birthday
  [arr sum size]
  (count (subarray-division arr sum size)))
