(ns game-of-life.grid
  (:gen-class))

(defn get-cell [grid coords]
  (or (get grid coords) false))

(defn count-living-neighbours [grid [x y]]
  (reduce +
          (for [neighbour-x [x (inc x) (dec x)]
                neighbour-y [y (inc y) (dec y)]
                :when (not (and (= neighbour-x x) (= neighbour-y y)))]
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
