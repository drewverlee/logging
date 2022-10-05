(ns build
  "Builds the centriq-web api server uberjar"
  (:require [clojure.tools.build.api :as b]
            [clojure.string :as str]
            [clojure.java.shell :as shell]))

(def lib 'core)

(def base-dir "app")

(def class-dir (str base-dir "/"  "classes"))
(def basis (b/create-basis {:project "deps.edn"}))
(def uber-file (str base-dir "/" "logging.jar"))
(def source-dirs ["src"])

(defn clean [_]
  (b/delete {:path base-dir}))

(defn uber [_]
  (clean nil)
  ;;NOTE the msg "skipping paths" produced by this function isn't a warning and can be ignored.
  (b/write-pom {:class-dir class-dir
                :lib       'logging/logging
                :basis     basis
                :src-dirs source-dirs})
  (b/copy-dir {:src-dirs   (concat source-dirs ["resources"])
               :target-dir class-dir})
  (b/compile-clj {:basis     basis
                  :src-dirs source-dirs
                  :class-dir class-dir})
  (b/uber {:class-dir class-dir
           :uber-file uber-file
           :basis     basis
           :main      lib}))

(comment
  ;; running this will build the uberjar from the repl as long as the repl was started with clj -A:build.
  ;; see deps.edn
  (uber nil))
