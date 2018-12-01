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

case class OpenChance(openChance: Double, numberOfVotes: Int) {
    def toJson = Json.obj(
        "openChance" -> openChance,
        "numberOfVotes" -> numberOfVotes
    )
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

    def calculateOpenChanceFor(id: Int): Option[OpenChance] = {
        val timeIntervalHours = 3;
        val timeInterval = convertHoursToMilliseconds(3)
        val currentTime = getCurrentTimestamp.getTime

        val latestVotes = getLatestVotes(id, currentTime, timeInterval)
        val numberOfLatestVotes = latestVotes.size

        numberOfLatestVotes match {
            case 0 => {
                val openChance = 0
                return Some(OpenChance(openChance, numberOfLatestVotes))
            }
            case _ => {
                val openChance = calculateOpenChance(latestVotes, numberOfLatestVotes, currentTime, timeInterval)
                return Some(OpenChance(openChance, numberOfLatestVotes))
            }
        }
    }

    def calculateOpenChance(votes: List[Vote], numberOfVotes: Int, currentTime : Long, timeInterval: Long): Double = {
        val votesForOpen = getVotesForOpen(votes)
        val sumOfChance = calculateSumOfChance(votesForOpen, currentTime, timeInterval)
        val openChance = sumOfChance / numberOfVotes.toDouble
        BigDecimal(openChance).setScale(3, BigDecimal.RoundingMode.HALF_UP).toDouble
    }

    def calculateOpenChanceOfVote(voteTime: Long, currentTime : Long, timeInterval: Long): Double = {
        val timeDifference = currentTime - voteTime
        val timeRelative = timeDifference/timeInterval.toDouble
        val chance = 1 - timeRelative
        chance.toDouble
    }

    def calculateSumOfChance(votes: List[Vote], currentTime : Long, timeInterval: Long): Double = {
        votes.map(_.voteTime.getTime)
          .map(voteTimeMillis => calculateOpenChanceOfVote(voteTimeMillis, currentTime, timeInterval))
          .sum
    }

    def addVote(voteToAdd: Vote): Unit = {
        Database.forDataSource(DB.getDataSource()) withSession { implicit session =>
            val insertQuery = votes returning votes.map(_.id) into ((vote,id) => vote.copy(id = id))
            insertQuery += voteToAdd
        }
    }

    def getLatestVotes(id: Int, currentTime: Long, timeInterval: Long): List[Vote] = {
        val votesForPlace = getVotesFor(id)
        votesForPlace.filter(currentTime - _.voteTime.getTime  < timeInterval)
    }

    def getVotesForOpen(votes: List[Vote]): List[Vote] = {
        votes.filter(_.open == true)
    }

    def partialApply(placeName: String, open: Boolean): Option[Vote] = {
        val foodplace = Foodplaces.getFoodplace(placeName)
        foodplace match {
            case Some(f) => Some(Vote(0, f.id, getCurrentTimestamp, open))
            case None => None
        }
    }

    def getCurrentTimestamp: Timestamp = {
        val currentTime = Calendar.getInstance.getTime
        val timeFormat =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
        val formattedTime = timeFormat.format(currentTime)
        Timestamp.valueOf(formattedTime)
    }

    def convertHoursToMilliseconds(hours: Double): Long = {
        val millisecondInHour = 3600000
        val multiplicationResult = hours * millisecondInHour
        multiplicationResult.toLong
    }
}