(ns cljweb.db
  (:use korma.db
        korma.core))

(defdb korma-db (mysql {:db "test",
                        :host "localhost",
                        :port 3306,
                        :user "www",
                        :password "www"}))

(declare courses)
(defentity courses)

(defn get-courses []
  (select courses
          (where {:online false})
          (order :name :asc)))

(defn create-course! [c]
  (println "create course:" c)
  (insert courses
    (values c)))

(defn init-courses! []
  (if (empty? (get-courses))
    (let [cs [{ :id "s-201", :name "SQL", :price 99.9, :online false, :days 30 },
              { :id "s-202", :name "PHP", :price 69.9, :online false, :days 15 },
              { :id "s-203", :name "F#",  :price 80.0, :online false, :days 20 }]]
      (println "init courses...")
      (dorun
        (map create-course! cs)))))

(defn -main [& args]
  (init-courses!)
  (println (get-courses)))

(-main)
