(ns routes.basic-routes
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [services.basic-service :as basic-service]))

(defroutes router
  (GET "/" [] basic-service/root)
  (GET "/another" [] {:status 200
                   :headers {"Content-Type" "text/html"}
                   :body "<h1>Hello another world!</h1>"})
  (route/not-found "<h1>Not found</h1>"))