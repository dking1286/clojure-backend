(ns migrations.001-create-users-table
  (:require [clojure.java.jdbc :as jdbc]
            [db.config :refer [db-config]]))

(defn up []
  (jdbc/execute!
    db-config
    [(jdbc/create-table-ddl
        "users"
        [:id "serial PRIMARY KEY"]
        [:first_name "varchar(128) NOT NULL"]
        [:last_name "varchar(128) NOT NULL"]
        [:email "varchar(128) NOT NULL UNIQUE"]
        [:password "varchar(128) NOT NULL"])]))

(defn down []
  (jdbc/execute!
    db-config
    ["DROP TABLE users;"]))