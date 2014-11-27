(defproject cljweb "0.1.0-SNAPSHOT"
  :description "Clojure Web Project"
  :url "https://github.com/michaelliao/cljweb"
  :license {:name "Apache License"
            :url "http://www.apache.org/licenses/LICENSE-2.0.html"}
  :dependencies [
                 [org.clojure/clojure "1.6.0"]
                 [org.clojure/data.json "0.2.5"]
                 [org.clojure/java.jdbc "0.3.6"]
                 [mysql/mysql-connector-java "5.1.25"]
                 [korma "0.3.0"]
                 [selmer "0.7.2"]
                 [ring "1.3.1"]
                 [ring/ring-json "0.3.1"]
                 [compojure "1.2.1"]]
  :main cljweb.web
  :aot [cljweb.web]
  :plugins [[lein-ring "0.8.13"]]
  :ring {:handler cljweb.web/app
         :auto-reload? true
         :auto-refresh? true
         })
