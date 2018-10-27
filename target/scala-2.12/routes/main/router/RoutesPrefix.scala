// @GENERATOR:play-routes-compiler
// @SOURCE:/home/bartek/Desktop/scala/hackaton/osadnikowe/scala-sample-hackathon/conf/routes
// @DATE:Sat Oct 27 19:08:03 CEST 2018


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
