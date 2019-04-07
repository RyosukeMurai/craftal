package net.craftal.web.mapper

object DataMappingUtility {

  def caseToMap(caseClassInstanceCollection: Seq[AnyRef]): Seq[Map[String, Any]] =
    caseClassInstanceCollection.map(caseToMap)

  def caseToMap(caseClassInstance: AnyRef): Map[String, Any] =
    (Map[String, Any]() /: caseClassInstance.getClass.getDeclaredFields) { (a, f) =>
      f.setAccessible(true)
      a + (f.getName -> f.get(caseClassInstance))
    }
}
