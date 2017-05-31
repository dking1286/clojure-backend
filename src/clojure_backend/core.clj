(ns clojure-backend.core
  (:require [org.httpkit.server :refer [run-server]]
            [routes.basic-routes :as basic-routes]
            [entities.core :as entities])
  (:gen-class))

(defn -main
  "Create and run an http server"
  [& args]
  (println "Starting server...")
  (run-server basic-routes/router {:port 3000}))
