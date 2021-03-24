(ns clojure-database-test.core
    (:gen-class)
    (:require [clojure.data.csv :as csv]
              [clojure.java.io :as io]
              [clojure-database-test.transactions :as transactions]))

(defn -main
    "I don't do a whole lot ... yet."
    [& args]

    (def file-name (nth args 0))

    ;; Clear database to prevent errors
    (transactions/clean-db)

    ;; Measures time of file reading
    (def start-time (System/nanoTime))

    (with-open [file (io/reader file-name)]
        (doall (csv/read-csv file)))

    (def file-reading-time
        (- (System/nanoTime) start-time))

    ;; Performs implicit insertions and measures time
    (def start-time (System/nanoTime))

    (with-open [file (io/reader file-name)]
        (transactions/insert-implicit 
            (doall (csv/read-csv file))))

    (def implicit-insertion-time
        (- (System/nanoTime) start-time))

    ;; Clear database to prevent errors
    (transactions/clean-db)

    ;; Performs explicit insertions and measures time
    (def start-time (System/nanoTime))

    (with-open [file (io/reader file-name)]
        (transactions/insert-explicit 
            (doall (csv/read-csv file))))

    (def explicit-insertion-time
        (- (System/nanoTime) start-time))

    (println "Implicit:" (/ implicit-insertion-time 1000000000.0) "sec")
    (println "Explicit:" (/ explicit-insertion-time 1000000000.0) "sec"))
