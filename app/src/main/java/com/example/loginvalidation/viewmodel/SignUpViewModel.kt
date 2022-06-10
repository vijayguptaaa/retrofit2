package com.example.loginvalidation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.loginvalidation.models.UserErrorModel
import com.example.loginvalidation.roomdb.User
import com.example.loginvalidation.roomdb.PersonRepository
import com.shivansh.officetask.utils.Valid
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignUpViewModel(private val repository: PersonRepository) : ViewModel()  {

    var firstName : MutableLiveData<String> = MutableLiveData()
    var lastName : MutableLiveData<String> = MutableLiveData()
    var email : MutableLiveData<String> = MutableLiveData()
    var phone : MutableLiveData<String> = MutableLiveData()
    var password : MutableLiveData<String> = MutableLiveData()
    var confirmPassword : MutableLiveData<String> = MutableLiveData()
    var userError : MutableLiveData<UserErrorModel> = MutableLiveData()
    private lateinit var user : User

    val onSignUpResponse : MutableLiveData<Boolean> = MutableLiveData()
    val onLoginResponse : MutableLiveData<Boolean> = MutableLiveData()


    init {
        onSignUpResponse.value = false
        onLoginResponse.value = false
        userError.value = UserErrorModel()
    }
//    private var dao = PersonDatabase.getInstance(application).personDao()
//    private var personRepository = PersonRepository(dao)
//    private val mContext = application

//    var showError : MutableLiveData<String> = MutableLiveData()
//    var firstName: ObservableField<String> = ObservableField("")
//    var lastName: ObservableField<String> = ObservableField("")
//    var email: ObservableField<String> = ObservableField("")
//    var password: ObservableField<String> = ObservableField("")

    /*var fullName : ObservableField<String>? = null
    var email : ObservableField<String>? = null
    var password : ObservableField<String>? = null*/

    // Format validation of First Name
    private fun validate() : Boolean {
        val userErrorMessage = UserErrorModel()

        val isValidFirstName = Valid.isValidFirstName(firstName.value.toString())
        val isValidLastName = Valid.isValidLastName(lastName.value.toString())
        val isValidEmail = Valid.isValidEmail(email.value.toString())
        val isValidPhone = Valid.isValidPhone(phone.value.toString())
        val isValidPassword = Valid.isValidPassword(password.value.toString())
//        val isConfirmPassword = Valid.isConfirmPassword(confirmPassword.toString())
        if(isValidFirstName){
            userErrorMessage.firstNameErrorMessage = "Field cannot be empty"
        }
        if(isValidLastName){
            userErrorMessage.lastNameErrorMessage = "Field Cannot be empty"
        }
        if(isValidPhone){
            userErrorMessage.phoneErrorMessage = "Inavalid Phone Number"
        }
        if(!isValidEmail){
            userErrorMessage.emailErrorMessage = "Invalid Email"
        }
        if(!isValidPassword){
            userErrorMessage.passwordErrorMessage = "Invalid Password"
        }
//        if(isConfirmPassword){
//            userErrorMessage.confirmPasswordErrorMessage = "Please Confirm Password"
//        }
        userError.value = userErrorMessage
        return !isValidFirstName && !isValidLastName && !isValidPhone && isValidEmail && isValidPassword
    }
    fun signupButton(){
        if (validate()){
            insertUser()
            onSignUpResponse.value = true
        }
    }
    fun loginButton(){
        onLoginResponse.value = true
    }
    // Save Data into Database
    private fun insertUser() {
        CoroutineScope(Dispatchers.IO).launch {
            user = User(0,firstName.value.toString(),lastName.value.toString()
                ,email.value.toString(),password.value.toString())
            repository.insert(user)
        }
    }
}
//    private fun firstNameValidation(): Boolean {
//        if (firstName.get().toString().isBlank()) {
//            showError.value = "Please Fill this Field"
//        } else {
//            return true
//        }
//        return false
//    }
//
//    // Format validation of Last Name
//    private fun lastNameValidation(): Boolean {
//        if (lastName.get().toString().isBlank()) {
//            showError.value = "Please Fill this Field"
//        } else {
//            return true
//        }
//        return false
//    }
//
//    // Format validation of Email
//    private fun emailValidation(): Boolean {
//        if (email.get().toString().isBlank()) {
//            showError.value = "Please Fill this Field"
//        } else if (!Patterns.EMAIL_ADDRESS.matcher(email.get().toString()).matches()) {
//            showError.value = "Please Enter a Valid Email"
//        } else {
//            return true
//        }
//        return false
//    }
//
//    // Format validation of Password
//    private fun passwordValidation(): Boolean {
//
//        if (password.get().toString().isBlank()) {
//            showError.value = "Please Fill this Field"
//        } else if (password.get().toString().length < 8) {
//            showError.value= "Password must be 8 Characters"
//        }
//        else {
//            return true
//        }
//        return false
//    }

    // Login button Function
//    fun loginButton() {
//        mContext.startActivity(Intent(mContext,LoginActivity::class.java
//            ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_NO_HISTORY)
//        )
//    }

    // Signup button function
//    fun signupButton() {
//        if (firstNameValidation() && lastNameValidation() && emailValidation() && passwordValidation()) {
//            savePerson()
//            mContext.startActivity(
//                Intent(
//                    mContext,
//                    LoginActivity::class.java
//                ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_NO_HISTORY)
//            )
//            showError.value = "Registered Successfully"
//            firstName.set("")
//            lastName.set("")
//            email.set("")
//            password.set("")
//        }
//        else{
//
//        }
//    }

