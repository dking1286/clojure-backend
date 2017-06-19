(ns config.migrate-config
  (:require [clojure.java.jdbc :as jdbc]
            [db.config :refer [db-config]]))

;;; This file needs to be in this location for Drift. Do not move it.

(declare
  init-migration-versioning
  get-current-db-version
  update-db-version
  versions-list->current-version)

(defn migrate-config []
  {:directory       "/resources/migrations"
   :init            init-migration-versioning
   :current-version get-current-db-version
   :update-version  update-db-version})

(defn init-migration-versioning [& args]
  (jdbc/execute! db-config
    ["CREATE TABLE IF NOT EXISTS drift_migrations (id serial PRIMARY KEY, version bigint NOT NULL);"]))

(defn get-current-db-version []
  (-> (jdbc/query db-config
        ["SELECT version FROM drift_migrations ORDER BY id DESC;"])
      versions-list->current-version))

(defn update-db-version [new-version]
  (jdbc/insert! db-config "drift_migrations" {:version new-version}))

(defn versions-list->current-version [versions-list]
    (if (= 0 (count versions-list))
      0
      (-> versions-list first :version)))