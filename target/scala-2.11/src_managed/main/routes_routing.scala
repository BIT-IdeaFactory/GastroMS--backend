// @SOURCE:/home/bartek/Desktop/scala/hackaton/osadnikowe/japila-play-slick-postgresql-seed/conf/routes
// @HASH:aefc87b8ba08f6cea86fa102dbe89736c1c5cc54
// @DATE:Tue Oct 30 11:02:48 CET 2018


import play.core._
import play.core.Router._
import play.core.Router.HandlerInvokerFactory._
import play.core.j._

import play.api.mvc._
import _root_.controllers.Assets.Asset

import Router.queryString

object Routes extends Router.Routes {

import ReverseRouteContext.empty

private var _prefix = "/"

def setPrefix(prefix: String) {
  _prefix = prefix
  List[(String,Routes)]().foreach {
    case (p, router) => router.setPrefix(prefix + (if(prefix.endsWith("/")) "" else "/") + p)
  }
}

def prefix = _prefix

lazy val defaultPrefix = { if(Routes.prefix.endsWith("/")) "" else "/" }


// @LINE:8
private[this] lazy val controllers_Application_allFoodplaces0_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("allFoodplaces"))))
private[this] lazy val controllers_Application_allFoodplaces0_invoker = createInvoker(
controllers.Application.allFoodplaces,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "allFoodplaces", Nil,"GET", """""", Routes.prefix + """allFoodplaces"""))
        

// @LINE:9
private[this] lazy val controllers_Application_getFoodplace1_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("Foodplace/"),DynamicPart("name", """[^/]+""",true))))
private[this] lazy val controllers_Application_getFoodplace1_invoker = createInvoker(
controllers.Application.getFoodplace(fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "getFoodplace", Seq(classOf[String]),"GET", """""", Routes.prefix + """Foodplace/$name<[^/]+>"""))
        

// @LINE:11
private[this] lazy val controllers_Application_allOpenHours2_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("allOpenHours"))))
private[this] lazy val controllers_Application_allOpenHours2_invoker = createInvoker(
controllers.Application.allOpenHours,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "allOpenHours", Nil,"GET", """""", Routes.prefix + """allOpenHours"""))
        

// @LINE:12
private[this] lazy val controllers_Application_getAllFoodplacesWithOpenHours3_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("AllFoodplacesWithOpenHours"))))
private[this] lazy val controllers_Application_getAllFoodplacesWithOpenHours3_invoker = createInvoker(
controllers.Application.getAllFoodplacesWithOpenHours,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "getAllFoodplacesWithOpenHours", Nil,"GET", """""", Routes.prefix + """AllFoodplacesWithOpenHours"""))
        

// @LINE:15
private[this] lazy val controllers_Assets_at4_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("assets/"),DynamicPart("file", """.+""",false))))
private[this] lazy val controllers_Assets_at4_invoker = createInvoker(
controllers.Assets.at(fakeValue[String], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Assets", "at", Seq(classOf[String], classOf[String]),"GET", """ Map static resources from the /public folder to the /assets URL path""", Routes.prefix + """assets/$file<.+>"""))
        
def documentation = List(("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """allFoodplaces""","""controllers.Application.allFoodplaces"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """Foodplace/$name<[^/]+>""","""controllers.Application.getFoodplace(name:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """allOpenHours""","""controllers.Application.allOpenHours"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """AllFoodplacesWithOpenHours""","""controllers.Application.getAllFoodplacesWithOpenHours"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """assets/$file<.+>""","""controllers.Assets.at(path:String = "/public", file:String)""")).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
  case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
  case l => s ++ l.asInstanceOf[List[(String,String,String)]]
}}
      

def routes:PartialFunction[RequestHeader,Handler] = {

// @LINE:8
case controllers_Application_allFoodplaces0_route(params) => {
   call { 
        controllers_Application_allFoodplaces0_invoker.call(controllers.Application.allFoodplaces)
   }
}
        

// @LINE:9
case controllers_Application_getFoodplace1_route(params) => {
   call(params.fromPath[String]("name", None)) { (name) =>
        controllers_Application_getFoodplace1_invoker.call(controllers.Application.getFoodplace(name))
   }
}
        

// @LINE:11
case controllers_Application_allOpenHours2_route(params) => {
   call { 
        controllers_Application_allOpenHours2_invoker.call(controllers.Application.allOpenHours)
   }
}
        

// @LINE:12
case controllers_Application_getAllFoodplacesWithOpenHours3_route(params) => {
   call { 
        controllers_Application_getAllFoodplacesWithOpenHours3_invoker.call(controllers.Application.getAllFoodplacesWithOpenHours)
   }
}
        

// @LINE:15
case controllers_Assets_at4_route(params) => {
   call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        controllers_Assets_at4_invoker.call(controllers.Assets.at(path, file))
   }
}
        
}

}
     