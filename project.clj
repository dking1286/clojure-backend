(defproject clojure-backend "0.1.0-SNAPSHOT"
  :description "A basic ToDo app in Clojure"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [http-kit "2.2.0"]
                 [compojure "1.6.0"]
                 [korma "0.4.0"]
                 [org.postgresql/postgresql "9.2-1002-jdbc4"]
                 [ragtime "0.6.0"]
                 [clj-time "0.13.0"]]
  :main ^:skip-aot clojure-backend.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}}
  :aliases {"migrate:make" ["exec" "scripts/migrate:make.clj"]})
