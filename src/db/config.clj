(ns db.config
  (:require [environ.core :refer [env]]
            [korma.db :refer [postgres]]))

(def db-config
  (-> (env :db-config)
      read-string
      postgres))