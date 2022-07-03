(ns spec-test
  (:require [clojure.test :refer :all]
            [clojure.spec.alpha :as s]))

(deftest conform-test
  (testing "it accepts simple predicates, and returns conformed value"
    (is (= 2
           (s/conform even? 2)))
    (is (= "yes"
           (s/conform string? "yes")))
    (is (= 39
           (s/conform (partial > 40) 39))))

  (testing "it returns ::s/invalid when value does not conform to spec"
    (is (= ::s/invalid
           (s/conform int? 1.0)))))

(deftest valid?-test
  (testing "it accepts simple predicates and return boolean values"
    (is (true? (s/valid? even? 2)))
    (is (false? (s/valid? even? 3)))))
