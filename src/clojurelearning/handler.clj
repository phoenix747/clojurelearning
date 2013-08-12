(ns clojurelearning.handler
  (:use [compojure.core :only (GET defroutes)])
  (:require [compojure.handler :as handler]
            [compojure.route :as route])

  (:use [net.cgrand.enlive-html :only (deftemplate defsnippet snippet append)]))

(defsnippet index-content "index.html" [:.jumbotron]
  [])

(defsnippet meterial-content "meterial.html" [:.meterial]
  [])

(defsnippet about-content "about.html" [:.about]
  [])

(defsnippet footer "footer.html" [:.footer]
  [])

(deftemplate template "template.html"
  [page]
  [:#main] (cond
            (= page "index") (append (index-content) (footer))
            (= page "meterial") (append (meterial-content) (footer))
            (= page "about") (append (about-content) (footer))
            true nil))

(defroutes app-routes
  (GET "/" [] (template "index"))
  (GET "/meterial.html" [] (template "meterial"))
  (GET "/about.html" [] (template "about"))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))
