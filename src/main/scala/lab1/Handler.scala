package lab1

class Handler {
  private var successor :Handler = null

  def setSuccessor(successor :Handler): Unit = {
    this.successor = successor
  }

  def getSuccessor(): Handler = {
    successor
  }

  def handleRequest(expression: Expression): Unit = ???
}
