package net.craftal.web.model.common.table

import play.api.mvc.Call

case class Column(key: String, label: String, getAction: Option[Any => Call])
