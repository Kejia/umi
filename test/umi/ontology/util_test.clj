(ns umi.ontology.util-test
  (:require [umi.ontology.util :as util]
            [clojure.test :refer :all]))

(def class-americanhot-uri "http://www.co-ode.org/ontologies/pizza/pizza.owl#AmericanHot")
(def class-americanhot-id "http://www.co-ode.org/ontologies/pizza/pizza.owl#AmericanHot")

(deftest uri->class-exp-test
  (is (= class-americanhot-id
         (.toStringID (util/uri->class-exp class-americanhot-uri)))))
