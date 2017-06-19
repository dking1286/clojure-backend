(defproject clojure-backend "0.1.0-SNAPSHOT"
  :description "A basic ToDo app in Clojure"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [environ "1.1.0"]
                 [http-kit "2.2.0"]
                 [compojure "1.6.0"]
                 [korma "0.4.0"]
                 [org.postgresql/postgresql "9.2-1002-jdbc4"]
                 [drift "1.5.3"]
                 [clj-time "0.13.0"]]

  :dev-dependencies [[drift "1.5.3"]]

  :plugins [[lein-environ "1.1.0"]
            [drift "1.5.3"]]

  :main ^:skip-aot clojure-backend.core

  :target-path "target/%s"

  :profiles 
  {:uberjar {:aot :all}
   :test {:ragtime {:database "jdbc:postgresql://localhost:5432/clojure_backend_test?user=dking&password=helloworld"}}})

;;; Where do I put db credential info so that it is accessible everywhere?
;;; Answer: Use the "environ" package
