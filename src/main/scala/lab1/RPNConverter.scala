package lab1

import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.util.matching.Regex

class RPNConverter extends Handler {
  val precedence: Map[String, Int] = Map(
    "(" -> 1,
    ")" -> 1,
    "+" -> 2,
    "-" -> 2,
    "*" -> 3,
    "/" -> 3,
  )
  override def handleRequest(expression: Expression): Unit = {
    var result: String = ""
    for(function <- Functions.functions){
      if(expression.getExpression().contains(function)) {
        val local: String = expression.getExpression()
        val regex: Regex = (function + """\(([\d.]+),([\d.]+)\)""").r
        result = regex.replaceAllIn(local, (m: Regex.Match) => {
          val arg1 = m.group(1).toDouble
          val arg2 = m.group(2).toDouble
          Functions.generalFunction(function, arg1, arg2).toString
        })
      }
    }

    val splittedExpression = result.split("(?<=[-+*/()])|(?=[-+*/()])").filter(_.nonEmpty)
    val output = mutable.ListBuffer[String]()
    val operatorsStack = mutable.Stack[String]()

    splittedExpression.foreach {
        case token if token.matches("""-?\d+(\.\d+)?([eE][+-]?\d+)?""")  =>
          output += token

        case "(" =>
          operatorsStack.push("(")

        case ")" =>
          while (operatorsStack.nonEmpty && operatorsStack.top != "(") {
            output += operatorsStack.pop()
          }
          if (operatorsStack.nonEmpty && operatorsStack.top == "(") {
            operatorsStack.pop()
          }

        case token if precedence.keys.toList.contains(token) =>
          while (operatorsStack.nonEmpty && precedence(operatorsStack.top) >= precedence(token)) {
            output += operatorsStack.pop()
          }
          operatorsStack.push(token)

        case token => throw new IllegalStateException(s"Неизвестный символ: $token")
    }

    while (operatorsStack.nonEmpty) {
      output += operatorsStack.pop()
    }

    expression.setPRN(output)
    if (getSuccessor() != null) getSuccessor().handleRequest(expression)
  }
}
