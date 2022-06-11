(ns hacker-rank.migratory-birds)

(defn sightings-then-birds [[bird-a sightings-a] [bird-b sightings-b]]
  (if (= sightings-b sightings-a)
    (compare bird-a bird-b)
    (compare sightings-b sightings-a)))

(defn migratoryBirds [arr]
  (->> arr
       frequencies
       (sort sightings-then-birds)
      ffirst))
