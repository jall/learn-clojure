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

(defn empty-grid [size]
    (zipmap (generate-grid-coords size) (repeat false)))

(defn get-cell [grid coords]
  (or (get grid coords) false))

(defn count-living-neighbours [grid [x y]]
  (reduce +
      (for [neighbour-x [x (inc x) (dec x)]
            neighbour-y [y (inc y) (dec y)]
            :when (and (not= neighbour-x x) (not= neighbour-y y))]
          (if (get-cell grid [neighbour-x neighbour-y]) 1 0))))

(defn still-alive? [grid [coords is-alive]]
  (def neighbour-count (count-living-neighbours grid coords))
  (if is-alive
    (or (= neighbour-count 3) (= neighbour-count 2))
    (= neighbour-count 3)))

(defn update-cell [grid cell]
  {(first cell) (still-alive? grid cell)})

(defn next-iteration [grid]
  (into {}
        (for [cell grid]
             (update-cell grid cell))))
