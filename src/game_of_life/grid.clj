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

(defn full-grid [size]
    (zipmap (generate-grid-coords size) (repeat true)))

(defn empty-grid [size]
    (zipmap (generate-grid-coords size) (repeat false)))

(defn midpoint [size]
  [(quot size 2) (quot size 2)])

(defn r-pentomino-grid [size]
  (def mid (midpoint size))
  (def x (first mid))
  (def y (second mid))
  (merge (empty-grid size)
         {[(dec x) y] true}
         {[x (dec y)] true}
         {[x y] true}
         {[x (inc y)] true}
         {[(inc x) (dec y)] true}))

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
