package com.horux.data.students

@kotlinx.serialization.Serializable
data class Student(
    val id: Int,
    val name: String,
    val age: Int
)
