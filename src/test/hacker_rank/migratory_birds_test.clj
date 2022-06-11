(ns hacker-rank.migratory-birds-test
  (:require [clojure.test :refer :all])
  (:require [hacker-rank.migratory-birds :refer [migratoryBirds]]))

(deftest migratoryBirds-test
  (testing "it returns the most frequent number"
    (is (= 3
           (migratoryBirds [1 2 2 3 3 3]))))

  (testing "it works on any order"
    (is (= 1
           (migratoryBirds (shuffle [1 1 1 2 2 3])))))

  (testing "it returns the lowest in case of tie"
    (is (= 2
           (migratoryBirds [1 2 2 3 3])))))
