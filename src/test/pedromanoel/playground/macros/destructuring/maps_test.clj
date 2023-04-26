(ns pedromanoel.playground.macros.destructuring.maps-test
  (:require [clojure.test :refer :all]
            [matcher-combinators.test :refer [match?]]
            [pedromanoel.playground.macros.destructuring.maps :as maps]))

(deftest keys-bindings-test
  (testing "it returns a binding vector for :keys"
    (let [expanded (maps/bindings '[{:keys [x y z]} {:x 1 :z 2}])]
      (is (match? '[x 1 y nil z 2]
                  expanded)))))

(deftest symbols-bindings-test
  (testing "it returns a binding vector with symbols and values"
    (let [expanded (maps/bindings '[{my-x :x} {:x 1 :z 2}])]
      (is (match? '[my-x 1]
                  expanded)))))

(deftest as-binding-test
  (testing "it returns a binding vector for :as"
    (let [expanded (maps/bindings '[{:as my-map} {:x 1 :z 2}])]
      (is (match? '[my-map {:x 1 :z 2}]
                  expanded)))))

(deftest or-binding-test
  (testing "it returns a binding vector for :or"
    (let [expanded (maps/bindings '[{:or {x 0 y 0 z 0}} {:x 1 :z 2}])]
      (is (match? '[x (or x 0) y (or y 0) z (or z 0)]
                  expanded)))))

(deftest bindings-test
  (testing "it returns empty vector when binding-spec is empty"
    (is (match? []
                (maps/bindings '[{} {:x 1}]))))

  (testing "it combines different types of bindings"
    (let [expanded (maps/bindings '[{:keys [x y z]
                                     z     :z
                                     :as   my-map
                                     x     :x
                                     y     :y
                                     :or {x 0 y 0 z 0}} {:x 1 :z 2}])]
      (is (match? '[x 1 y nil z 2
                    z 2
                    my-map {:x 1 :z 2}
                    x 1 y nil
                    x (or x 0) y (or y 0) z (or z 0)]
                  expanded)))))
