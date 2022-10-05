package com.horux.data.services

import com.horux.data.data_sources.StudentDataSource
import com.horux.data.students.Student
import com.horux.data.students.Students
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

class StudentH2Service : StudentDataSource {

    override suspend fun getAllByName(name: String): List<Student> {
        return transaction {
            Students.select {
                Students.name eq name
            }.map {
                Student(
                    it[Students.id],
                    it[Students.name],
                    it[Students.age]
                )
            }
        }
    }

    override suspend fun add(entity: Student): Boolean {
        return transaction {
            Students.insert {
                it[name] = entity.name
                it[age] = entity.age
            }.insertedCount > 0
        }
    }

    override suspend fun update(entity: Student): Boolean {
        transaction {
            Students.update({ Students.id eq entity.id }) {
                it[name] = entity.name
                it[age] = entity.age
            }
        }
        return true
    }

    override suspend fun delete(entity: Student): Boolean {
        transaction { Students.deleteWhere { Students.id eq entity.id } }
        return true
    }

    override suspend fun get(id: Int): Student? {
        return transaction {
            Students.select { Students.id eq id }.map {
                Student(it[Students.id], it[Students.name], it[Students.age])
            }.firstOrNull()
        }
    }

    override suspend fun getAll(): List<Student>? {
        return transaction {
            Students.selectAll().map {
                Student(it[Students.id], it[Students.name], it[Students.age])
            }
        }
    }
}