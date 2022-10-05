package com.horux.data.data_sources

import com.horux.data.students.Student

interface StudentDataSource: CrudDataSource<Student> {

    suspend fun getAllByName(name: String): List<Student>

}