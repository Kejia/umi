(ns umi.reasoner.core-test
  (:require [umi.ontology.core :as ontology]
            [umi.ontology.core-test :as ontology-test]
            [umi.ontology.util :as util]
            [umi.reasoner.core :as reasoner]
            [clojure.test :refer :all]))

(deftest consistent?-test
  (is (reasoner/consistent? ontology-test/pizza-ontology)))

(deftest satisfiable?-test
  (is (reasoner/satisfiable? ontology-test/class-americanhot ontology-test/pizza-ontology)))

(deftest get-top-test
  (is (.isTopNode (reasoner/get-top ontology-test/pizza-ontology))))

(deftest get-bottom-test
  (is (.isBottomNode (reasoner/get-bottom ontology-test/pizza-ontology))))

(deftest entailed?-test
  (is (every? identity
              (pmap #(reasoner/entailed? % ontology-test/pizza-ontology)
                    (set (ontology/get-all-axiom ontology-test/pizza-ontology))))))

(deftest get-equivalent-classes-test
  (let [equivalent-classes (reasoner/get-equivalent-classes ontology-test/class-americanhot ontology-test/pizza-ontology)]
    (is (and (count equivalent-classes)
             (= ontology-test/class-americanhot-id
                (.toStringID (first equivalent-classes)))))))

(deftest get-super-classes-test
  (is (= #{"http://www.co-ode.org/ontologies/pizza/pizza.owl#Food" "http://www.co-ode.org/ontologies/pizza/pizza.owl#SpicyPizza" "http://www.co-ode.org/ontologies/pizza/pizza.owl#Pizza" "http://www.co-ode.org/ontologies/pizza/pizza.owl#NonVegetarianPizza" "http://www.co-ode.org/ontologies/pizza/pizza.owl#CheeseyPizza" "http://www.co-ode.org/ontologies/pizza/pizza.owl#NamedPizza" "http://www.co-ode.org/ontologies/pizza/pizza.owl#SpicyPizzaEquivalent" "http://www.co-ode.org/ontologies/pizza/pizza.owl#DomainConcept" "http://www.co-ode.org/ontologies/pizza/pizza.owl#InterestingPizza" "http://www.co-ode.org/ontologies/pizza/pizza.owl#MeatyPizza" "http://www.w3.org/2002/07/owl#Thing"}
         (set (map #(.toStringID %)
                   (reasoner/get-super-classes ontology-test/class-americanhot ontology-test/pizza-ontology))))))

(deftest get-sub-classes-test
  (is (= #{"http://www.co-ode.org/ontologies/pizza/pizza.owl#CheeseyVegetableTopping" "http://www.w3.org/2002/07/owl#Nothing" "http://www.co-ode.org/ontologies/pizza/pizza.owl#IceCream"}
         (set (map #(.toStringID %)
                   (reasoner/get-sub-classes ontology-test/class-americanhot ontology-test/pizza-ontology))))))

(deftest get-unsatisfiable-classes-test
  (let [unsat-class (reasoner/get-unsatisfiable-classes ontology-test/pizza-ontology)]
    (is (= 3
           (count unsat-class)))
    (is (unsat-class (-> (reasoner/get-bottom ontology-test/pizza-ontology)
                         util/owl-node->set
                         first)))))
