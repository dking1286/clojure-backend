(in-ns 'entities.core)

(defentity todo
  (table :todos)
  (belongs-to user))