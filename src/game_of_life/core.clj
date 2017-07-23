(ns game-of-life.core
  (:gen-class)
  (:require [game-of-life.grid :refer [random-grid]]
            [quil.core :as q]
            [quil.middleware :as m]))

(def square-size-px 10)
(def grid-size 50)
(def grid-size-px (* grid-size square-size-px))

(def dead-colour [90 90 90])
(def alive-colour [240 240 240])

(defn draw-square [x y is-alive]
    (apply q/fill (if is-alive alive-colour dead-colour))
    (q/rect
        (* x square-size-px)
        (* y square-size-px)
        square-size-px
        square-size-px))

(defn draw-grid [state]
    (doseq [[coords is-alive] state
        :let [x (first coords) y (second coords)]]
        (draw-square x y is-alive)))

(defn setup []
    (q/frame-rate 30)
    (random-grid grid-size))

(defn update-state [state]
    (identity state))

(defn draw-state [state]
    ; Clear the sketch first.
    (apply q/background alive-colour)
    (apply q/stroke alive-colour)
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
