(ns umi.ontology.core
  (:require [umi.ontology.util :as util])
  (:import (org.semanticweb.owlapi.apibinding OWLManager)
           (org.semanticweb.owlapi.io StringDocumentSource)
           (org.semanticweb.owlapi.model OWLOntology)))

(defn- load-ontology
  [datasource]
  (let [manager (OWLManager/createOWLOntologyManager)]
    (.loadOntologyFromOntologyDocument manager datasource)))

(defn load-ontology-from-desc
  [ontology-desc]
  (let [datasource (StringDocumentSource. ontology-desc)]
    (load-ontology datasource)))

(defn load-ontology-from-uri
  [uri-to-ontology]
  (let [datasource (util/uri->iri uri-to-ontology)]
    (load-ontology datasource)))

(defn get-all-axiom
  [ontology]
  (set (.. ontology (getAxioms))))
