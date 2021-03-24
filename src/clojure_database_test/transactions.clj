(ns clojure-database-test.transactions
    (:require [clojure.java.jdbc :as sql]))

(defn insert-implicit
    [data]

    (def postgres-db {
      :dbtype "postgresql"
      :dbname "bd2"
      :port "5432"
      :user "postgres"
      :password ""})
      
      (doseq [line data]
        (println line))

      )

