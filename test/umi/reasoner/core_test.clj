(ns umi.reasoner.core-test
  (:require [umi.ontology.owl :as owl]
            [umi.ontology.util :as util]
            [umi.reasoner.core :as core]
            [clojure.test :refer :all]))

(def pizza-uri "http://protege.stanford.edu/ontologies/pizza/pizza.owl")
(def pizza-ontology (owl/load-ontology-from-uri pizza-uri))
(def class-americanhot-uri "http://www.co-ode.org/ontologies/pizza/pizza.owl#AmericanHot")
(def class-americanhot (util/uri->class-exp class-americanhot-uri))
(def class-americanhot-id "http://www.co-ode.org/ontologies/pizza/pizza.owl#AmericanHot")

(deftest consistent?-test
  (is (core/consistent? pizza-ontology)))

(deftest satisfiable?-test
  (is (core/satisfiable? class-americanhot pizza-ontology)))

(deftest get-top-test
  (is (.isTopNode (core/get-top (owl/load-ontology-from-uri pizza-uri)))))

(deftest get-bottom-test
  (is (.isBottomNode (core/get-bottom (owl/load-ontology-from-uri pizza-uri)))))

;; (deftest sub-class-of-test
;;   (is (core/sub-class-of (core/get-bottom (owl/load-ontology-from-uri pizza-uri))
;;                          (core/get-top (owl/load-ontology-from-uri pizza-uri))
;;                          pizza-ontology)))

(deftest get-equivalent-classes-test
  (let [equivalent-classes (core/get-equivalent-classes class-americanhot pizza-ontology)]
    (is (and (count equivalent-classes)
             (= class-americanhot-id
                (.toStringID (first equivalent-classes)))))))

(deftest get-super-classes-test
  (is (= #{"http://www.co-ode.org/ontologies/pizza/pizza.owl#Food" "http://www.co-ode.org/ontologies/pizza/pizza.owl#SpicyPizza" "http://www.co-ode.org/ontologies/pizza/pizza.owl#Pizza" "http://www.co-ode.org/ontologies/pizza/pizza.owl#NonVegetarianPizza" "http://www.co-ode.org/ontologies/pizza/pizza.owl#CheeseyPizza" "http://www.co-ode.org/ontologies/pizza/pizza.owl#NamedPizza" "http://www.co-ode.org/ontologies/pizza/pizza.owl#SpicyPizzaEquivalent" "http://www.co-ode.org/ontologies/pizza/pizza.owl#DomainConcept" "http://www.co-ode.org/ontologies/pizza/pizza.owl#InterestingPizza" "http://www.co-ode.org/ontologies/pizza/pizza.owl#MeatyPizza" "http://www.w3.org/2002/07/owl#Thing"}
         (set (map #(.toStringID %)
                   (core/get-super-classes class-americanhot pizza-ontology))))))

(deftest get-sub-classes-test
  (is (= #{"http://www.co-ode.org/ontologies/pizza/pizza.owl#CheeseyVegetableTopping" "http://www.w3.org/2002/07/owl#Nothing" "http://www.co-ode.org/ontologies/pizza/pizza.owl#IceCream"}
         (set (map #(.toStringID %)
                   (core/get-sub-classes class-americanhot pizza-ontology))))))
