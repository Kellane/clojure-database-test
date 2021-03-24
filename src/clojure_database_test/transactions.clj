(ns clojure-database-test.transactions
    (:require [clojure.java.jdbc :as jdbc]))

(def dbspec-explicit {
    :dbtype "postgresql"
    :dbname "bd2"
    :user "postgres"
    :port 5432
    :password ""
    :auto-commit false})

(def dbspec-implicit {
    :dbtype "postgresql"
    :dbname "db2"
    :port 5432
    :user "postgres"
    :password ""})

(defn insert-implicit
    [data]
      
    (doseq [row data]
    	(def eid (get row 0))
        (def description (get row 1))
       
        (jdbc/insert! postgres-db
            :product {:eid (Integer. eid) :description description})))

(defn insert [row, t-conn]
    (jdbc/insert! t-conn :product {:eid (Integer. (get row 0)) :description (get row 1)}))

(defn insert-explicit [data, dbspec]
    (jdbc/with-db-transaction [t-conn dbspec]
        (try
            (doseq [row data]
                (insert row t-conn))
            
            (catch Exception e
                (throw e)))))
