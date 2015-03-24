package controllers


import models.Markof
import play.api.mvc._

object Application extends Controller {

  def index = Action {
    Ok(views.html.index())
  }
}


