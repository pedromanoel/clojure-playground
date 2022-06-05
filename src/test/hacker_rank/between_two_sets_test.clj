(ns hacker-rank.between-two-sets-test
  (:require [clojure.test :refer :all])
  (:require [hacker-rank.between-two-sets :refer [getTotalX]]))

(deftest getTotalX-test
  (testing "it returns empty list when there are no common factors"
    (is (= []
           (getTotalX [2] [3]))))

  (testing "it returns common multiples on first that are factors of second"
    (is (= [6]
           (getTotalX [2 3] [6])))

    (is (= [2 4]
           (getTotalX [2] [4])))

    (is (= [4 8 16]
           (getTotalX [2 4] [16 32 96])))

    (is (= [6 12]
           (getTotalX [2 6] [24 36])))

    (is (= [12 24]
           (getTotalX [3 4] [24 48])))))
