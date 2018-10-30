package controllers

import play.api._
import play.api.mvc._
import models._
import play.api.libs.json._
import java.sql.Time
object Application extends Controller {

  implicit val foodplaceWrites = new Writes[Foodplace] {
    def writes(foodplace: Foodplace) = Json.obj(
      "name" -> foodplace.name,
      "coordX" -> foodplace.coordX,
      "coordY" -> foodplace.coordY
    )
  }

  implicit val itemWrites = new Writes[(String, List[( String, String, String)])] {
    implicit val listWrites = new Writes[( String, String, String)]{
      def writes(t: ( String, String, String)) = Json.obj(
        "day" -> t._1,
        "start" -> t._2,
        "end" -> t._3)
    }
    
    def writes(t: (String,  List[( String, String, String)])) = Json.obj(
      "name" -> t._1,
      "hours" -> Json.toJson(t._2))
  }

  implicit val itemWithNullWrites = new Writes[(String, List[( String, Option[Time], Option[Time])])] {
    implicit val listWrites = new Writes[( String, Option[Time], Option[Time])]{
      def writes(t: ( String, Option[Time], Option[Time])) = Json.obj(
        "day" -> t._1,
        "start" -> t._2.getOrElse("null").toString,
        "end" -> t._3.getOrElse("null").toString)
    }
    
    def writes(t: (String,  List[( String, Option[Time], Option[Time])])) = Json.obj(
      "name" -> t._1,
      "hours" -> Json.toJson(t._2))
  }



  def allFoodplaces = Action {
    Ok(Json.toJson(Foodplaces.getAll))
  }

  def getFoodplace(name: String) = Action {
    Foodplaces.getFoodplace(name) match {
      case Some(x) => Ok(Json.toJson(x))
      // nie moze byc List() bo wtedy sie pluje
      case None    => Ok(Json.toJson(List(1).tail))
    }
  }

  def allOpenHours = Action {
    Ok(OpenHours.getAll.toString)
  }

  def getAllFoodplacesWithOpenHours = Action {
    val tmp: (Map[String,List[(String,Option[Time],Option[Time])]]) = 
      (Foodplaces.getAllFoodplacesWithOpenHours
        .groupBy(_._1)
        .mapValues (_ map (chosenValues => (chosenValues._2.toString,chosenValues._3,chosenValues._4)))
      )
    Ok(Json.toJson(tmp))
  }
}