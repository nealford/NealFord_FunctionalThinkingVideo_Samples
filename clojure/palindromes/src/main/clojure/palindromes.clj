(defn palindrome? [s]
  (let [sl (.toLowerCase s)]
  (= sl (apply str (reverse sl)))))

(defn find-palindromes [s]
  (filter palindrome? (clojure.string/split s #" ")))

(println (find-palindromes "The quick brown fox jumped over anna the dog"))
(println (find-palindromes "Bob went to Harrah and gambled with Otto and Steve"))
(println (take 1 (find-palindromes "Bob went to Harrah and gambled with Otto and Steve")))