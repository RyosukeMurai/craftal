package web.mapper

object DataMappingUtility {

  def caseToMap(caseClassInstance: AnyRef): Map[String, Any] =
    (Map[String, Any]() /: caseClassInstance.getClass.getDeclaredFields) { (a, f) =>
      f.setAccessible(true)
      a + (f.getName -> f.get(caseClassInstance))
    }


  def caseToMap(caseClassInstanceCollection: Seq[AnyRef]): Seq[Map[String, Any]] =
    caseClassInstanceCollection.map(caseToMap)
}
