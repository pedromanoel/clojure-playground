(ns pedromanoel.playground.macros.destructuring-test
  (:require [clojure.test :refer :all]
            [matcher-combinators.test :refer [match?]]
            [pedromanoel.playground.macros.destructuring :refer [my-let]]))

(deftest my-let-map-test
  (testing "it destructures maps correctly"
    (is (match? {:name "Pedro"
                 :age :unknown
                 :my-name "Pedro"
                 :person {:name "Pedro"}}
                (my-let [{:keys   [name age]
                          my-name :name
                          :as     person
                          :or     {name :unknown
                                   age  :unknown}}
                         {:name "Pedro"}]
                  {:name name
                   :age age
                   :my-name my-name
                   :person person})))))
