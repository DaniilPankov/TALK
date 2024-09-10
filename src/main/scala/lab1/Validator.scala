package lab1

class Validator extends Handler{
  override def handleRequest(expression: Expression): Unit = {
    if (expression.getExpression().count(symbol => symbol == "(".toCharArray.head) != expression.getExpression().count(symbol => symbol == ")".toCharArray.head))
      throw new IllegalStateException("Количество открывающих скобок и закрывающих не соотвествует")

    val allowedSymbols = "0123456789+-*/().," + Functions.functions.mkString("")
    val invalidSymbols = expression.getExpression().filter(symbol => !allowedSymbols.contains(symbol))
    if (invalidSymbols.nonEmpty)
      throw new IllegalStateException(s"Выражение содержит недопустимые символы: ${invalidSymbols.mkString(", ")}")

    if (expression.getExpression().isEmpty) throw new IllegalStateException("Вы ввели пустую строку!")

    if (getSuccessor() != null) getSuccessor().handleRequest(expression)
  }
}
