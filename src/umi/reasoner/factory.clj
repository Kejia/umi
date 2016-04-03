(ns umi.reasoner.factory
  (:import (org.semanticweb.owlapi.reasoner OWLReasoner OWLReasonerFactory)
           (org.semanticweb.owlapi.reasoner SimpleConfiguration)
           (org.semanticweb.HermiT Reasoner)))

(defn produce
  [ontology & {:keys [reasoner-type]
               :or {reasoner-type :hermit}}]
  {:pre [(keyword? reasoner-type)]}
  (let [reasoner-factory (case reasoner-type
                           (org.semanticweb.HermiT.Reasoner$ReasonerFactory.))]
    (.createNonBufferingReasoner reasoner-factory ontology)))
