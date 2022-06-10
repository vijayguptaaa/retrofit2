package com.example.loginvalidation.viewmodel

import android.app.Person
import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.loginvalidation.models.UserErrorModel
import com.example.loginvalidation.roomdb.PersonRepository
import com.example.loginvalidation.roomdb.User
import com.shivansh.officetask.utils.Valid
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel(private val repository: PersonRepository) : ViewModel() {

    var email : MutableLiveData<String> = MutableLiveData()
    var password : MutableLiveData<String> = MutableLiveData()
    var liveUser : MutableLiveData<User> = MutableLiveData()
    val onSignUpResponse2 : MutableLiveData<Boolean> = MutableLiveData()
    val onLogInResponse : MutableLiveData<Boolean> = MutableLiveData()
    var userError : MutableLiveData<UserErrorModel> = MutableLiveData()
    init {
        onSignUpResponse2.value = false
        onLogInResponse.value = false
        userError.value = UserErrorModel()
    }



    // Login Button Function
    fun loginButton() {
        if (validate()){
            getPerson(email.value.toString(),password.value.toString())
        }
    }

    private fun validate() : Boolean{
        val userErrorMessage = UserErrorModel()
        val isValidEmail = Valid.isValidEmail(email.value.toString())
        val isValidPassword = Valid.isValidPassword(password.value.toString())

        if (!isValidEmail){
            userErrorMessage.emailErrorMessage = "Invalid Email"
        }
        if (!isValidPassword){
            userErrorMessage.passwordErrorMessage= "Invalid Password"
        }
        userError.value = userErrorMessage

        return isValidEmail && isValidPassword
    }

    private fun getPerson(email : String, password: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val user : User = repository.getPerson(email,password)
            withContext(Dispatchers.Main){
                liveUser.value = user
            }
        }
    }

    // Signup Button Function
    fun signupButton() {

        onSignUpResponse2.value = true
//        mContext.startActivity(
//            Intent(
//                mContext,
//                SignupActivity::class.java
//            ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_NO_HISTORY)
//        )

    }
}