(ns transaction
  (:require
    [clojure.data.csv :as csv]
    [clojure.java.io :as io]
    [clojure.java.jdbc :as jdbc]
  )
  (:gen-class))

; http://clojure.github.io/java.jdbc/#clojure.java.jdbc.spec
; previous link says auto-commit goes here
(def dbspec-explicit {
  :dbtype "postgresql"
  :dbname "bd2"
  :user "postgres"
  :port 5432
  :password ""
  :auto-commit false
})

; Get a row and db connection
; and performs a insert.
(defn insert [row, t-conn]
  (jdbc/insert! t-conn :product {:eid (Integer. (get row 0)) :description (get row 1)})
)

; insert with transactions
(defn insert_explicit [filename]
  (jdbc/with-db-transaction [t-conn dbspec-explicit]
    (try
      (with-open [in-file (io/reader filename)]
        (doall
          (map #(insert %1, t-conn) (csv/read-csv in-file)))
      )
      (println "Insert explicit succeed :)")
      (catch Exception e
        (println "Insert explicit failed ;(")
        (throw e)
      )
    )
  )
)