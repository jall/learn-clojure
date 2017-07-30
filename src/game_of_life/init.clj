(ns game-of-life.init
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

(defn block-laying-switch-engine-grid [size]
  (def mid (midpoint size))
  (def x (first mid))
  (def y (second mid))
  (merge (empty-grid size)
         {[(- x 2) (- y 2)] true}
         {[(- x 1) (- y 2)] true}
         {[x (- y 2)] true}
         {[(+ x 2) (- y 2)] true}

         {[(- x 2) (- y 1)] true}

         {[(+ x 1) y] true}
         {[(+ x 2) y] true}

         {[(- x 1) (+ y 1)] true}
         {[x (+ y 1)] true}
         {[(+ x 2) (+ y 1)] true}

         {[(- x 2) (+ y 2)] true}
         {[x (+ y 2)] true}
         {[(+ x 2) (+ y 2)] true}))
