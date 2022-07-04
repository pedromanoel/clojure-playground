(ns pedromanoel.playground.spec
  (:require
   [clojure.spec.alpha :as s]))

(s/def :order/date inst?)
(s/def :deck/suit #{:club :diamonds :heart :spade})
(s/def :num/big-even (s/and int? even? (partial <= 1000)))
(s/def :domain/name-or-id (s/or :name string?
                                :id int?))
