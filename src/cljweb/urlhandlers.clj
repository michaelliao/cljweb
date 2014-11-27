(ns cljweb.urlhandlers
  (:use [compojure.core :only [GET POST PUT DELETE defroutes]]
        [compojure.route :only [not-found]]
        korma.db
        korma.core
        [ring.util.response :only [response]]))

(defdb korma-db (mysql {:db "test",
                        :host "localhost",
                        :port 3306,
                        :user "www",
                        :password "www"}))

(declare courses)

(defentity courses)

(defn get-course [id]
  (let [cs (select courses
                   (where {:id id}))]
    (if (empty? cs)
      nil
      (first cs))))

(defn get-courses []
  (select courses
          (where {:online true})
          (order :name :asc)))

(defn create-course! [c]
  (println "create course:" c)
  (insert courses
    (values c)))

(defn delete-course! [id]
  (println "delete course:" id)
  (delete courses
          (where {:id id}))) 

(defn init-courses! []
  (if (empty? (get-courses))
    (let [cs [{ :id "c-101", :name "Clojure", :price 19.9, :online true, :days 20 },
              { :id "c-102", :name "Java",    :price 9.9,  :online true, :days 15 },
              { :id "c-103", :name "Python",  :price 15.0, :online true, :days 18 }]]
      (println "init courses...")
      (dorun
        (map create-course! cs)))))

(init-courses!)

(defroutes app-routes

  (GET "/" request (response {:template "index.html",
                              :model {:id "u-20141123-9091",
                                      :name "Michael"
                                      :courses (get-courses)}}))

  (POST "/courses" [] (fn [request]
                        (let [params (:params request)]
                          (create-course! {:id (str "c-" (System/currentTimeMillis)),
                                           :name (:name params),
                                           :price 8.8,
                                           :online true,
                                           :days 7})
                          (response (str "You have created course: " (:name params))))))

  (GET "/rest/courses" [] (response { :courses (get-courses) }))

  (POST "/rest/courses" [] (fn [request]
                             (let [c (:body request)
                                   id (str "c-" (System/currentTimeMillis))]
                               (create-course! (assoc c :id id, :online true,))
                               (response (get-course id)))))

  (GET "/rest/courses/:id" [id] (response (get-course id)))

  (DELETE "/rest/courses/:id" [id] (do
                                     (delete-course! id)
                                     (response {:id id})))

  (not-found "<h1>page not found!</h1>"))
