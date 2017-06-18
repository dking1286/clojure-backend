(ns clojure-backend.core
  (:require [org.httpkit.server :refer [run-server]]
            [routes.basic-routes :as basic-routes]
            [entities.core :as entities]
            [middleware.core :refer [request-logger]])
  (:gen-class))

(defn apply-middleware
  [middleware handler]
  (middleware handler))

(defn -main
  "Create and run an http server"
  [& args]
  (println "Starting server...")
  (run-server
    (apply-middleware request-logger basic-routes/router)
    {:port 3000}))
