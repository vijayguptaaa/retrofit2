package com.example.loginvalidation.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast

import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.loginvalidation.BR
import com.example.loginvalidation.R
import com.example.loginvalidation.base.BaseActivity
import com.example.loginvalidation.databinding.ActivityLoginBinding
import com.example.loginvalidation.roomdb.PersonDatabase
import com.example.loginvalidation.roomdb.PersonRepository
import com.example.loginvalidation.viewModelFactories.LoginViewModelFactory
import com.example.loginvalidation.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {
    private lateinit var mViewBinding: ActivityLoginBinding
    private lateinit var mViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        val dao = PersonDatabase.getInstance(application).personDao()
        val repository = PersonRepository(dao)
        mViewModel =
            ViewModelProvider(this, LoginViewModelFactory(repository))[LoginViewModel::class.java]

        mViewBinding.loginViewModel = mViewModel
        mViewBinding.lifecycleOwner = this

        mViewModel.onSignUpResponse2.observe(this, Observer {
            if (it) {
                startActivity(Intent(this, SignupActivity::class.java))
            }
        })

        mViewModel.liveUser.observe(this, Observer {
            if (it != null){
                startActivity(Intent(this,HomeActivity::class.java))
            }
        })

//        mViewBinding = getViewDataBinding()
//        mViewBinding.loginViewModel = mViewModel
//
//        mViewModel.showError.observe(this, Observer { error->
//            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
//        })
//
//    }
//
//    override fun getBindingVariable(): Int {
//        return BR.loginViewModel
//    }
//
//    override fun getViewModel(): LoginViewModel {
//        val mLoginViewModel: LoginViewModel by viewModels()
//        this.mViewModel = mLoginViewModel
//        return mLoginViewModel
//    }
//
//    override fun getLayoutId(): Int {
//        return R.layout.activity_login
//    }

    }
}
