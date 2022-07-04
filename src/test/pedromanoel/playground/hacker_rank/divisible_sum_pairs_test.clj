(ns pedromanoel.playground.hacker-rank.divisible-sum-pairs-test
  (:require
   [clojure.test :refer :all])
  (:require [pedromanoel.playground.hacker-rank.divisible-sum-pairs :refer [divisibleSumPairs]]))

(deftest divisibleSumPairs-test
  (testing "it returns zero if array is empty"
    (is (= []
           (divisibleSumPairs 0 2 []))))

  (testing "it returns one if the pair is divisible"
    (is (= [[2 2]]
           (divisibleSumPairs 0 2 [2 2])))
    (is (= []
           (divisibleSumPairs 0 2 [2 3]))))

  #_(testing "it does not return pairs in decreasing order"
      (is (= []
             (divisibleSumPairs 0 2 [4 2]))))

  (testing "it returns when there are three numbers"
    (is (= [[2 2] [2 2] [2 2]]
           (divisibleSumPairs 0 2 [2 2 2])))))
