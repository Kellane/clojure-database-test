(ns clojure-database-test.core
  (:require [clojure.data.csv :as csv]
            [clojure.java.io :as io]
            [clojure-database-test.transactions :as transactions]))
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  
  [& args]

  (def file-name (nth args 0))
  (println file-name)

  ;; (with-open [file (io/reader file-name)]
  ;;   (transactions/insert-implicit (doall (csv/read-csv file)))))