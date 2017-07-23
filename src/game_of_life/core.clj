(ns game-of-life.core
  (:gen-class)
  (:require [game-of-life.grid
             :refer [random-grid
                     empty-grid
                     r-pentomino-grid
                     next-iteration]]
            [quil.core :as q]
            [quil.middleware :as m]))

(def scale 10)
(def grid-size 80)
(def grid-size-px (* grid-size scale))

(def dead-colour [240 240 240])
(def separator-colour [220 220 220])
(def alive-colour [90 90 90])

(defn draw-square [x y is-alive]
  (apply q/fill (if is-alive alive-colour dead-colour))
  (q/rect
   (* x scale)
   (* y scale)
   scale
   scale))

(defn draw-grid [grid]
  (doseq [[coords is-alive] grid
          :let [x (first coords) y (second coords)]]
    (draw-square x y is-alive)))

(defn setup []
  (q/frame-rate 5)
  (r-pentomino-grid grid-size))

(defn update-state [state]
    (next-iteration state))

(defn draw-state [state]
    ; Clear the sketch first.
  (apply q/background dead-colour)
  (apply q/stroke separator-colour)
  (draw-grid state))

(q/defsketch drawing
             :title "Game of life"
             :size [grid-size-px grid-size-px]
             :setup setup
             :update update-state
             :draw draw-state
             :features [:keep-on-top]
             :middleware [m/fun-mode])

(defn -main [& args])
