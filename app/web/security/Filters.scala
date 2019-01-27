package web.security

import be.objectify.deadbolt.scala.filters.DeadboltRoutePathFilter
import javax.inject.Inject
import play.api.http.{DefaultHttpFilters, EnabledFilters, HttpFilters}
import play.filters.cors.CORSFilter

class Filters @Inject()(
                         enabledFilters: EnabledFilters,
                         corsFilter: CORSFilter,
                         deadboltFilter: DeadboltRoutePathFilter
                       ) extends DefaultHttpFilters(enabledFilters.filters :+ corsFilter :+ deadboltFilter: _*) {
}
