(ns code-arcade.solution-test
  (:require [clojure.test :refer :all]
            [code-arcade.add-two-digits :refer [solution]]
            [matcher-combinators.test :refer [match?]]))

(deftest add-two-digits-test
  (is (match? 18
              (solution 99)))

  (is (match? 27
              (solution 999))))
