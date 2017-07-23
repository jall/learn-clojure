(ns game-of-life.core
  (:gen-class)
  (:require [game-of-life.grid :refer [random-grid]]
            [quil.core :as q]
            [quil.middleware :as m]))

(def square-size-px 10)
(def grid-size 50)
(def grid-size-px (* grid-size square-size-px))

(def dead-colour 240)
(def alive-colour 50)

(defn setup []
    (q/frame-rate 30)
    (random-grid grid-size))

(defn update-state [state]
    (identity state))

(defn draw-square [x y is-alive]
    ; (if is-alive (q/fill alive-colour) ((q/fill dead-colour)))
    (q/rect
        (* x square-size-px)
        (* y square-size-px)
        square-size-px
        square-size-px))

(defn draw-grid [state]
    (doseq [[coords is-alive] state
        :let [x (first coords) y (second coords)]]
        (draw-square x y is-alive)))

(defn draw-state [state]
    ; Clear the sketch first.
    (q/background dead-colour)
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
