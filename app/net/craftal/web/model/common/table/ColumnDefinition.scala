package net.craftal.web.model.common.table

import play.api.mvc.Call

case class ColumnDefinition(key: String,
                            label: String, order: Int,
                            getAction: Option[Any => Call] = None)
