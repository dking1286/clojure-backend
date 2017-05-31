(ns db.core
  (:require [korma.db :refer :all]
            [db.environments :as environments]))

(defdb db (postgres environments/development))