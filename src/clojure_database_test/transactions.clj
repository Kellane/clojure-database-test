(ns transactions
    (:require
        [clojure.java.jdbc :as jdbc])
    (:gen-class))

(defn insert [row, t-conn]
    (jdbc/insert! t-conn :product {:eid (Integer. (get row 0)) :description (get row 1)}))

(defn insert-explicit [data, dbspec]
    (jdbc/with-db-transaction [t-conn dbspec]
        (try
            (doseq [row data]
                (insert row t-conn))
            
            (catch Exception e
                (throw e)))))
