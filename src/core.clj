(ns core
  (:import [java.lang.Throwable])
  (:require [clojure.tools.logging :as log]))

;; try any level
(log/log :cat :a "hi"  )

;; try a well known one

java.lang.Throwable

(log/log :debug "hi")

(defn divide [x y]
  (log/info "dividing" x "by" y)
  (try
    (log/spyf "result: %s" (/ x y)) ; yields the result
    (catch Exception ex
      (log/error ex "There was an error in calculation"))))

(divide 0 0)

(println "hi")
