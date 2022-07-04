(ns pedromanoel.playground.code-arcade.solution-test
  (:require
   [clojure.test :refer :all]
   [matcher-combinators.test :refer [match?]]
   [pedromanoel.playground.code-arcade.add-two-digits :refer [solution]]))

(deftest add-two-digits-test
  (is (match? 18
              (solution 99)))

  (is (match? 27
              (solution 999))))
