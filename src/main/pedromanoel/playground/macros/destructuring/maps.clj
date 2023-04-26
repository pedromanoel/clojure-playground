(ns pedromanoel.playground.macros.destructuring.maps)

(defn bindings
  [[binding-spec binding-val]]
  (->> binding-spec
       (map (fn [[spec-key spec-val :as binding-spec]]
              (cond
                (= :keys spec-key)
                (->> spec-val
                     (map (juxt identity (comp (partial get binding-val) keyword)))
                     (reduce concat))

                (= :as spec-key)
                [spec-val binding-val]

                (= :or spec-key)
                (->> spec-val
                     (map (juxt first (partial apply list 'or)))
                     (reduce concat))

                (symbol? spec-key)
                [spec-key (->> spec-val keyword (get binding-val))]

                :else
                binding-spec)))
       (reduce concat)))
