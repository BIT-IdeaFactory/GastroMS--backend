package models

import java.sql.Timestamp
import play.api.Play.current
import play.api.db.DB
import play.api.libs.json.Json
import slick.driver.PostgresDriver.simple._

case class Vote(id: Int, placeId: Int, voteTime: Timestamp, open: Boolean) {
    def toJson = Json.obj(
        "voteTime" -> voteTime.toString,
        "open" -> open.toString
    )
}

class Votes(tag: Tag) extends Table[Vote](tag, "Vote") {
    def id = column[Int]("id", O.PrimaryKey)
    def placeId = column[Int]("placeId")
    def voteTime = column[Timestamp]("voteTime")
    def open = column[Boolean]("open")
    def * = (id, placeId, voteTime, open) <> (Vote.tupled, Vote.unapply)

    def foodPlace = foreignKey("FP_FK", placeId, Foodplaces.foodplaces)(_.id)
}

object Votes {

    val votes = TableQuery[Votes]

    def getAll: List[Vote] = {
      Database.forDataSource(DB.getDataSource()) withSession { implicit session =>
          votes.list
      }
    }

    def getVotesFor(id: Int): List[Vote] = {
      Database.forDataSource(DB.getDataSource()) withSession { implicit session =>
          votes.list.filter(_.placeId == id)
      }
    }
}