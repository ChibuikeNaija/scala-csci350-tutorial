package models

import play.api.libs.json._

case class Task(id: Long, label: String)

object Task {
  
  implicit val taskReads: Reads[Task] = Json.reads[Task]
  implicit val taskWrites: Writes[Task] = Json.writes[Task]

  private var taskList = List(
    Task(1, "Do something"),
    Task(2, "Do something else")
  )

  def getAllTasks: List[Task] = taskList

  def getTaskById(id: Long): Option[Task] = taskList.find(_.id == id)

  def addTask(task: Task): Unit = {
    taskList = taskList :+ task
  }

  def deleteTask(id: Long): Unit = {
    taskList = taskList.filterNot(_.id == id)
  }
}
