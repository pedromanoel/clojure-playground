(ns pedromanoel.playground.spec-test
  (:require
   [clojure.spec.alpha :as s]
   [clojure.test :refer :all]
   [pedromanoel.playground.spec]))

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
           (s/conform int? 1.0))))

  (testing "it returns tuples for `or` predicates"
    (is (= [:name "Pedro"]
           (s/conform (s/or :name string? :id int?) "Pedro")))

    (is (= [:id 10]
           (s/conform (s/or :name string? :id int?) 10)))))

(deftest valid?-test
  (testing "it accepts simple predicates and return boolean values"
    (is (true? (s/valid? even? 2)))
    (is (false? (s/valid? even? 3))))

  (testing "it returns false for nil values"
    (is (false? (s/valid? string? nil)))))

(deftest and-or-nilable-test
  (testing "it combines predicates with `and`"
    (testing "(int?, even?, >= 1000)"
      (is (true? (s/valid? :num/big-even 1000)))
      (is (false? (s/valid? :num/big-even 1001)))
      (is (false? (s/valid? :num/big-even "1")))
      (is (false? (s/valid? :num/big-even 998)))))

  (testing "it combines predicates with `or`"
    (testing ":name or :id"
      (is (true? (s/valid? :domain/name-or-id "Pedro")))
      (is (true? (s/valid? :domain/name-or-id 1001)))
      (is (false? (s/valid? :domain/name-or-id :some)))))

  (testing "it returns true for nil values when nilable"
    (is (true? (s/valid? (s/nilable string?) nil)))))

(deftest registry-test
  (testing "it registers specs to keywords"
    (is (true? (s/valid? :order/date #inst "2022-07-03T12:00:00Z")))
    (is (true? (s/valid? :deck/suit :spade)))
    (is (false? (s/valid? :deck/suit :squares)))))
