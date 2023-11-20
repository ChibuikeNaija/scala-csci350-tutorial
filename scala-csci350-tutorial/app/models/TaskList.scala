package models

object TaskList {

  private var tasks: List[Task] = List(
    Task(1, "Do something"),
    Task(2, "Do something else")
  )

  def getAllTasks: List[Task] = tasks

  def getTaskById(id: Long): Option[Task] = tasks.find(_.id == id)

  def addTask(task: Task): Unit = {
    tasks = tasks :+ task
  }

  def deleteTask(id: Long): Unit = {
    tasks = tasks.filterNot(_.id == id)
  }
}
