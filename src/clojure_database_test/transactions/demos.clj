(ns clojure_database_test.transactions.demos
  (:require
    [clojure.data.csv :as csv]
    [clojure.java.io :as io]
    [clojure.java.jdbc :as jdbc]
    [clojure_database_test.transactions.connection :as conn]
  )
  (:gen-class))

; print row
(defn print_line [row] 
  (println (get row 0) (get row 1))
)

; read cvs and print rows
(defn read-values [filename]
  (with-open [in-file (io/reader filename)]
    (doall
     (map print_line (csv/read-csv in-file)))
  )
)

; demo select
(defn select[] 
  (println 
    (jdbc/query conn/dbspec-explicit ["SELECT * from product;"])
  )
)