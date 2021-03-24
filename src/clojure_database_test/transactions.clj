(ns clojure-database-test.transactions
    (:require [clojure.java.jdbc :as jdbc]))

(defn insert-implicit
    [data]

    (def postgres-db {
    	:dbtype "postgresql"
      	:dbname "db2"
      	:port 5432
      	:user "postgres"
      	:password "123"})
      
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
