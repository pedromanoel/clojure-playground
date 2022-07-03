(ns pedromanoel.playground.hacker-rank.number-line-jump)

(defn sorted-kangaroos
  [positions-and-velocities]
  (->> positions-and-velocities
       (partition 2)
       (sort-by first)))

(defn kangaroo-positions
  [[back-position back-velocity]
   [front-position front-velociy]]
  (map vector
       (iterate (partial + back-velocity) back-position)
       (iterate (partial + front-velociy) front-position)))

(defn is-back-kangaroo-faster?
  [& kangaroos]
  (let [velocity second]
    (apply > (map velocity kangaroos))))

(def did-not-meet
  (partial apply <))

(defn will-meet [[back-kangaroo front-kangaroo]]
  (when (is-back-kangaroo-faster? back-kangaroo front-kangaroo)
    (->> (kangaroo-positions back-kangaroo front-kangaroo)
         (drop-while did-not-meet)
         first
         (apply =))))

(defn kangaroo [& positions-and-velocities]
  (if (will-meet (sorted-kangaroos positions-and-velocities))
    "YES"
    "NO"))
