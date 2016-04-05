(ns umi.reasoner.core
  (:require [umi.reasoner.factory :as factory])
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
  (set (.getEntities (.getEquivalentClasses (factory/produce ontology) class-exp))))

(defn get-super-classes
  [class-exp ontology & {:keys [directed]
                         :or {directed false}}]
  (set (.getFlattened (.getSuperClasses (factory/produce ontology) class-exp directed))))

(defn get-sub-classes
  [class-exp ontology & {:keys [directed]
                         :or {directed false}}]
  (set (.getFlattened (.getSubClasses (factory/produce ontology) class-exp directed))))
