(ns clojure_database_test.transactions.connection
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
