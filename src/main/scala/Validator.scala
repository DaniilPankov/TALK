package lab1

object Validator {
  def run(expression: Expression): Unit = {
    if (expression.getExpression().count(symbol => symbol == "(".toCharArray.head) != expression.getExpression().count(symbol => symbol == ")".toCharArray.head))
      throw new IllegalStateException("Количество открывающих скобок и закрывающих не соотвествует")

    val allowedSymbols = "0123456789+-*/().," + Functions.functions.mkString("")
    val invalidSymbols = expression.getExpression().filter(symbol => !allowedSymbols.contains(symbol))
    if (invalidSymbols.nonEmpty)
      throw new IllegalStateException(s"Выражение содержит недопустимые символы: ${invalidSymbols.mkString(", ")}")

    if (expression.getExpression().isEmpty) throw new IllegalStateException("Вы ввели пустую строку!")

    //    try {
    //      expression.getExpression().replace(",", ".").split("([+\\-*/()])").filter(symbol => !symbol.equals(" ")).foreach(symbol => symbol.toDouble)
    //    }
    //    catch {
    //      case e: NumberFormatException => throw new IllegalStateException("Выражение содержит не числа")
    //    }
  }
}
