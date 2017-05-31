(require '[clojure.java.io :as io])
(require '[leiningen.exec :refer [deps]])
(deps '[[clj-time "0.13.0"]])
(require '[clj-time.core :as time])

(def timefuncs {:year time/year :month time/month
                :day time/day :hour time/hour
                :minute time/minute :second time/second
                :now time/now})

(defn timestamp-padding-length
  [timefunc]
  (if (= timefunc (:year timefuncs)) 4 2))


(defn pad-timestamp
  [timefunc string]
  (let [num-zeros (- (timestamp-padding-length timefunc) (count string))]
    (str (apply str (repeat num-zeros "0")) string)))


(defn zip
  ([zipper col1 col2]
    (zip zipper col1 col2 []))
  ([zipper col1 col2 result]
    (if (empty? col1)
      result
      (recur zipper
             (rest col1)
             (rest col2)
             (conj result (zipper (first col1) (first col2)))))))
    

(defn timestamp
  []
  (let [{:keys [year month day hour minute second now]} timefuncs
        current-time (now)
        getters [year month day hour minute second]]
    (->> getters
         (map #(% current-time))
         (map str)
         (zip #(pad-timestamp %1 %2) getters)
         (apply str))))


(defn init-migration-folder
  []
  (let [dir (io/file "./resources/migrations")]
    (if (not (.exists dir))
      (.mkdir dir))))


(defn migration-file-name
  [name up-or-down]
  (str "./resources/migrations/"
       (timestamp) 
       "-"
       name
       "."
       up-or-down
       ".sql"))


(defn name-from-migration-file-name
  [filename]
  (->> filename
       (drop-while #(not= "-" %))
       (take-while #(not= "." %))
       (apply str)))


(defn migration-name-conflict?
  [name]
  (let [dir (io/file "./resources/migrations")]
    (->> dir
         .listFiles
         (map #(.getName %))
         (map #(name-from-migration-file-name %))
         (some #(= name %)))))


(defn make-migration
  [name up-or-down]
  (let [file (io/file (migration-file-name name up-or-down))]
    (.createNewFile file)))


(defn make-migrations
  [name]
  (make-migration name "up")
  (make-migration name "down"))

(defn -main
  []
  (init-migration-folder)
  (let [name (second *command-line-args*)]
    (make-migrations name)))


(-main)