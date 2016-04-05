(ns umi.ontology.core-test
  (:require [umi.ontology.core :as core]
            [clojure.test :refer :all]))

(def pizza-uri "http://protege.stanford.edu/ontologies/pizza/pizza.owl")

(deftest load-ontology-from-uri-test
  (is (= 940
         (.getAxiomCount (core/load-ontology-from-uri pizza-uri))))
  (is (= 712
       (.getLogicalAxiomCount (core/load-ontology-from-uri pizza-uri)))))
