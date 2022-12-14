(ns core
  (:require [clojure.tools.logging :as log])
  (:gen-class))

;; try any level
;; (log/log :cat  "hi"  )
;; that fails, looks like we have to pick a prexisting one. This would be a good place for a clojure spec.

;; pick one that your sure will work:
(log/log :debug "debug from build!")

(defn divide [x y]
  (log/info "dividing" x "by" y)
  (try
    (log/spyf "result: %s" (/ x y)) ; yields the result
    (catch Exception ex
      (log/error ex "There was an error in calculation"))))
(comment
  ;; this will show in the repl
  (divide 0 0)
  )

(defn -main [& args]
  (println "println")
  (log/error ">>>--------------- FOOBAR ----------------<<<"))
