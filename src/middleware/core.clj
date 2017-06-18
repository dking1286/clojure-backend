(ns middleware.core)

(defn apply-middleware
  [& args]
  (let [request-handler (last args)
        middleware (apply comp (butlast args))]
    (middleware request-handler)))

(load "request_logger")