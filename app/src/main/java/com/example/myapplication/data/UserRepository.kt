package com.example.myapplication.data

import com.example.myapplication.model.User

class UserRepository {

    fun getUser(): User {
        return User(name = "Hello Madi Berikkazy!")
    }
}