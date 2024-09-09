package lab1

import scala.sys.exit

object Main extends App {
  val expression: Expression = new Expression("(5+6)*(1+2)-5+pow(2,5)")
  try {
    Validator.run(expression)
    println("\u001B[32m" +"Выражение успешно прошло валидацию!!" + "\u001B[37m")
    val RPN = RPNConverter.toRPN(expression)
    println("Преобразованное выражение в обратной польской нотации: " + RPN.mkString(""))
    val answer = RPNCalculator.run(RPN)
    println(answer)
  } catch {
    case e: Exception => println("\u001B[31m"+s"Ошибка: ${e.getMessage}" + "\u001B[37m")
      exit(1)
  }
}