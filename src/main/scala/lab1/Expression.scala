package lab1

import scala.collection.mutable.ListBuffer

class Expression(expression: String) {

  var PRN: ListBuffer[String] = ListBuffer()
  var result: Double = 0.0

  def getExpression(): String = {
    expression
  }

  def setPRN(PRN: ListBuffer[String]) = {
    this.PRN = PRN.clone()
  }

  def getPRN(): ListBuffer[String] = {
    PRN
  }

  def setResult(result:Double) = {
    this.result = result
  }

  def getResult(): Double = {
    result
  }
}
