(ns clojure-database-test.transactions
    (:require [java-time :as t])
    (:gen-class))

(defn now
    "Returns the current datetime"
    []
    (t/instant))

(defn insert-implicit
    "Performs a series of inserts."
    [data]

    (Thread/sleep 100)
    (doseq [line data]
        (get line 0) (get line 1)))

(defn insert-explicit
    "Performs a series of inserts."
    [data]
    
    (Thread/sleep 1000)
    (doseq [line data]
        (get line 0) (get line 1)))