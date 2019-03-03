package net.craftal.common.data

import slick.codegen.SourceCodeGenerator

object SlickModelGenerator {

  def main(args: Array[String]): Unit = {
    val slickDriver = "slick.jdbc.MySQLProfile"
    val jdbcDriver = "com.mysql.jdbc.Driver"
    val url = "jdbc:mysql://127.0.0.1:3306/craftal?characterEncoding=UTF-8"
    val user = "craftal"
    val password = "craftal"

    val outputFolder = "../modules/common/src"
    val pkg = "net.craftal.common.data"

    SourceCodeGenerator.main(
      Array(
        slickDriver,
        jdbcDriver,
        url,
        outputFolder,
        pkg,
        user,
        password
      )
    )
  }
}
