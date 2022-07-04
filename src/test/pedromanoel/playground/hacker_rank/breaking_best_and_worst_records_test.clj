(ns pedromanoel.playground.hacker-rank.breaking-best-and-worst-records-test
  (:require
   [clojure.test :refer :all]
   [matcher-combinators.test :refer [match?]]
   [pedromanoel.playground.hacker-rank.breaking-best-and-worst-records :refer [breakingRecords]]))

(deftest breakingRecords-test
  (testing "hackerrank test"
    (is (= [2 4]
           (breakingRecords [10 5 20 20 4 5 2 25 1]))))

  (testing "return[0]"
    (testing "it does not count single score"
      (is (match? [0 int?]
                  (breakingRecords [1]))))

    (testing "it counts consecutive increases"
      (is (match? [2 int?]
                  (breakingRecords [1 2 1 3])))))

  (testing "return[1]"
    (testing "it does not count single score"
      (is (match? [int? 0]
                  (breakingRecords [1]))))

    (testing "it counts consecutive decreases"
      (is (match? [int? 2]
                  (breakingRecords [3 2 3 1]))))))
