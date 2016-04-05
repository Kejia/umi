(ns umi.ontology.util
  (:import (org.semanticweb.owlapi.apibinding OWLManager)
           (org.semanticweb.owlapi.io StringDocumentSource)
           (org.semanticweb.owlapi.model OWLOntologyManager IRI)
           (uk.ac.manchester.cs.owl.owlapi OWLClassImpl)))

(defn uri->iri
  [uri]
  (IRI/create uri))

(defn uri->class-exp
  [uri]
  (OWLClassImpl. (uri->iri uri)))

(defn owl-node->set
  [owl-node]
  (-> owl-node
      (.getFlattened)
      set))

(defn owl-nodeset->set
  [owl-node]
  (-> owl-node
      (.getEntities)
      set))

