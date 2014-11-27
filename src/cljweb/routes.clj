(ns cljweb.routes
  (:use [compojure.core]
        [compojure.route :only [not-found]]
        [ring.adapter.jetty :as jetty]))

(defroutes app-routes

  (GET "/" [] "<h1>Index page</h1>")

  (GET "/learn/:lang" [lang] (str "<h1>Learn " lang "</h1>"))

  (not-found "<h1>page not found!</h1>"))

;; start web server
(defn start-server []
  (jetty/run-jetty app-routes {:host "localhost",
                               :port 3000}))

(start-server)
