package com.horux.data.students

import com.horux.data.data_sources.StudentDataSource
import com.horux.data.services.StudentH2Service
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

val studentDataSource: StudentDataSource = StudentH2Service()

fun Route.addStudent(){
    post("/students/add") {
        val request = call.receiveOrNull<Student>() ?: kotlin.run {
            call.respond(HttpStatusCode.BadRequest,"Request could not be parsed. Please check the request model.")
            return@post
        }

        if(!studentDataSource.add(request)){
            call.respond(HttpStatusCode.BadRequest,"Student insertion failed!")
            return@post
        }

        call.respond(HttpStatusCode.OK,"Student added successfully $request")
    }
}

fun Route.updateStudent(){
    put("/students/update") {
        val request = call.receiveOrNull<Student>() ?: kotlin.run {
            call.respond(HttpStatusCode.BadRequest,"Request could not be parsed. Please check the request model.")
            return@put
        }

        if(!studentDataSource.update(request)){
            call.respond(HttpStatusCode.BadRequest,"Student update failed!")
            return@put
        }

        call.respond(HttpStatusCode.OK,"Student updated successfully")
    }
}

fun Route.deleteStudent(){
    delete("/students/delete") {
        val request = call.receiveOrNull<Student>() ?: kotlin.run {
            call.respond(HttpStatusCode.BadRequest,"Request could not be parsed. Please check the request model.")
            return@delete
        }

        if(!studentDataSource.delete(request)){
            call.respond(HttpStatusCode.BadRequest,"Student deletion failed!")
            return@delete
        }

        call.respond(HttpStatusCode.OK,"Student deleted successfully")
    }
}

fun Route.getStudent(){
    get("/students/{id}") {
        val id = call.parameters["id"]?.toInt() ?: kotlin.run {
            call.respond(HttpStatusCode.BadRequest,"Id must be an integer!")
            return@get
        }

        studentDataSource.get(id)?.let{
            call.respond(HttpStatusCode.OK,it)
            return@get
        }

        call.respond(HttpStatusCode.NotFound,"Student with $id not found!")
    }
}

fun Route.getStudents(){
    get("/students/all") {
        studentDataSource.getAll()?.let{
            call.respond(HttpStatusCode.OK,it)
            return@get
        }

        call.respond(HttpStatusCode.NotFound,"No students found in the database!")
    }
}

