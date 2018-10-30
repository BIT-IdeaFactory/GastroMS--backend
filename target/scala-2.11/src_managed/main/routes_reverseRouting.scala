// @SOURCE:/home/bartek/Desktop/scala/hackaton/osadnikowe/japila-play-slick-postgresql-seed/conf/routes
// @HASH:aefc87b8ba08f6cea86fa102dbe89736c1c5cc54
// @DATE:Tue Oct 30 11:02:48 CET 2018

import Routes.{prefix => _prefix, defaultPrefix => _defaultPrefix}
import play.core._
import play.core.Router._
import play.core.Router.HandlerInvokerFactory._
import play.core.j._

import play.api.mvc._
import _root_.controllers.Assets.Asset

import Router.queryString


// @LINE:15
// @LINE:12
// @LINE:11
// @LINE:9
// @LINE:8
package controllers {

// @LINE:15
class ReverseAssets {


// @LINE:15
def at(file:String): Call = {
   implicit val _rrc = new ReverseRouteContext(Map(("path", "/public")))
   Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[PathBindable[String]].unbind("file", file))
}
                        

}
                          

// @LINE:12
// @LINE:11
// @LINE:9
// @LINE:8
class ReverseApplication {


// @LINE:8
def allFoodplaces(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "allFoodplaces")
}
                        

// @LINE:11
def allOpenHours(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "allOpenHours")
}
                        

// @LINE:9
def getFoodplace(name:String): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "Foodplace/" + implicitly[PathBindable[String]].unbind("name", dynamicString(name)))
}
                        

// @LINE:12
def getAllFoodplacesWithOpenHours(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "AllFoodplacesWithOpenHours")
}
                        

}
                          
}
                  


// @LINE:15
// @LINE:12
// @LINE:11
// @LINE:9
// @LINE:8
package controllers.javascript {
import ReverseRouteContext.empty

// @LINE:15
class ReverseAssets {


// @LINE:15
def at : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Assets.at",
   """
      function(file) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("file", file)})
      }
   """
)
                        

}
              

// @LINE:12
// @LINE:11
// @LINE:9
// @LINE:8
class ReverseApplication {


// @LINE:8
def allFoodplaces : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.allFoodplaces",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "allFoodplaces"})
      }
   """
)
                        

// @LINE:11
def allOpenHours : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.allOpenHours",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "allOpenHours"})
      }
   """
)
                        

// @LINE:9
def getFoodplace : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.getFoodplace",
   """
      function(name) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "Foodplace/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("name", encodeURIComponent(name))})
      }
   """
)
                        

// @LINE:12
def getAllFoodplacesWithOpenHours : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.getAllFoodplacesWithOpenHours",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "AllFoodplacesWithOpenHours"})
      }
   """
)
                        

}
              
}
        


// @LINE:15
// @LINE:12
// @LINE:11
// @LINE:9
// @LINE:8
package controllers.ref {


// @LINE:15
class ReverseAssets {


// @LINE:15
def at(path:String, file:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Assets.at(path, file), HandlerDef(this.getClass.getClassLoader, "", "controllers.Assets", "at", Seq(classOf[String], classOf[String]), "GET", """ Map static resources from the /public folder to the /assets URL path""", _prefix + """assets/$file<.+>""")
)
                      

}
                          

// @LINE:12
// @LINE:11
// @LINE:9
// @LINE:8
class ReverseApplication {


// @LINE:8
def allFoodplaces(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.allFoodplaces(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "allFoodplaces", Seq(), "GET", """""", _prefix + """allFoodplaces""")
)
                      

// @LINE:11
def allOpenHours(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.allOpenHours(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "allOpenHours", Seq(), "GET", """""", _prefix + """allOpenHours""")
)
                      

// @LINE:9
def getFoodplace(name:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.getFoodplace(name), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "getFoodplace", Seq(classOf[String]), "GET", """""", _prefix + """Foodplace/$name<[^/]+>""")
)
                      

// @LINE:12
def getAllFoodplacesWithOpenHours(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.getAllFoodplacesWithOpenHours(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "getAllFoodplacesWithOpenHours", Seq(), "GET", """""", _prefix + """AllFoodplacesWithOpenHours""")
)
                      

}
                          
}
        
    