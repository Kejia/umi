(ns umi.ontology.owl-test
  (:require [umi.ontology.owl :as owl]
            [clojure.test :refer :all]))

(def pizza-uri "http://protege.stanford.edu/ontologies/pizza/pizza.owl")

(deftest load-ontology-from-uri-test
  (is (= 940
         (.getAxiomCount (owl/load-ontology-from-uri pizza-uri))))
  (is (= 712
       (.getLogicalAxiomCount (owl/load-ontology-from-uri pizza-uri)))))
