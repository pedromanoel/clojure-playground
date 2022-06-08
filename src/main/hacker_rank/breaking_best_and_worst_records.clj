(ns hacker-rank.breaking-best-and-worst-records)

(defn records-count
  [{:keys [worst best] :as records} score]
  (cond
    (> score best)
    (-> records
        (update-in [:records 0] inc)
        (assoc :best score))

    (< score worst)
    (-> records
        (update-in [:records 1] inc)
        (assoc :worst score))

    :else records))

(defn breakingRecords [[first-score & scores]]
  (:records
    (reduce records-count
            {:worst   first-score
             :best    first-score
             :records [0 0]}
            scores)))
