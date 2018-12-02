package web.model.common.table

import play.api.data.Form
import play.api.mvc.Call

abstract case class FormDefinition[T](private val fieldDefinitions: Seq[FieldDefinition],
                                      form: Form[T],
                                      submitAction: Call,
                                      cancelAction: Call) {
  lazy val fields: Seq[FieldDefinition] = this.fieldDefinitions.sortWith(_.order < _.order)
}
