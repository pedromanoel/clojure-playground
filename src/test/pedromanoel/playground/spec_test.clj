(ns pedromanoel.playground.spec-test
  (:require
   [clojure.spec.alpha :as s]
   [clojure.test :refer :all]
   [matcher-combinators.test :refer [match?]]
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

(deftest explain-data-test
  (testing "it returns offending value in :val key"
    (is (match? #::s{:problems [{:val 10M}]}
                (s/explain-data int? 10M)))
    (is (match? #::s{:problems [{:val :foo}]}
                (s/explain-data string? :foo))))

  (testing "it returns failing predicate symbol in :pred"
    (is (match? #::s{:problems [{:pred `string?}]}
                (s/explain-data string? 10))))

  (testing "it returns ::s/unknown for custom predicates"
    (is (match? #::s{:problems [{:pred ::s/unknown}]}
                (s/explain-data (partial > 10) 11))))

  (testing "it returns registered spec in :via"
    (is (match? #::s{:problems [{:via [:deck/suit]}]}
                (s/explain-data :deck/suit 42)))
    (is (match? #::s{:problems [{:via [:acct/person :acct/last-name]}]}
                (s/explain-data :acct/person
                                #:acct{:first-name "Pedro"
                                       :last-name  nil
                                       :email      "a@example.com"}))))

  (testing "it returns empty in :via when spec is not registered"
    (is (match? #::s{:problems [{:via empty}]}
                (s/explain-data string? 42))))

  (testing "it returns failed specs in :path for combined specs"
    (is (match? #::s{:problems [{:path [:name]}
                                {:path [:id]}]}
                (s/explain-data :domain/name-or-id :foo))))

  (testing "it returns path of offending value in nested structures in :in key"
    (is (match? #::s{:problems [{:in [:acct/first-name]}
                                {:in [:acct/phone]}]}
                (s/explain-data :acct/person
                                #:acct{:first-name 10
                                       :last-name  "Evangelista"
                                       :email      "a@example.com"
                                       :phone      123}))))

  (testing "for entity-map"
    (testing "it return missing keys in :pred key"
      (is (match? #::s{:problems [{:pred `(fn [~'%] (contains? ~'% :acct/last-name))}
                                  {:pred `(fn [~'%] (contains? ~'% :acct/email))}]}
                  (s/explain-data :acct/person
                                  #:acct{:first-name "Pedro"}))))

    (testing "it return missing keys in :pred key"
      (is (match? #::s{:problems [{:pred `string? :path [:acct/last-name]}]}
                  (s/explain-data :acct/person
                                  #:acct{:first-name "Pedro"
                                         :last-name  10
                                         :email      "a@example.com"}))))))

(deftest keys-test
  (let [person #:acct{:first-name "Pedro"
                      :last-name  "Evangelista"
                      :email      "a@example.com"}]
    (testing ":req key"
      (testing "it validates required keys"
        (is (true? (s/valid? :acct/person person)))
        (is (false? (s/valid? :acct/person (dissoc person :acct/first-name))))
        (is (false? (s/valid? :acct/person (dissoc person :acct/last-name))))
        (is (false? (s/valid? :acct/person (dissoc person :acct/email))))))

    (testing ":opt key"
      (testing "it validates optional keys"
        (is (true? (s/valid? :acct/person (assoc person :acct/phone "123")))))))

  (testing ":req-un and :opt-un"
    (testing "it validates unamespaced entities"
      (is (true? (s/valid? :unq/person
                           {:first-name "Pedro"
                            :last-name  "Evangelista"
                            :email      "a@example.com"}))))))

(deftest keys*-test
  (testing "conform return seq as namespaced map"
    (is (match? #:my.config{:id :id1 :host "123" :port 8000}
                (s/conform :my.config/server
                           [:my.config/id :id1 :my.config/host "123" :my.config/port 8000]))))

  (testing "it validates optional keys"
    (is (true? (s/valid? :my.config/server [:my.config/id :id1 :my.config/host "123"])))
    (is (false? (s/valid? :my.config/server [:my.config/id :id1]))))

  (testing "it validates in any order keys"
    (is (true? (s/valid? :my.config/server [:my.config/id :id1 :my.config/host "123"])))
    (is (true? (s/valid? :my.config/server [:my.config/host "123" :my.config/id :id1])))))
