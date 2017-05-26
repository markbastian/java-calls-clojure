(ns interop.scripts)

(defn add [a b] (+ a b))

(defn nth-fib [n]
  (nth (map second (iterate (fn [[a b]] [b (+ a b)]) [0N 1N])) n))
