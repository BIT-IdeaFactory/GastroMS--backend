# GastroMS

# New Features!
 - "localhost:9000/AllFoodplacesWithOpenHours" gives you JSON with all hours places for each foodplace. 
Example:
 ```json
 [{"name":"lua","hours":[{"day":"tuesday","start":"11:00:00","end":"20:00:00"},{"day":"wednesday","start":"12:00:00","end":"20:00:00"},...,{"day":"monday","start":"10:00:00","end":"null"}]},{"name":"awiteks","hours":[{"day":"monday","start":"null","end":"null"},...,,{"day":"sunday","start":"12:00:00","end":"13:00:00"}]}]
 ```
 
# Start application
- Create databse with postgresql and give it a name "FoodPlaceApp". Next create a user "postgres" and give they password "123" (See [conf/application.conf](https://github.com/BIT-IdeaFactory/GastroMS--backend/blob/master/conf/application.conf))
- With pgAdmin load schema and example data from [database/database.sql](https://github.com/BIT-IdeaFactory/GastroMS--backend/blob/master/database/database.sql))
- Start application with sbt (it will take A LOT OF TIME :sob: )
- If there are any errors try change version of [Java](https://github.com/BIT-IdeaFactory/GastroMS--backend/blob/master/build.sbt) to 1.8 or/and [sbt](https://github.com/BIT-IdeaFactory/GastroMS--backend/blob/master/project/build.properties) to 0.13.17


# How to use sbt
- install sbt
- go to the directory with project
- in terminal write 'sbt' and later write 'run'
- Have fun!
