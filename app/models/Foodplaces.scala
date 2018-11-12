package models

import java.sql.Date
import play.api.Play.current
import play.api.db.DB
import play.api.libs.json.Json
import slick.driver.PostgresDriver.simple._

case class Foodplace(id: Int, name: String, coordX: Float, coordY: Float) {
    def toJson = Json.obj(
        "name" -> this.name,
        "latitude" -> this.coordX,
        "longitude" -> this.coordY
    )
}

class Foodplaces(tag: Tag) extends Table[Foodplace](tag, "Foodplace") {
    def id = column[Int]("id",O.PrimaryKey)
    def name = column[String]("name")
    def coordX = column[Float]("coordX")
    def coordY = column[Float]("coordY")
    def * = (id, name, coordX, coordY) <> (Foodplace.tupled, Foodplace.unapply)
}

object Foodplaces {

    val foodplaces = TableQuery[Foodplaces]

    def getAll: List[Foodplace] = {
        Database.forDataSource(DB.getDataSource()) withSession { implicit session =>
            foodplaces.list
        }
    }

    def getFoodplace(name: String): Option[Foodplace] = {
        Database.forDataSource(DB.getDataSource()) withSession { implicit session =>
            foodplaces.list.find(fp => fp.name == name)
        }
    }

    def getAllFoodplacesWithOpenHours: List[(Foodplace, OpenHour)] = {
        Database.forDataSource(DB.getDataSource()) withSession { implicit session =>
            val innerJoin = for {
                (f, o) <- foodplaces join OpenHours.openHours on (_.id === _.placeId)
            } yield (f, o)
            innerJoin.list
        }
    }
}
