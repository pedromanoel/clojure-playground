(ns pedromanoel.playground.hacker-rank.subarray-division-test
  (:require [clojure.test :refer :all]
            [pedromanoel.playground.hacker-rank.subarray-division :refer [subarray-division]]))

(deftest subarray-division-test
  (testing "it returns zero when size is zero"
    (is (= [] (subarray-division [] 10 1))))

  (testing "it returns zero if array size is less than month"
    (is (= [] (subarray-division [1] 1 2))))

  (testing "it returns sub-arrays of size 1 and sum"
    (is (= [[1]] (subarray-division [1] 1 1)))
    (is (= [[20]] (subarray-division [20] 20 1))))

  (testing "it returns sub-arrays of expected sum"
    (is (= [] (subarray-division [1 2] 1 2)))
    (is (= [[1 2]] (subarray-division [1 2] 3 2)))
    (is (= [[15 5]] (subarray-division [15 5] 20 2))))

  (testing "it returns sub-arrays with bigger sizes"
    (is (= [[2 2] [1 3]] (subarray-division [2 2 1 3 2] 4 2)))))
