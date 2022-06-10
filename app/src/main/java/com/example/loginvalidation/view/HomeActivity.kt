package com.example.loginvalidation.view

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import com.example.loginvalidation.databinding.ActivityHomeBinding
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.loginvalidation.MainAdapter
import com.example.loginvalidation.R
import com.example.loginvalidation.RetrofitService
import com.example.loginvalidation.model.MainRepository
import com.example.loginvalidation.view.fragments.HomeFragment
import com.example.loginvalidation.view.fragments.MessageFragment
import com.example.loginvalidation.view.fragments.RateFragment
import com.example.loginvalidation.viewModelFactories.HomeViewModelFactory
import com.example.loginvalidation.viewmodel.HomeActivityViewModel

class HomeActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityHomeBinding
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    lateinit var viewModel: HomeActivityViewModel
    private val retrofitService = RetrofitService.getInstance()
    val adapter = MainAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        this.title = "Home Page"

        //get viewmodel instance using ViewModelProvider.Factory
        viewModel = ViewModelProvider(
                this,HomeViewModelFactory(MainRepository(retrofitService))).get(HomeActivityViewModel::class.java)

        //set adapter in recyclerview
        mBinding.recyclerview.adapter =adapter


        //set adapter in recyclerview
        mBinding.recyclerview.adapter = adapter

        //the observer will only receive events if the owner(activity) is in active state
        //invoked when movieList data changes
        viewModel.movieList.observe(this, Observer {
            Log.d(TAG, "movieList: $it")
            adapter.setMovieList(it)
        })

        //invoked when a network exception occurred
        viewModel.errorMessage.observe(this, Observer {
            Log.d(TAG, "errorMessage: $it")
        })

        viewModel.getAllMovies()


        setUpView()

        mBinding.navView.setNavigationItemSelectedListener {

            it.isChecked = true

            when(it.itemId){
                R.id.nav_home -> replaceFragment(HomeFragment(), it.title.toString())
                R.id.message -> replaceFragment(MessageFragment(), it.title.toString())
                R.id.rateUs -> replaceFragment(RateFragment(), it.title.toString())
                R.id.nav_share -> Toast.makeText(applicationContext,"Clicked share button",Toast.LENGTH_SHORT).show()
                R.id.contact -> Toast.makeText(applicationContext,"Clicked contact",Toast.LENGTH_SHORT).show()
            }
            true
        }
    }
    private fun replaceFragment(fragment: Fragment,title: String){

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout,fragment)
        fragmentTransaction.commit()
        mBinding.drawerLayout.closeDrawers()
        setTitle(title)

    }

    private fun setUpView(){
        setUpDrawerLayout()
    }

    private fun setUpDrawerLayout(){
        setSupportActionBar(mBinding.appBar)
        actionBarDrawerToggle = ActionBarDrawerToggle(this,mBinding.drawerLayout,R.string.open,R.string.close)
        actionBarDrawerToggle.syncState()
/*
actionBarDrawerToggle.isDrawerIndicatorEnabled = true
mBinding.drawerLayout.addDrawerListener(actionBarDrawerToggle)
*/

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}