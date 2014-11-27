(ns cljweb.hello
  (:require [ring.adapter.jetty :as jetty]))

(defn handler [request]
      {:status 200,
       :headers {"Content-Type" "text/html"}
       :body "<h1>Hello, world.</h1>"})

;; start web server
(defn start-server []
  (jetty/run-jetty handler {:host "localhost",
                            :port 3000}))

(start-server)
