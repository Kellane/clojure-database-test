(ns clojure-database-test.transactions
    (:require [clojure.java.jdbc :as sql]))

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
       
        (sql/insert! postgres-db
            :product {:eid (Integer. eid) :description description}))

      )

