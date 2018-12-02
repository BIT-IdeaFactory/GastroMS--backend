import java.sql.Timestamp
import java.util.Calendar

import models.{Vote, Votes}
import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._


@RunWith(classOf[JUnitRunner])
class PlaceOpenChanceTest extends Specification {

  "calculateOpenChance" should {
    "Return -1.0 if there's only one vote for not open just a moment ago" in {
      val currentTime = Calendar.getInstance.getTimeInMillis
      val currentTimeStamp = new Timestamp(currentTime)
      val voteNotOpen = new Vote(1,1, currentTimeStamp, false)
      val votes = List(voteNotOpen)
      val numberOfVotes = votes.size
      val chance = Votes.calculateOpenChance(votes, numberOfVotes, currentTime, 1800)
      chance == -1.0
    }

    "Return 1.0 if there's only one vote for open just a moment ago" in {
      val currentTime = Calendar.getInstance.getTimeInMillis
      val currentTimeStamp = new Timestamp(currentTime)
      val voteOpen = new Vote(1,1, currentTimeStamp, true)
      val votes = List(voteOpen)
      val numberOfVotes = votes.size
      val chance = Votes.calculateOpenChance(votes, numberOfVotes, currentTime, 1800)
      chance == 1.0
    }
  }

  "calculateOpenChanceOfVote" should {
    "Return 0.5 if vote for open was added in the half of time interval" in {
      val timeInterval = 3600
      val currentTime = Calendar.getInstance.getTimeInMillis
      val voteTimeStamp = new Timestamp(currentTime - timeInterval/2)
      val voteOpen = new Vote(1,1, voteTimeStamp, true)
      val chance = Votes.calculateOpenChanceOfVote(voteOpen.voteTime.getTime, voteOpen.open, currentTime, timeInterval)
      chance == 0.5
    }
  }
}
