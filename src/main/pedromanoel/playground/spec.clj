(ns pedromanoel.playground.spec
  (:require [clojure.spec.alpha :as s]))

(s/conform (s/and int? even?) "100")
