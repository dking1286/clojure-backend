(in-ns 'middleware.core)

(defn request-logger
  [handler]
  (fn [req]
    (println req)
    (handler req)))