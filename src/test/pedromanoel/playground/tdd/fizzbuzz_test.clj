(ns pedromanoel.playground.tdd.fizzbuzz-test
  (:require
   [clojure.test :refer :all]
   [matcher-combinators.test :refer [match?]]
   [pedromanoel.playground.tdd.fizzbuzz :refer [fizzbuzz]]))

(deftest fizzbuzz-test
  (testing "it returns numbers as strings"
    (is (match? "1" (fizzbuzz 1))))

  (testing "it returns fizz when number is a multiple of three"
    (is (match? "fizz" (fizzbuzz 3))))

  (testing "it returns buzz when number is a multiple of three"
    (is (match? "buzz" (fizzbuzz 5))))

  (testing "it returns fizzbuzz when number is a multiple of three and five"
    (is (match? "fizzbuzz" (fizzbuzz 15)))))
