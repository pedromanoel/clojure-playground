(ns pedromanoel.playground.destructuring
  (:require
   [clojure.test :refer :all]
   [matcher-combinators.matchers :as m]
   [matcher-combinators.test :refer [match?]]))

(deftest map-destructuring-test
  (testing "it destructures maps"
    (testing "using entry [:keys [& symbols]]"
      (let [{:keys [name]} {:name "Pedro"}]
        (is (match? "Pedro" name))))

    (testing "using entry [:namespace/keys [& symbol-names]]"
      (let [{:person/keys [name]} {:person/name "Pedro"}]
        (is (match? "Pedro" name))))

    (testing "using entry [symbol :symbol]"
      (let [{person-name :person/name} {:person/name "Pedro"}]
        (is (match? "Pedro" person-name))))

    (testing "mixing binding forms"
      (let [{person-name :person/name
             :person/keys [age]
             :keys [label]} {:person/name "Pedro"
                             :person/age 37
                             :label :engineer}]
        (is (= "Pedro" person-name))
        (is (= 37 age))
        (is (= :engineer label))))

    (testing "nesting binding forms"
      (let [{{:pet/keys [name]} :person/pet}
            {:person/pet {:pet/name "Rex"}}]
        (is (= "Rex" name))))

    (testing "with binding to whole map using entry [:as symbol]"
      (let [{:as person} {:person/name "Pedro"}]
        (is (match? (m/equals {:person/name "Pedro"})
                    person))))

    (testing "with default values using entry [:or {symbol default-value}]"
      (let [{:keys [name] :or {name "Unknown"}} {}]
        (is (= "Unknown" name))))))

(deftest destructuring-test
  (testing "var binding occurs in"
    (testing "let"
      (let [a-symbol "a value"]
        (is (= "a value" a-symbol))))

    (testing "fn"
      (is (= "a value"
             ((fn [a-symbol] a-symbol) "a value"))))))

(defmacro as-map [& vars]
  (->> vars
       (map (juxt keyword identity))
       (into {})))
