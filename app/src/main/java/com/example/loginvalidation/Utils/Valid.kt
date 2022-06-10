package com.shivansh.officetask.utils

import android.util.Patterns
import com.example.loginvalidation.Utils.Constants.PASSWORD_PATTERN
import java.util.regex.Pattern

 object Valid  {
    val pattern = Pattern.compile(PASSWORD_PATTERN)

     fun isValidEmail(email : String) : Boolean{
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
     fun isValidFirstName(firstname : String) : Boolean{
         return  firstname.isEmpty()
     }
     fun isValidLastName(lastname : String) : Boolean{
         return  lastname.isEmpty()
     }
    fun isValidPassword(password : String) : Boolean{
        return pattern.matcher(password).matches()
    }

    fun isValidPhone(phone : String) : Boolean{
        return phone.length == 10
    }
     fun isConfirmPassword(password: String,confirmPassword : String) :Boolean{
         return password == confirmPassword
     }

 }