// @GENERATOR:play-routes-compiler
// @SOURCE:/home/bartek/Desktop/scala/hackaton/osadnikowe/scala-sample-hackathon/conf/routes
// @DATE:Sat Oct 27 19:08:03 CEST 2018

import play.api.mvc.Call


import _root_.controllers.Assets.Asset

// @LINE:1
package controllers {

  // @LINE:1
  class ReverseHomeController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:8
    def sayHello(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "all")
    }
  
    // @LINE:1
    def index(): Call = {
      
      Call("GET", _prefix)
    }
  
    // @LINE:10
    def allEateries(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "allEateries")
    }
  
  }

  // @LINE:6
  class ReverseAssets(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:6
    def at(file:String): Call = {
      implicit lazy val _rrc = new play.core.routing.ReverseRouteContext(Map(("path", "/public"))); _rrc
      Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[play.api.mvc.PathBindable[String]].unbind("file", file))
    }
  
  }


}
