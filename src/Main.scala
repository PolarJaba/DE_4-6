import scala.io.StdIn.{readDouble, readLine}

object Main {
  def main(args: Array[String]): Unit = {
    val phrase = "Hello Scala!"
    println("Задание 3а")
    println(phrase)
    println(phrase.reverse)
    val low_case_phrase = phrase.toLowerCase
    val no_exclam_point = low_case_phrase.replace('!', ' ')
    println(no_exclam_point.concat("and goodbye python!"))
  }
}

object CalcMonthlyIncome {

  println("Задание 3b")
  val monthly_income: Double = {
    //    print("Введите значение годового дохода: ")
    //    val annual_income = readDouble()
    //    print("Введите размер премии в %: ")
    //    val prem = readDouble()
    //    print("Введите сумму компенсации на питание: ")
    //    val meal = readDouble()
    val annual_income = 1200000
    val prem = 30
    val meal = 70000
    (annual_income * 0.87 + annual_income * prem / 100 + meal) / 1000 / 12
  }

  def main(args: Array[String]): Unit = {
    println("Месячный доход после вычета налогов: " + monthly_income + " тысяч")
  }
}

object SalaryFluctCalc {

  import CalcMonthlyIncome._

  println("Задание 3c")
  val salaries = List(100, 150, 200, 80, 120, 75, monthly_income) // В тысячах
  val avg_salary = salaries.sum / salaries.length
  val flucts = salaries.map(salary => salary / avg_salary * 100 - 100)

  def main(args: Array[String]): Unit = {
    println("Отклонение (в процентах) оклада каждого отрудника от среднего значения оклада на уровне всего отдела ")
    flucts.foreach(fluct => println(f"$fluct%.2f"))
  }
}

object NewSalary{

  import CalcMonthlyIncome.monthly_income
  import SalaryFluctCalc.salaries

  println("Задание 3d")
//  println("Хорошо ли вел себя сотрудник N? (yes/no):")
//  val behavior = readLine()
  val behavior = "yes"
  val newSalary = {
    if (behavior == "yes") monthly_income * 1.3
    else monthly_income * 0.9
  }
  val newSalaries = salaries.dropRight(1) :+ newSalary
  val minSalary = newSalaries.min
  val maxSalary = newSalaries.max
  def main(args: Array[String]): Unit = {
    println(f"Значение самой низкой зарплаты (в тыс.): $minSalary")
    println(f"Значение самой высокой зарплаты (в тыс.): $maxSalary")
  }
}

object SortListEmployers {

  import NewSalary.newSalaries

  println("Задание 3е")
  val salariesNewSpecs = List[Double](350, 90)
  val curEmployersSalaries = newSalaries ++ salariesNewSpecs
  val sortedSalaries = curEmployersSalaries.sorted

  def main(args: Array[String]): Unit = {
    sortedSalaries.foreach(salary => println(f"$salary%.3f"))
  }
}


object NewEmployer {

  import SortListEmployers.sortedSalaries
  println("Задание 3f")
  val newEmpSal: Double = 130
  val ind: Int = sortedSalaries.indexWhere(salary => salary >= newEmpSal)
  val listEnd = sortedSalaries.drop(ind)
  val listBegin = sortedSalaries.dropRight(ind - 1)
  val allEmpSalaries = listBegin ++ List(newEmpSal) ++ listEnd
  def main(args: Array[String]): Unit = {
    allEmpSalaries.foreach(salary => println(f"$salary%.3f"))
  }
}

object MiddleDef {

  import NewEmployer.allEmpSalaries

  println("Задание 3g")
//  print("Введите нижний порог зп (в тыс.): ")
//  val lowSalary = readDouble()
//  print("Введите вкрхний порог зп (в тыс.): ")
//  val highSalary = readDouble()
  val lowSalary = 120
  val highSalary = 200
  val allEmpSalariesUpd = allEmpSalaries.zipWithIndex.map {case(salary, ind) => (ind, salary)}
  val middleSalEmps: List[(Int, Double)] = allEmpSalariesUpd.filter {
    case (_, salary) => salary >= lowSalary && salary <= highSalary}

  def main(args: Array[String]): Unit = {
    middleSalEmps.foreach { case(ind, salary) => println(f"$ind: $salary%.3f")}
  }
}

object IndSalaries{

  import NewEmployer.allEmpSalaries

  val indexedAllSalaries = allEmpSalaries.map(salary => salary * 1.07)
  def main(args: Array[String]): Unit = {
    indexedAllSalaries.foreach(salary => println(f"$salary%.3f"))
  }
}

