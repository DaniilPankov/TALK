package lab1

import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.util.matching.Regex

object RPNConverter {
  val functions: ListBuffer[String] = ListBuffer("pow")
  val precedence: Map[String, Int] = Map(
    "(" -> 1,
    ")" -> 1,
    "+" -> 2,
    "-" -> 2,
    "*" -> 3,
    "/" -> 3,
  )
  def toRPN(expression: Expression): List[String] = {
    var result1: String = ""
    for(function <- functions){
      if(expression.getExpression().contains(function)) {
        val local: String = expression.getExpression()
        val regex: Regex = (function + """\(([\d.]+),([\d.]+)\)""").r
        result1 = regex.replaceAllIn(local, (m: Regex.Match) => {
          val base = m.group(1).toDouble   // Первый аргумент
          val exponent = m.group(2).toDouble // Второй аргумент
          // Вычисляем результат pow(base, exponent)
          val result = Functions.generalFunction(function, base, exponent)

          // Заменяем pow(a, b) на результат
          result.toString
        })
      }
    }

    val splittedExpression = result1.split("(?<=[-+*/()])|(?=[-+*/()])").filter(_.nonEmpty)
    val output = mutable.ListBuffer[String]()  // Выходная строка
    val operators = mutable.Stack[String]()    // Стек для операторов

    for (token <- splittedExpression) {
      token match {
        // Если токен - операнд (число или переменная), добавляем его в выходную строку
        case t if t.matches("""-?\d+(\.\d+)?([eE][+-]?\d+)?""") || t.matches("[a-zA-Z]") =>
          output += t

        // Если токен - открывающая скобка, добавляем её в стек
        case "(" =>
          operators.push(token)

        // Если токен - закрывающая скобка, выталкиваем операторы до открытия скобки
        case ")" =>
          while (operators.nonEmpty && operators.top != "(") {
            output += operators.pop()
          }
          // Удаляем открывающую скобку из стека
          if (operators.nonEmpty && operators.top == "(") {
            operators.pop()
          }

        // Если токен - оператор
        case t if precedence.keys.toList.contains(t) =>
          // Выталкиваем все операторы с большим или равным приоритетом
          while (operators.nonEmpty && precedence(operators.top) >= precedence(t)) {
            output += operators.pop()
          }
          operators.push(t)

        // В случае неизвестного токена (на случай некорректного ввода)
        case _ => throw new IllegalArgumentException(s"Неизвестный символ: $token")
      }
    }

    // Выталкиваем все оставшиеся операторы из стека
    while (operators.nonEmpty) {
      output += operators.pop()
    }

    output.toList
  }
}
