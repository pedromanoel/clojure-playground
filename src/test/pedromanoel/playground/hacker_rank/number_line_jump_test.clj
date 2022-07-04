(ns pedromanoel.playground.hacker-rank.number-line-jump-test
  (:require
   [clojure.test :refer :all]
   [pedromanoel.playground.hacker-rank.number-line-jump :refer [kangaroo]]))

(deftest kangaroo-test
  (testing "it returns YES when kangaroo on the back is faster"
    (is (= "YES" (kangaroo 0 3 4 2))))

  (testing "it returns YES even when kangaroos in different parameter order"
    (is (= "YES" (kangaroo 4 2 0 3))))

  (testing "it returns YES when kangaroos start at the same place"
    (is (= "NO" (kangaroo 0 2 0 2)))
    (is (= "NO" (kangaroo 0 2 0 3))))

  (testing "it returns NO when kangaroos have same speed"
    (is (= "NO" (kangaroo 0 2 4 2))))

  (testing "it returns NO when kangaroos do not fall on the same place
              -0-1-2-3-4-5
            k1 *     *
            k2   * *"
    (is (= "NO" (kangaroo 0 3 1 1)))))
