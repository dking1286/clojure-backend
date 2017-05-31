(ns services.basic-service)

(defn root
  [req]
  (println req)
  "<h1>Hello world!</h1>")