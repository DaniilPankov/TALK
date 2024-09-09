package lab1

import scala.collection.mutable.ListBuffer
import scala.math._

object Functions {
  val functions: ListBuffer[String] = ListBuffer("pow")

  def generalFunction(name: String, arg1: Double, arg2: Double): Double = {
    name match {
      case "pow" => pow(arg1, arg2)
      case _ => throw new IllegalStateException("Не сущестсует такой функции от двух переменных")
    }
  }

}
