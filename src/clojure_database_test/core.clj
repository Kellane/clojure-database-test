(ns clojure-database-test.core
  (:gen-class))

(load-file "src/transactions/src/make-explicit/explicit.clj")

(defn transaction_explicit []
  (println "Running explicit transaction...")
  (let [start# (System/currentTimeMillis)]
    
    (transaction/insert_explicit "data/data.csv")
    
    (let [amountTime (double (/ (- (System/currentTimeMillis) start#) 1000))]
      (println "Explicit transaction took" amountTime "seconds")
    )
  )
)

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (transaction_explicit)
)