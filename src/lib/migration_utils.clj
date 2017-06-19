(ns lib.migration-utils
  (:require [clojure.java.jdbc :as jdbc]))


(defn create-table-with-timestamp-ddl [tablename & columns]
  (apply
    jdbc/create-table-ddl
    (concat [tablename [:created_at "timestamp DEFAULT NOW()"]] columns)))