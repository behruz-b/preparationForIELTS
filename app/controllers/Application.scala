package controllers

import models.Markof
import play.api._
import play.api.mvc._

object Application extends Controller {

  def index = Action {
    Ok(views.html.index())
  }

}
object MarkList extends Controller{
  def showList = Action{
    Ok(views.html.marksList(Markof.list))
  }
}