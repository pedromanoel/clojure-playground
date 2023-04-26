(ns pedromanoel.playground.macros.destructuring.maps)

(defn keys-bindings
  [binding-val [spec-key spec-val]]
  (when (= :keys spec-key)
    (->> spec-val
             (map (juxt identity (comp (partial get binding-val) keyword)))
             (reduce concat))))

(defn as-bindings
  [binding-val [spec-key spec-val]]
  (when (= :as spec-key)
    [spec-val binding-val]))

(defn or-bindings
  [_ [spec-key spec-val]]
  (when (= :or spec-key)
    (->> spec-val
         (map (juxt first (partial apply list 'or)))
         (reduce concat))))

(defn symbol-bindings
  [binding-val [spec-key spec-val]]
  (when (symbol? spec-key)
    [spec-key (get binding-val (keyword spec-val))]))

(defn bindings
  [[binding-spec binding-val]]
  (->> binding-spec
       (map (some-fn (partial keys-bindings binding-val)
                     (partial as-bindings binding-val)
                     (partial or-bindings binding-val)
                     (partial symbol-bindings binding-val)))
       (reduce concat)
       vec))
