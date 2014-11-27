(ns cljweb.core)

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))

(println (foo "Clojure:"))

;; 定义自然数序列
(defn natuals []
  (iterate inc 1))

;; 定义奇数序列
(defn odds []
  (filter odd? (natuals)))

;; 定义偶数序列
(defn evens []
  (filter even? (natuals)))

;; 定义斐波那契数列
(defn fib []
  (defn fib-iter [a b]
    (lazy-seq (cons a (fib-iter b (+ a b)))))
  (fib-iter 0 1))

;; 打印前10个数
(println (take 10 (natuals)))
(println (take 10 (odds)))
(println (take 10 (evens)))
(println (take 10 (fib)))

;; 打印1x2, 2x3, 3x4...
(println (take 10 (map * (natuals)
                         (drop 1 (natuals)))))
