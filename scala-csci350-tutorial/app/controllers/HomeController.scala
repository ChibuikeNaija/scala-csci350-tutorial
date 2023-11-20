package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.libs.json._
import models.{Task, TaskList}

@Singleton
class HomeController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def index() = Action { implicit request: Request[AnyContent] =>
    val tasks = TaskList.getAllTasks
    Ok(views.html.index(tasks))
  }

  def getTasks() = Action { implicit request: Request[AnyContent] =>
    val tasks = TaskList.getAllTasks
    Ok(Json.toJson(tasks))
  }

  def getTaskDetails(id: Long) = Action { implicit request: Request[AnyContent] =>
    TaskList.getTaskById(id) match {
      case Some(task) => Ok(views.html.taskDetails(task))
      case None       => NotFound("Task not found")
    }
  }
}
