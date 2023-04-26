(ns pedromanoel.playground.macros.destructuring.maps)

(defn keys-spec->bindings
  "Converts keys into bindings. Ex.: [{:keys [x]} {:x 1}] => [x 1]"
  [binding-val [spec-key spec-val]]
  (when (= :keys spec-key)
    (->> spec-val
             (map (juxt identity (comp (partial get binding-val) keyword)))
             (reduce concat))))

(defn as-spec->bindings
  "Converts as into bindings. Ex.: [{:as m} {:x 1}] => [m {:x 1}]"
  [binding-val [spec-key spec-val]]
  (when (= :as spec-key)
    [spec-val binding-val]))

(defn or-spec->bindings
  "Converts or into bindings. Ex.: [{:or {:x 0}} {:x 1}] => [x (or x 0)].

  Note that this binding will fail if x was not previously destructured."
  [_ [spec-key spec-val]]
  (when (= :or spec-key)
    (->> spec-val
         (map (juxt first (partial apply list 'or)))
         (reduce concat))))

(defn symbol-spec->bindings
  "Converts symbols into bindings. Ex.: [{my-x :x} {:x 1}] => [my-x 1]"
  [binding-val [spec-key spec-val]]
  (when (symbol? spec-key)
    [spec-key (get binding-val (keyword spec-val))]))

(defn bindings
  [[binding-spec binding-val]]
  (->> binding-spec
       (map (some-fn (partial keys-spec->bindings binding-val)
                     (partial as-spec->bindings binding-val)
                     (partial or-spec->bindings binding-val)
                     (partial symbol-spec->bindings binding-val)))
       (reduce concat)
       vec))
