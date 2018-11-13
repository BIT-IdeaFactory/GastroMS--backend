package models

import java.sql.Time
import play.api.Play.current
import play.api.db.DB
import play.api.libs.json.Json
import slick.driver.PostgresDriver.simple._

case class OpenHour(id: Int, day: String, placeId: Int, start: Option[Time], end: Option[Time]) {
    def toJson = Json.obj(
        "day" -> day,
        "start" -> start.getOrElse("null").toString.take(5),
        "end" -> end.getOrElse("null").toString.take(5)
    )

}

class OpenHours(tag: Tag) extends Table[OpenHour](tag, "OpenHour") {
    def id = column[Int]("id",O.PrimaryKey)
    def day = column[String]("day")
    def placeId = column[Int]("placeId")
    def start = column[Option[Time]]("start")
    def end = column[Option[Time]]("end")
    def * = (id, day, placeId, start, end) <> (OpenHour.tupled, OpenHour.unapply)

    def foodPlace = foreignKey("FP_FK", placeId, Foodplaces.foodplaces)(_.id)
}

object OpenHours {

    val openHours = TableQuery[OpenHours]

    def getAll: List[OpenHour] = {
        Database.forDataSource(DB.getDataSource()) withSession { implicit session =>
            openHours.list
        }
    }

    def getOpenHoursOf(id: Int): List[OpenHour] = {
        Database.forDataSource(DB.getDataSource()) withSession { implicit session =>
            openHours.list.filter(_.placeId == id)
        }
    }

}
