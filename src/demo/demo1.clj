(ns demo.demo1
  (:require
   [tablecloth.api :as tc]
   [missionary.core :as m]))

(defn add-row-count [ds]
  (println "row-count: " (tc/row-count ds))
  (tc/add-column ds :idx (range (tc/row-count ds))))

(defn from-algo-cell [algo-cell]
  (m/stream
   (m/ap (when-let [ds (m/?> algo-cell)]
           (let [ds-idx (add-row-count ds)]
             (for [idx (range (tc/row-count ds))]
               (let [;ds-until-idx (tc/select-rows ds-idx (range 0 idx))
                     ds-until-idx ds-idx]
                 (println "idx: " idx)
                 (m/amb {:idx idx
                         :ds ds-until-idx}))))))))



(def ds
  (tc/dataset {:close [40 40 50 80 100 240 130 70 90]
               :entry [nil nil nil :long nil nil :short nil nil]}))


(defn print1 [& args]
  (println "ds-with-idx: " (add-row-count ds)))


(def algo-cell (m/seed ds))

(defn process-row [r {:keys [idx ds] :as x}]
  (println "row: " idx)
  (println "x: " x))

(defn print2 [& args]
   (m/? (m/reduce process-row nil (from-algo-cell algo-cell))))





;(row-count ds)