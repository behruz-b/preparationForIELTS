package controllers

import play.api.mvc._
import play.api.db.slick.Config.driver.simple._
import play.api.db.slick._
import models._
import play.api.Logger

import scala.slick.lifted.TableQuery

/**
 * Created by Behruz on 3/27/2015.
 */
class TextList extends Controller {
  val textList = TableQuery[Rtext]


  def showRForm = Action {
    Ok(views.html.rform())
  }


  def add = DBAction { implicit request =>
    val formParams = request.body.asFormUrlEncoded
    val text = formParams.get("RText")(0)
    val title = formParams.get("RTitle")(0)


    val textId = (textList returning textList.map(_.id)) += Text(None, text, title)
    Logger.info(s"LastId = $textId")
    Redirect(routes.TextList.reading())
  }

  def reading = DBAction { implicit rs =>
    Logger.info(s"SHOW_ALL = ${textList.list}")
    Ok(views.html.reading(textList.list))
  }
  def remove(id: Int) = DBAction { implicit request =>
  textList.filter(_.id === id). delete;
    Redirect(routes.TextList.reading())
  }

}
