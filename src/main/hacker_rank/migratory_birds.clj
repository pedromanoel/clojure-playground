(ns hacker-rank.migratory-birds)

(def sightings-then-birds
  (juxt (comp - second) first))

(defn migratoryBirds [arr]
  (->> (frequencies arr)
       (sort-by sightings-then-birds)
      ffirst))
