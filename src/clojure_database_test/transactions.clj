(ns clojure-database-test.transactions
    (:require [clojure.java.jdbc :as jdbc]))

(def dbspec-implicit {
    :dbtype "postgresql"
    :port 5432
    :dbname "db2"
    :user "clojure_user"
    :password "123456"})

(def dbspec-explicit {
    :dbtype "postgresql"
    :port 5432
    :dbname "db2"
    :user "clojure_user"
    :password "123456"
    :auto-commit false})

(defn insert
    "Inserts a single row into database defined by db-connection." 
    [row, db-connection]

    (jdbc/insert! db-connection 
        :product {:eid (get row 0) :description (get row 1)}))

(defn insert-implicit
    "Inserts every row from data into a database, using
    implicit transactions."
    [data]

    (try
        (doseq [row data]
            (def eid (Integer. (get row 0)))
            (def description (get row 1))
    
            (insert [eid description] dbspec-implicit))

        (catch Exception e
            (constantly nil))))

(defn insert-explicit
    "Inserts every row from data into a database, using
    explicit transactions."
    [data]

    (jdbc/with-db-transaction [db-connection dbspec-explicit]
        (try
            (doseq [row data]
                (def eid (Integer. (get row 0)))
                (def description (get row 1))
                
                (insert [eid description] db-connection))

            (catch Exception e
                (constantly nil)))))

(defn clean-db
    "Clears database."
    []
    (jdbc/delete! dbspec-implicit :product []))
