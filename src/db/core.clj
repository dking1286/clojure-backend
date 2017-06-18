(ns db.core
  (:require [korma.db :refer :all]
            [environ.core :refer [env]]))

(defdb db (postgres (env :db-config)))