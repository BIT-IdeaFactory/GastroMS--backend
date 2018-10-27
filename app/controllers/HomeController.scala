package controllers

import javax.inject.Inject
import play.api.libs.json.Json
import play.api.mvc._

/**
  * A very small controller that renders a home page.
  */
class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {


  val exampleEatery: List[Eatery] = List(Eatery("lua",1,2),Eatery("lewiatan",2,3))
  def index = Action { implicit request =>
    Ok(views.html.index())
  }

  def sayHello = Action(parse.json) { request =>
    (request.body \ "name").asOpt[String].map { name =>
      Ok(Json.toJson(
        Map("status" -> "OK", "message" -> ("Hello " + name))
      ))
    }.getOrElse {
      BadRequest(Json.toJson(
        Map("status" -> "KO", "message" -> "Missing parameter [name]")
      ))
    }
  }

  def allEateries = Action { implicit request =>
      Ok(Json.toJson(exampleEatery.map{ eatery =>
        Json.toJson(Map("name" -> eatery.name,"x" -> eatery.x.toString, "y" -> eatery.y.toString))

      }))
  }

}

case class Eatery(name: String, x: Int, y: Int)
