(ns main.clojure.NumberClassifier)

(defn is-factor? [factor number]
  (zero? (rem number factor)))

(defn factors [number]
  (filter #(is-factor? % number) (range 1 (inc number))))

(defn sum-factors [number]
    (reduce + (factors number)))

(defn perfect? [number]
  (= (* 2 number) (sum-factors number)))

(defn abundant? [number]
  (< (* 2 number) (sum-factors number)))

(defn deficient? [number]
  (> (* 2 number) (sum-factors number)))
