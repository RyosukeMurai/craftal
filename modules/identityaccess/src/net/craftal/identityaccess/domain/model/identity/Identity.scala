package net.craftal.identityaccess.domain.model.identity

import net.craftal.common.domain.model.Entity

trait Identity extends Entity[Identity] {
  def userId: Int

  def activated: Boolean
}
