(ns learn-clojure.core
  (:gen-class))

; At the moment I'm working my way through the problem list here:
; http://www.4clojure.com/

(defn lastInSequence [items]
  (nth items (- (count items) 1)))

(defn nthInSequence [items position]
    ((into [] item) position))

(defn countItemsInSequence [items]
  (reduce
      (fn [acc, item] (+ acc 1))
      0
      items))

(defn reverseSequence [items]
    (def cleanItems (into [] items))
    (def reversed
      (reduce
        (fn [acc, item]
            (def oldPosition (acc 0))
            (def oldReversedList (acc 1))
            (def newPosition (- oldPosition 1))
            (def newReversedList
              (conj oldReversedList (cleanItems newPosition)))
            (vector newPosition newReversedList))
        [(count cleanItems), []]
        cleanItems))
    (reversed 1))

(defn sumOfNumbers [numbers]
  (reduce + numbers))

(defn onlyOdds [numbers]
    (filter odd? numbers))

(defn fibonacci-sequence []
    (defn recursive [a b]
        (lazy-seq (cons a (recursive b (+ a b)))))
    (recursive 1 1))

(defn nFibonacciNumbers [n]
  (take n (fibonacci-sequence)))

(defn adds-100
  "Write a function that takes a number and adds 100 to it."
  [number]
  (+ number 100))

(defn dec-maker
  "Write a function, dec-maker, that works exactly like the function inc-maker except with subtraction"
  [decrement]
  #(- % decrement))

(defn mapset
  "Write a function, mapset, that works like map except the return value is a set"
  [f coll]
  (into #{} (map f coll)))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (lastInSequence [1 2 3 4 5]))
