package controllers

import play.api._
import play.api.mvc._
import models._
import play.api.libs.json._
import java.sql.Time
object Application extends Controller {

  implicit val foodplaceWrites = new Writes[Foodplace] {
    def writes(foodplace: Foodplace) = foodplace.toJson
  }

  implicit val foodplaceAndHoursWrites = new Writes[OpenHour]{
    def writes(t: OpenHour) = t.toJson
  }

  implicit val foodplacesAndHoursListWrites = new Writes[(Foodplace, List[OpenHour])] {
    def writes(t: (Foodplace,  List[OpenHour])) = Json.obj(
      "foodplace" -> t._1.toJson,
      "hours" -> Json.toJson(t._2)
    )
  }


  def allFoodplaces = Action {
    Ok(Json.toJson(Foodplaces.getAll))
  }

  def getFoodplace(name: String) = Action {
    Foodplaces.getFoodplace(name) match {
      case Some(x) => Ok(x.toJson)
      case None    => Ok(Json.toJson(List[String]()))
    }
  }

  def getFoodplaceOpenHours(name: String) = Action {
    Foodplaces.getFoodplace(name) match {
      case Some(x) => Ok(Json.toJson(OpenHours.getOpenHoursOf(x.id)))
      case None    => Ok(Json.toJson(List[String]()))
    }
  }

  def allOpenHours = Action {
    Ok(OpenHours.getAll.toString)
  }

  def getAllFoodplacesWithOpenHours = Action {
    val tmp: Map[Foodplace,List[OpenHour]] =
      Foodplaces.getAllFoodplacesWithOpenHours
        .groupBy(_._1)
        .mapValues (_ map (chosenValues => chosenValues._2))
    Ok(Json.toJson(tmp))
  }
}