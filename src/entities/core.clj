(ns entities.core
  (:require [korma.core :refer :all]
            [db.core :refer [db]]))

(declare user todo)

(load "user")
(load "todo")