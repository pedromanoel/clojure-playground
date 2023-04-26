(ns pedromanoel.playground.macros.destructuring.maps)

(defn keys-bindings
  [[_ spec-val] binding-val]
  (->> spec-val
       (map (juxt identity (comp (partial get binding-val) keyword)))
       (reduce concat)))

(defn as-bindings
  [[_ spec-val] binding-val]
  [spec-val binding-val])

(defn or-bindings
  [[_ spec-val]]
  (->> spec-val
       (map (juxt first (partial apply list 'or)))
       (reduce concat)))

(defn symbol-bindings
  [[spec-key spec-val] binding-val]
  [spec-key (->> spec-val keyword (get binding-val))])

(defn bindings
  [[binding-spec binding-val]]
  (->> binding-spec
       (map (fn [[spec-key spec-val :as binding-spec]]
              (cond
                (= :keys spec-key)
                (keys-bindings binding-spec binding-val)

                (= :as spec-key)
                (as-bindings binding-spec binding-val)

                (= :or spec-key)
                (or-bindings binding-spec)

                (symbol? spec-key)
                (symbol-bindings binding-spec binding-val)

                :else
                binding-spec)))
       (reduce concat)
       vec))
