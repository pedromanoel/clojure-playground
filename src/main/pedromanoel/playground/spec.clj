(ns pedromanoel.playground.spec
  (:require
   [clojure.spec.alpha :as s]))

;; simple predicates
(s/def :order/date inst?)
(s/def :deck/suit #{:club :diamonds :heart :spade})
;; combined predicates
(s/def :num/big-even (s/and int? even? (partial <= 1000)))
(s/def :domain/name-or-id (s/or :name string?
                                :id int?))
;; map predicates
(def email-regex #"^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,63}$")
(s/def :acct/email-type (s/and string? (partial re-matches email-regex)))

(s/def :acct/acctid int?)
(s/def :acct/first-name string?)
(s/def :acct/last-name string?)
(s/def :acct/phone string?)
(s/def :acct/email :acct/email-type)

(s/def :acct/person (s/keys :req [:acct/first-name :acct/last-name :acct/email]
                            :opt [:acct/phone]))
