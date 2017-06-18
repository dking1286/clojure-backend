(in-ns 'entities.core)

(defentity user
  (table :users)
  (has-many todo))