(defproject learn-clojure "0.1.0-SNAPSHOT"
  :description "A playground for learning the basics of Clojure."
  :url "https://github.com/jall/learn-clojure"
  :license {:name "MIT"
            :url "https://opensource.org/licenses/MIT"}
  :dependencies [
     [org.clojure/clojure "1.8.0"]
     [proto-repl "0.3.1"]
  ]
  :main ^:skip-aot learn-clojure.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
