(ns test.clojure.NumberClassifierTest
  (:use clojure.test)
  (:use main.clojure.NumberClassifier))


(deftest test-is-factor
  (is (is-factor? 1 10))
  (is (is-factor? 5 25))
  (is (not (is-factor? 7 20)))
  (is (is-factor? 25 25)))

(deftest test-factors
  (is (= '(1 2 3 6) (factors 6)))
  (is (= '(1 2 4 8 16) (factors 16))))

(deftest test-sum-of-factors
  (is (= 12 (sum-factors 6)))
  (is (= 1 (sum-factors 1)))
  (is (= (* 28 2) (sum-factors 28))))

(deftest test-perfection
  (is (perfect? 6))
  (is (perfect? 28))
  (is (perfect? 496))
  (is (perfect? 8128))
  (is (not (perfect? 12)))
  (is (not (perfect? 100))))

(deftest abundance
  (is (abundant? 12))
  (is (abundant? 18))
  (is (not (abundant? 13))))

(deftest deficiency
  (is (deficient? 3))
  (is (deficient? 5))
  (is (deficient? 9))
  (is (deficient? 10))
  (is (not (deficient? 12))))

(run-tests)
