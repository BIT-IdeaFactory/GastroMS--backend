package controllers

import play.api.mvc._
import models._
import play.api.libs.json._
import play.api.libs.functional.syntax._

object Application extends Controller {

  implicit val foodplaceWrites = new Writes[Foodplace] {
    override def writes(foodplace: Foodplace) = foodplace.toJson
  }

  implicit val openHoursWrites = new Writes[OpenHour]{
    override def writes(oh: OpenHour) = oh.toJson
  }

  implicit val foodplacesAndHoursListWrites = new Writes[(Foodplace, List[OpenHour])] {
    override def writes(t: (Foodplace,  List[OpenHour])) = Json.obj(
      "foodplace" -> t._1.toJson,
      "hours" -> Json.toJson(t._2)
    )
  }

  implicit val votesWrites = new Writes[Vote] {
    override def writes(vote: Vote): JsValue = vote.toJson
  }

  implicit val votesReads: Reads[Option[Vote]] = (
    (JsPath \ "name").read[String] and
    (JsPath \ "open").read[Boolean]
  ) (Votes.partialApply _)


  implicit val openChanceWrites = new Writes[OpenChance] {
    override def writes(openChance: OpenChance): JsValue = openChance.toJson
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
    Ok(Json.toJson(OpenHours.getAll))
  }

  def getAllFoodplacesWithOpenHours = Action {
    val tmp: Map[Foodplace,List[OpenHour]] =
      Foodplaces.getAllFoodplacesWithOpenHours
        .groupBy(_._1)
        .mapValues (_ map (chosenValues => chosenValues._2))
    Ok(Json.toJson(tmp))
  }

  def getFoodplaceVotes(name: String) = Action {
    Foodplaces.getFoodplace(name) match {
      case Some(x) => Ok(Json.toJson(Votes.getVotesFor(x.id)))
      case None    => Ok(Json.toJson(List[String]()))
    }
  }

  def getOpenChanceFor(name: String) = Action {
    Foodplaces.getFoodplace(name) match {
      case Some(x) => Ok(Json.toJson(Votes.calculateOpenChanceFor(x.id)))
      case None    => Ok(Json.toJson(List[String]()))
    }

  }

  def postVote = Action { implicit request =>
    val json = request.body.asJson.get
    val voteFromJson = Json.fromJson(json).get
    voteFromJson match {
      case None => List[String]()
      case Some(vote) => Votes.addVote(vote)
    }
    Ok
  }
}