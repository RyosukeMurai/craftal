package net.craftal.web.model.common.table

import play.api.mvc.Call

case class Cell(value: Any, call:Option[Call] = None)
