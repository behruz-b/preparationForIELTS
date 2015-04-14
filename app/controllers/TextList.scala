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
  val textGroups = TableQuery[TextGroupTable]


  def reading = DBAction { implicit rs =>
   // Logger.info(s"SHOW_ALL = ${textList.list}")
    val textResult = (for {
      (text, textGroup) <- textList leftJoin textGroups on(_.textGroupId === _.id)
    } yield  (text,textGroup.name)).list
    .map {case (text, textGroupName) => TextForDisplay(text,textGroupName)}

    Ok(views.html.reading(textResult))
  }

  def showRForm = DBAction { implicit rs =>
    Ok(views.html.addText(textGroups.list))
  }


  def add = DBAction { implicit request =>
    val formParams = request.body.asFormUrlEncoded
    val title = formParams.get("RTitle")(0)
    val text = formParams.get("RText")(0)
    val textGroupId = formParams.get("textGroup")(0).toInt

    val textId = (textList returning textList.map(_.id)) += Text(None, title, text, textGroupId)
    Logger.info(s"LastId = $textId")
    Redirect(routes.TextList.reading())
  }
  def remove(id: Int) = DBAction { implicit request =>
    textList.filter(_.id === id). delete;
    Redirect(routes.TextList.reading())
  }
  def update(id: Int) = DBAction { implicit rs =>
    val formParams = rs.body.asFormUrlEncoded
    val title = formParams.get("Rtitle")(0)
    val text = formParams.get("Rtext")(0)
    val textGroupId = formParams.get("textGroup")(0).toInt

    val texts = Text(Some(id), title,text, textGroupId)
    textList.filter(_.id === id).update(texts)

    Redirect(routes.TextList.reading())
  }
  def showEditForm(textId: Int) = DBAction { implicit request =>
    val byId = textList.findBy(_.id)
    val text = byId(textId).list.head

    Ok(views.html.edit(text,textGroups.list))
  }

}
