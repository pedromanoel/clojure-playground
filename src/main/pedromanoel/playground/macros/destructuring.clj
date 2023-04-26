(ns pedromanoel.playground.macros.destructuring)

(defn binding-keys
  "Receives a pair of binding-spec and binding-val:
   Ex.: [x 1], where x is the binding-spec and 1 is the binding-val.

   If the spec is a map "
  [[binding-spec binding-val]]
  (->> (:keys binding-spec)
       (map (juxt identity #((keyword %) binding-val)))
       (reduce concat)
       vec))

(defmacro my-let [binding-spec & forms]
  (apply list
         'let (binding-keys binding-spec)
         forms))
