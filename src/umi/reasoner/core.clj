(ns umi.reasoner.core
  (:require [umi.reasoner.factory :as factory]
            [umi.ontology.util :as ontology-util])
  (:import (org.semanticweb.owlapi.reasoner OWLReasoner OWLReasonerFactory)
           (org.semanticweb.owlapi.reasoner SimpleConfiguration)
           (org.semanticweb.HermiT Reasoner)))

(defn consistent?
  [ontology]
  (.isConsistent (factory/produce ontology)))

(defn satisfiable?
  [class-exp ontology]
  (.isSatisfiable (factory/produce ontology) class-exp))

(defn get-top
  [ontology]
  (.getTopClassNode (factory/produce ontology)))

(defn get-bottom
  [ontology]
  (.getBottomClassNode (factory/produce ontology)))

(defn entailed?
  [owl-axiom ontology]
  (.isEntailed (factory/produce ontology) owl-axiom))

(defn get-equivalent-classes
  [class-exp ontology]
  (-> (factory/produce ontology)
      (.getEquivalentClasses class-exp)
      ontology-util/owl-nodeset->set))

(defn get-super-classes
  [class-exp ontology & {:keys [directed]
                         :or {directed false}}]
  (-> (factory/produce ontology)
      (.getSuperClasses class-exp directed)
      ontology-util/owl-node->set))

(defn get-sub-classes
  [class-exp ontology & {:keys [directed]
                         :or {directed false}}]
  (-> (factory/produce ontology)
      (.getSubClasses class-exp directed)
      ontology-util/owl-node->set))
