(ns clojure-database-test.core
  (:require 
    [clojure-database-test.transactions]
    [clojure.java.io :as io]
    [clojure.java.jdbc :as jdbc]
    [clojure.data.csv :as csv]
  )
  (:gen-class))

(def dbspec-explicit {
  :dbtype "postgresql"
  :dbname "bd2"
  :user "postgres"
  :port 5432
  :password ""
  :auto-commit false
})

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (with-open [data (io/reader input-file)]
    ;(.setAutocommit db false) n funcionou
    (transactions/insert-explicit (csv/read-csv data) dbspec-explicit)
  )
)