package com.crispywalrus
package qdsl

import io.getquill._
import java.time._


// LocalDateTime is built in to quill so this was easier to start with.
// case class Ralph(id: Long,description: String,createdAt: LocalDateTime)
case class Ralph(id: Long,description: String,createdAt: Instant)

object Db extends App {

  lazy val ctx = new FinagleMysqlContext[SnakeCase]("ctx") // the name here is prepended to all the config keys this uses

  import ctx._

  // this turned out harder than expected due to the fact that the finagle-mysql context doesn't use java.sql.Timestamp
  // the compiler then couldn't resolve two things, dateDecoder and the mappedDecoder function... 
  implicit val decodeInstant: Decoder[Instant] = mappedDecoder(MappedEncoding[java.util.Date,java.time.Instant](_.toInstant),dateDecoder)
 
  def getRalph: (Long) => Quoted[Query[Ralph]] = id => quote {
    query[Ralph].filter( _.id == id)
  }

  def getAllRalph = quote { query[Ralph] }

  def doOne(id: Long) = ctx.run(getRalph(id))
  def doGetAll = ctx.run(getAllRalph)

}
