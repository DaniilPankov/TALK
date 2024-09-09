trait Handler {
  var sucessor :Handler

  def setSuccessor(successor :Handler): Unit = {
    this.sucessor = successor
  }

  def handleRequest(expression: String): Unit = ???
}
