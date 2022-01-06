package com.example.tiroid

import java.io.Serializable

class User(nameSurname:String,age:String,mail:String,password:String) : Serializable {

    var nameSurname: String = nameSurname
    var age: String = age
    var mail: String = mail
    var password: String = password



}