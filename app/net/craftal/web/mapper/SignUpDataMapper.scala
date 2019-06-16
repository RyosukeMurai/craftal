package net.craftal.web.mapper

import net.craftal.core.domain.model.prefecture.Prefecture
import net.craftal.web.model.auth.SignUp


object SignUpDataMapper extends DataMapper {
  def transform(prefectures: List[Prefecture]): SignUp =
    SignUp(prefectures.map(p => (p.id.toString, p.name)))
}
