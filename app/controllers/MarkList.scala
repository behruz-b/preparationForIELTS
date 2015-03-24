package controllers

import play.api.mvc._
import play.api.db.slick.Config.driver.simple._
import play.api.db.slick._
import models._
import play.api.Logger

import scala.slick.lifted.TableQuery

/**
 * Created by Behruz on 3/24/2015.
 */
class MarkList extends Controller {
  val markList = TableQuery[Markof]

  def showList = DBAction { implicit rs =>
    Logger.info(s"SHOW_ALL = ${markList.list}")
    Ok(views.html.marksList(markList.list))
  }
}

