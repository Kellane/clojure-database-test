(ns clojure-database-test.core
  (:require
    [clojure_database_test.transactions.explicit :as explicit]
  )
  (:gen-class))

(defn explicit_transaction_runtime []
  (println "Running explicit transaction...")
  (let [start# (System/currentTimeMillis)]
    
    (explicit/insert_with_transaction "data/data.csv")
    
    (let [amountTime (double (/ (- (System/currentTimeMillis) start#) 1000))]
      (println "Explicit transaction took" amountTime "seconds")
    )
  )
)

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (explicit_transaction_runtime)
)