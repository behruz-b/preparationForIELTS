package models

/**
 * Created by Behruz on 3/13/2015.
 */
import play.api.db.slick.Config.driver.simple._
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


