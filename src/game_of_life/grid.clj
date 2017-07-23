(ns game-of-life.grid
  (:gen-class))

(defn random-boolean []
  (zero? (rand-int 2)))

(defn random-booleans []
  (lazy-seq (repeatedly random-boolean)))

(defn generate-grid-coords [size]
  (for [y (range size) x (range size)] [x y]))

(defn random-grid [size]
  (zipmap (generate-grid-coords size) (random-booleans)))
