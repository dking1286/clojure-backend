(ns entities.core
  (:require [korma.core :refer :all]
            [db.core :refer [db]]))

(declare user todo)

(defentity user
  (table :users)
  (has-many todo))

(defentity todo
  (table :todos)
  (belongs-to user))