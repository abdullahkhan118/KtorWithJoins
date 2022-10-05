package com.horux.plugins

import com.horux.data.students.*
import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*

fun Application.configureRouting() {

    routing {
        addStudent()
        updateStudent()
        deleteStudent()
        getStudent()
        getStudents()
        get("/") {
            call.respondText("Hello World!")
        }
    }
}
