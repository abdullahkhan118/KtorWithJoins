package com.horux.data.students

import org.jetbrains.exposed.sql.Table


object Students: Table() {

    val id = integer("id").autoIncrement()
    val name = varchar("name",255)
    val age = integer("age")

    override val primaryKey = PrimaryKey(id, name = "pk_user_id")

}