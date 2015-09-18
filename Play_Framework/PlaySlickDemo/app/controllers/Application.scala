package controllers

import models.Task
import play.api.data.Form
import play.api.data.Forms.nonEmptyText
import play.api.mvc.Action
import play.api.mvc.Controller
import models.database.EmployeeUtil

object Application extends Controller {

  def index = Action {
    Redirect(routes.Application.tasks)
  }
   /* we use TODO to define our action implementations. Because we don’t 
   want to write the action implementations yet, we can use the built-in TODO 
   action that will return a 501 Not Implemented HTTP response. */
  
  def newTask = Action { implicit request =>
  taskForm.bindFromRequest.fold(
    errors => BadRequest(views.html.index(Task.all, errors)),
    label => {
      println("Before insert")
      EmployeeUtil.insertEmployees
      println("After insert")
      EmployeeUtil.getEmployees
     // Task.create(label)
      Redirect(routes.Application.tasks)
    }
  )
}

  def deleteTask(id:  Int) = TODO
  
  val taskForm = Form("label" -> nonEmptyText)
  
  
  def tasks= Action{
    Ok(views.html.index(Task.all,taskForm))
  }
  

}