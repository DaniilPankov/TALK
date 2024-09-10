package lab1

import scala.sys.exit

object Main extends App {
  val expression: Expression = new Expression("(5+6)*(1+2)-5+pow(2,5)")
  try {
    val validator: Handler = new Validator
    val rpnConverter: Handler = new RPNConverter
    val rpnCalculator: Handler = new RPNCalculator

    validator.setSuccessor(rpnConverter)
    rpnConverter.setSuccessor(rpnCalculator)

    validator.handleRequest(expression)

    println("Исходное выражение: " + expression.getExpression())
    println("Преобразованное выражение в обратной польской нотации: " + expression.getPRN().mkString(""))
    println("Результат вычисления выражения: " + expression.getResult())
  } catch {
    case e: Exception => println("\u001B[31m"+s"Ошибка: ${e.getMessage}" + "\u001B[37m")
      exit(1)
  }
}