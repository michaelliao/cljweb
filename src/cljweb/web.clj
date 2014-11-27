(ns cljweb.web
  (:require
    [ring.adapter.jetty :as jetty]
    [ring.middleware.cookies :as cookies]
    [ring.middleware.params :as params]
    [ring.middleware.keyword-params :as keyword-params]
    [ring.middleware.json :as json]
    [ring.middleware.file :as file]
    [ring.middleware.resource :as resource]
    [ring.middleware.stacktrace :as stacktrace]
    [cljweb.templating :as templating]
    [cljweb.urlhandlers :as urlhandlers])
  (:gen-class))

(def app
  (-> urlhandlers/app-routes
      (resource/wrap-resource (clojure.java.io/resource "resources")) ;; static resource
      templating/wrap-template-response  ;; render template
      json/wrap-json-response            ;; render json
      json/wrap-json-body                ;; request json
      stacktrace/wrap-stacktrace-web     ;; wrap-stacktrace-log
      keyword-params/wrap-keyword-params ;; convert parameter name to keyword
      cookies/wrap-cookies ;; get / set cookies
      params/wrap-params   ;; query string and url-encoded form
  ))

;; start web server
(defn start-server []
  (jetty/run-jetty app {:host "localhost",
                        :port 3000
                        }))

(defn -main [& args]
  (if (= "start" (first args))
    (start-server)))
