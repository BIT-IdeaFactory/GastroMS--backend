package models

import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.Calendar

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
    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
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

    def addVote(voteToAdd: Vote): Unit = {
        Database.forDataSource(DB.getDataSource()) withSession { implicit session =>
            val insertQuery = votes returning votes.map(_.id) into ((vote,id) => vote.copy(id = id))
            val action = insertQuery += voteToAdd
            votes += action
        }
    }

    def partialApply(placeId: Int, open: Boolean): Vote = {
        Vote(0, placeId, getCurrentTimestamp, open)
    }

    def getCurrentTimestamp: Timestamp = {
        val currentTime = Calendar.getInstance.getTime
        val timeFormat =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
        val formattedTime = timeFormat.format(currentTime)
        Timestamp.valueOf(formattedTime)
    }

}