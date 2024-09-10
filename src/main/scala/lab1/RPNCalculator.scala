package lab1

import scala.collection.mutable


class RPNCalculator extends Handler {
  override def handleRequest(expression: Expression): Unit = {
    val stack = mutable.Stack[Double]()

    expression.getPRN().foreach {
      case token if token.matches("""-?\d+(\.\d+)?""") =>
        stack.push(token.toDouble)

      case "+" =>
        val b = stack.pop()
        val a = stack.pop()
        stack.push(a + b)

      case "-" =>
        val b = stack.pop()
        val a = stack.pop()
        stack.push(a - b)

      case "*" =>
        val b = stack.pop()
        val a = stack.pop()
        stack.push(a * b)

      case "/" =>
        val b = stack.pop()
        val a = stack.pop()
        if (b == 0.0) throw new ArithmeticException("Деление на ноль")
        stack.push(a / b)

      case token => throw new IllegalArgumentException(s"Неизвестный оператор: $token")
    }

    if (stack.size != 1) {
      throw new IllegalStateException("Некорректное выражение")
    }

    expression.setResult(stack.pop())
    if (getSuccessor() != null) getSuccessor().handleRequest(expression)
  }
}
