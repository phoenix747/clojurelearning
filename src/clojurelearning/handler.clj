(ns clojurelearning.handler
  (:use [compojure.core :only (GET defroutes)])
  (:require [compojure.handler :as handler]
            [compojure.route :as route])

  (:require [net.cgrand.enlive-html :as eh]))

(eh/defsnippet home-content "index.html" [:.jumbotron]
  [])

(eh/defsnippet meterial-content "meterial.html" [:.meterial]
  [])

(eh/defsnippet about-content "about.html" [:.about]
  [])

(eh/defsnippet home-masthead "masthead.html" [:.masthead]
  []
  [:#home] (eh/add-class "active"))

(eh/defsnippet meterial-masthead "masthead.html" [:.masthead]
  []
  [:#meterial] (eh/add-class "active"))

(eh/defsnippet about-masthead "masthead.html" [:.masthead]
  []
  [:#about] (eh/add-class "active"))

(eh/defsnippet footer "footer.html" [:.footer]
  [])

(eh/deftemplate template "template.html"
  [page]
  [:#main] (cond
            (= page "home") (eh/append (home-masthead) (home-content) (footer))
            (= page "meterial") (eh/append (meterial-masthead) (meterial-content) (footer))
            (= page "about") (eh/append (about-masthead) (about-content) (footer))
            true nil))

(defroutes app-routes
  (GET "/" [] (template "home"))
  (GET "/meterial.html" [] (template "meterial"))
  (GET "/about.html" [] (template "about"))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))
