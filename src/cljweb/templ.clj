(ns cljweb.templ)

;; using selmer templating system
(use 'selmer.parser)

(selmer.parser/cache-off!)

(selmer.parser/set-resource-path! (clojure.java.io/resource "templates"))

(render-file "test.html" {:title "Selmer Template",
                          :name "Michael",
                          :now (new java.util.Date)})
