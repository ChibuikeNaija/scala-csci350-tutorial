package controllers

import javax.inject._
import play.api.mvc._
import play.api.libs.json._
import models.{Task, TaskList}

@Singleton
class TaskController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def addTask() = Action(parse.json) { request =>
    request.body.validate[Task].fold(
      errors => {
        BadRequest(Json.obj("message" -> "Invalid task format"))
      },
      task => {
        TaskList.addTask(task)
        Created(Json.obj("message" -> "Task added successfully"))
      }
    )
  }

  def getTask(id: Long) = Action {
    TaskList.getTaskById(id) match {
      case Some(task) => Ok(Json.toJson(task))
      case None       => NotFound(Json.obj("message" -> "Task not found"))
    }
  }

  def deleteTask(id: Long) = Action {
    TaskList.getTaskById(id) match {
      case Some(_) =>
        TaskList.deleteTask(id)
        Ok(Json.obj("message" -> "Task deleted successfully"))
      case None =>
        NotFound(Json.obj("message" -> "Task not found"))
    }
  }
}
