(ns learn-clojure.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (lastInList [1 2 3 4 5]))

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
