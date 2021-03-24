(ns clojure-database-test.core
  (:require [clojure.data.csv :as csv]
            [clojure.java.io :as io]
            [clojure-database-test.transactions :as transactions])
  (:gen-class))

(def dbspec-explicit {
    :dbtype "postgresql"
    :dbname "bd2"
    :user "postgres"
    :port 5432
    :password ""
    :auto-commit false})

(defn -main
  "I don't do a whole lot ... yet."
  
  [& args]
)
