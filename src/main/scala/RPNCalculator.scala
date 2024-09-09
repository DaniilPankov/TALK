package lab1

import scala.collection.mutable


object RPNCalculator {
  // Функция для вычисления значения выражения, записанного в обратной польской нотации
  def run(expression: List[String]): Double = {
    // Стек для операндов
    val stack = mutable.Stack[Double]()

    // Проход по каждому элементу выражения
    expression.foreach {
      // Если это число, помещаем его в стек
      case num if num.matches("""-?\d+(\.\d+)?""") =>
        stack.push(num.toDouble)
      // Если это оператор, извлекаем два операнда и выполняем операцию
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

      case "^" =>
        val b = stack.pop()
        val a = stack.pop()
        stack.push(Math.pow(a, b))

      // Если это неизвестный символ, выбрасываем исключение
      case token => throw new IllegalArgumentException(s"Неизвестный оператор: $token")
    }

    // В конце в стеке должен остаться только один элемент - результат
    if (stack.size != 1) {
      throw new IllegalStateException("Некорректное выражение")
    }

    stack.pop()
  }
}
