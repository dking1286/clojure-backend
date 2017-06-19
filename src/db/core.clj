(ns db.core
  (:require [korma.db :refer :all]
            [db.config :refer [db-config]]))

(defdb db db-config)