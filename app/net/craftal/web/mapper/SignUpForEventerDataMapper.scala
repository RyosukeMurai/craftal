package net.craftal.web.mapper

import net.craftal.web.model.auth.SignUpForEventer


object SignUpForEventerDataMapper extends DataMapper {
  def transform(): SignUpForEventer = SignUpForEventer()
}
