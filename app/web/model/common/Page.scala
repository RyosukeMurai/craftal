package web.model.common

case class Page(page: Int, size: Int, total: Int) {
  lazy val prev: Option[Int] = Option(page - 1).filter(_ > 0)
  lazy val next: Option[Int] = Option(page + 1).filter(_ => ((page * size) + size) < total)

  def getSurroundingPages(collectionRef: Seq[Page] = Seq(), limit: Int = 3): Seq[Page] = {
    collectionRef.size match {
      case s if s < limit && collectionRef.lastOption.map(_.next).isDefined => getSurroundingPages(
        collectionRef :+ Page(collectionRef.size + 1, this.size, this.total),
        limit
      )
      case _ => collectionRef
    }
  }
}
