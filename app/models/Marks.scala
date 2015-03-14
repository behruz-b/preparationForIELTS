package models

/**
 * Created by Behruz on 3/13/2015.
 */
case class Mark(id:Int, name:String, reading: Double,listening: Double, writing:Double, speaking: Double, total:Double)
object Markof{
  var marks= List(
  Mark(1,"Behruz",90.5,75.8,66.9,81.4,78.65),
  Mark(2,"*******",0,0,0,0,0),
  Mark(3,"******",0,0,0,0,0)
  )
  def list=marks.toList
}


