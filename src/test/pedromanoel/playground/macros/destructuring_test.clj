(ns pedromanoel.playground.macros.destructuring-test
  (:require [clojure.test :refer :all]
            [matcher-combinators.test :refer [match?]]
            [pedromanoel.playground.macros.destructuring :as destructuring]))

(deftest my-let-map-test
  (testing "it "
    (is (match? {:x 1 :y 0 :z 2 :my-map {:x 1 :z 2}}
                (destructuring/my-let [{:keys [x]
                                        y     :y
                                        z     :z
                                        :as   my-map
                                        :or   {y 0}} {:x 1 :z 2}]
                  {:x      x
                   :y      y
                   :z      z
                   :my-map my-map})))))
