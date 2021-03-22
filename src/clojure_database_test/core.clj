(ns clojure-database-test.core
    (:gen-class)
    (:require [clojure.data.csv :as csv]
              [clojure.java.io :as io]))

(load-file "src/transactions/src/transaction.clj")

(defn -main
    "I don't do a whole lot ... yet."
    [& args]

    (def file-name (nth args 0))

    (println (transaction/now))

    (with-open [file (io/reader file-name)]
        (doseq [line (csv/read-csv file)]
            (println line)))
    
    )
