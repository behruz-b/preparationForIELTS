package controllers

import models._
import play.api.Logger
import play.api.db.slick.Config.driver.simple._
import play.api.db.slick._
import play.api.mvc._

/**
 * Created by Behruz on 4/14/2015.
 */
class TextGroups extends Controller {

  val textGroups = TableQuery[TextGroupTable]

  def list = DBAction { implicit rs =>
    Ok(views.html.textGroupList(textGroups.list))
  }
  def showAddForm = DBAction { implicit rs =>
    Ok(views.html.addTextGroup())
  }
  def add = DBAction { implicit request =>
    val formParams = request.body.asFormUrlEncoded
    val name = formParams.get("name")(0)
    val textId = (textGroups returning textGroups.map(_.id)) += TextGroup(None, name)
    Logger.info(s"LastId = $textId")
    Redirect(routes.TextGroups.list())
  }


  def remove(id: Int) = DBAction { implicit request =>
    textGroups.filter(_.id === id).delete;
    Redirect(routes.TextGroups.list())
  }

  def update(id: Int) = DBAction { implicit rs =>
    val formParams = rs.body.asFormUrlEncoded
    val name = formParams.get("name")(0)

    val textGroup = TextGroup(Some(id), name)
      textGroups.filter(_.id === id).update(textGroup)

    Redirect(routes.TextGroups.list())
  }

  def showEditForm(textGroupId: Int) = DBAction { implicit request =>
    val byId = textGroups.findBy(_.id)
    val textGroup = byId(textGroupId).list.head

    Ok(views.html.editTextGroup(textGroup))
  }

}
