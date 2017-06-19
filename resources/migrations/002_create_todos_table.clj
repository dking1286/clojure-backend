(ns migrations.002-create-todos-table
  (:require [clojure.java.jdbc :as jdbc]
            [db.config :refer [db-config]]
            [lib.migration-utils :refer [create-table-with-timestamp-ddl]]))

(defn up []
  (jdbc/execute!
    db-config
    [(create-table-with-timestamp-ddl
       "todos"
       [:id "serial PRIMARY KEY"]
       [:title "text NOT NULL"]
       [:body "text NOT NULL"]
       [:user_id "bigint REFERENCES users(id)"]
       [:is_done "bool NOT NULL DEFAULT false"])]))

(defn down []
  (jdbc/execute!
    db-config
    ["DROP TABLE todos;"]))