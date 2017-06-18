(ns config.migrate-config
  (:require [clojure.java.jdbc :as sql]
            [environ.core :refer [env]]
            [korma.db :refer [postgres]]))

(def db-config (postgres (env :db-config))

(defn migrate-config []
  {:directory       "/resources/migrations"
   :init            init-migration-versioning
   :current-version get-current-db-version
   :update-version  update-db-version})

(defn init-migration-versioning []
  (sql/query db-config
    ["CREATE TABLE IF NOT EXISTS drift_migrations (id serial PRIMARY KEY, version biginteger NOT NULL)"]))

(defn get-current-db-version []
  (-> (sql/query db-config
        ["SELECT version FROM drift_migrations ORDER BY version DESC"])
      versions-list->current-version))

(defn update-db-version [new-version]
  (sql/query db-config
    ["INSERT INTO drift_migrations(version) VALUES (?)" new-version]))

(defn versions-list->current-version [versions-list]
    (if (= 0 (count versions-list)) 0 (first versions-list)))