(ns clojure_database_test.transactions.explicit
  (:require
    [clojure.data.csv :as csv]
    [clojure.java.io :as io]
    [clojure.java.jdbc :as jdbc]
    [clojure_database_test.transactions.connection :as conn]
  )
  (:gen-class))

; Get a row and db connection
; and performs a insert.
(defn insert [row, t-conn]
  (jdbc/insert! t-conn :product {:eid (Integer. (get row 0)) :description (get row 1)})
)

; insert with transactions
(defn insert_with_transaction [filename]
  (jdbc/with-db-transaction [t-conn conn/dbspec-explicit]
    (try
      (with-open [in-file (io/reader filename)]
        (doall
          (map #(insert %1, t-conn) (csv/read-csv in-file)))
      )
      (println "Insert succeed :)")
      (catch Exception e
        (println "Insert failed ;(")
        (throw e)
      )
    )
  )
)