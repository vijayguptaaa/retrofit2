package com.example.loginvalidation.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.loginvalidation.R
import com.example.loginvalidation.databinding.ActivitySignupBinding
import com.example.loginvalidation.roomdb.PersonDatabase
import com.example.loginvalidation.roomdb.PersonRepository
import com.example.loginvalidation.viewModelFactories.SignUpViewModelFactory
import com.example.loginvalidation.viewmodel.SignUpViewModel

class SignupActivity : AppCompatActivity() {

    private lateinit var mViewBinding: ActivitySignupBinding
    private lateinit var mViewModel: SignUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mViewBinding = DataBindingUtil.setContentView(this, R.layout.activity_signup)
        val dao = PersonDatabase.getInstance(application).personDao()
        val repository = PersonRepository(dao)
        mViewModel = ViewModelProvider(this,SignUpViewModelFactory(repository))[SignUpViewModel::class.java]

        mViewBinding.signupViewModel = mViewModel
        mViewBinding.lifecycleOwner = this

        mViewModel.onSignUpResponse.observe(this, Observer {
            if (it) {
                startActivity(Intent(this, LoginActivity::class.java))
            }
        })
        mViewModel.onLoginResponse.observe(this, Observer {
            if (it) {
                startActivity(Intent(this, LoginActivity::class.java))
            }
        })

    }


//
//        mViewBinding.signupViewModel = mViewModel
//        mViewModel.showError.observe(this, Observer { error->
//            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
//        })
//
//    }
//
//    override fun getBindingVariable(): Int {
//        return BR.signupViewModel
//    }
//
//    override fun getViewModel(): SignUpViewModel {
//        val mSignUpViewModel: SignUpViewModel by viewModels()
//        this.mViewModel = mSignUpViewModel
//        return mSignUpViewModel
//    }
//
//    override fun getLayoutId(): Int {
//        return R.layout.activity_signup
//    }
//
//    override fun onBackPressed() {
//        intent = Intent(this, LoginActivity::class.java)
//        startActivity(intent)
//        finish()
//    }
}