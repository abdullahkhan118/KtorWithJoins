package com.horux.plugins

import com.horux.data.students.Students
import io.ktor.server.application.*

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.*


fun Application.configurePostGreSQL(){
    Database.connect("jdbc:postgresql://localhost:12346/test", driver = "org.postgresql.Driver",
    user = "root", password = "your_pwd")
}

fun Application.configureH2InMemory(){
    Database.connect("jdbc:h2:mem:regular;DB_CLOSE_DELAY=-1;", "org.h2.Driver")
    createTables()
}

private fun createTables() = transaction{
    SchemaUtils.create(Students)
}