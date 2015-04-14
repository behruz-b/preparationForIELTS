package models

/**
 * Created by Behruz on 3/13/2015.
 */
import play.api.db.slick.Config.driver.simple._

import scala.slick.lifted.TableQuery

case class Mark(id:Option[Int],
                name:String,
                reading: Double,
                listening: Double,
                writing:Double,
                speaking: Double,
                total:Double)
class Markof(tag:Tag) extends Table[Mark] (tag, "Marks") {

  def id = column[Int]("ID", O.PrimaryKey, O.AutoInc)

  def name = column[String]("NAME", O.Default(""))

  def reading = column[Double]("READING", O.Default(0))

  def listening = column[Double]("LISTENING", O.Default(0))

  def writing = column[Double]("WRITING", O.Default(0))

  def speaking = column[Double]("SPEAKING", O.Default(0))

  def total = column[Double]("TOTAL", O.Default(0))

  def * = (id.?, name, reading, listening, writing, speaking, total) <> (Mark.tupled, Mark.unapply _)

}
case class  Text(id:Option[Int],
                  text: String,
                  title: String,
                  textGroupId: Int)

case class TextForDisplay(text: Text,
                          textGroupName: String)

case class TextGroup(id: Option[Int],
                      name: String)

class Rtext(tag:Tag) extends Table[Text] (tag, "Rtext"){
    val textGroups = TableQuery[TextGroupTable]
    def id = column[Int]("ID", O.PrimaryKey, O.AutoInc)
    def text = column[String]("Rtext", O.Default(""))
    def title =   column[String]("RTitle", O.Default(""))
    def textGroupId = column[Int] ("TEXTGROUP_ID", O.NotNull)
    def * = (id.?,text,title,textGroupId) <> (Text.tupled, Text.unapply _)

    def textGroup = foreignKey("TEXT_FK_TEXTGROUP_ID",textGroupId, textGroups)(_.id)
}
  class TextGroupTable(tag: Tag) extends Table[TextGroup] (tag, "TEXTGROUPS"){
    def id = column[Int]("ID", O.PrimaryKey, O.AutoInc)

    def name = column[String]("NAME", O.Default(""))

    def * = (id.?, name) <> (TextGroup.tupled, TextGroup.unapply _)
  }

