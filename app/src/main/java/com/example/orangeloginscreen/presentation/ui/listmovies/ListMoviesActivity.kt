package com.example.orangeloginscreen.presentation.ui.listmovies

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.bumptech.glide.Glide
import com.example.orangeloginscreen.R
import com.example.orangeloginscreen.RecyclerView.adapter.ListMoviesAdapter
import com.example.orangeloginscreen.RecyclerView.adapter.MovieListener
import com.example.orangeloginscreen.databinding.ActivityListMoviesBinding
import com.example.orangeloginscreen.domain.model.Movie
import com.example.orangeloginscreen.domain.model.User
import com.example.orangeloginscreen.presentation.ui.loadingdialog.LoadingDialog
import com.example.orangeloginscreen.presentation.ui.login.LoginActivity
import com.example.orangeloginscreen.presentation.ui.moviedetails.MovieDetailsActivity
import com.google.android.material.navigation.NavigationView
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListMoviesActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{

    private val binding by lazy {
        ActivityListMoviesBinding.inflate(layoutInflater)
    }

    private val user by lazy { intent.getParcelableExtra<User>(User::javaClass.name) }

    private val drawer by lazy {
        binding.listMoviesDrawerLayout
    }

    private val listMoviesViewModel: ListMoviesViewModel by viewModel()

    private val loadingDialog by lazy {
        LoadingDialog(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configureToolbar()
        configureMenuInformations()
        listMovies()
        onObserverState()
    }

    private val oneMovieSelected = object : MovieListener {
        override fun invoke(movie: Movie) {
            startActivity(Intent(this@ListMoviesActivity, MovieDetailsActivity::class.java).apply {
                putExtra(Movie::javaClass.name, movie)
            })
        }
    }

    private fun configureAdapter(movies: List<Movie>) {
        binding.recyclerView.adapter = ListMoviesAdapter(
            movies, oneMovieSelected
        )
    }

    private fun configureToolbar(){
        setSupportActionBar(binding.listMoviesToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val toggle = ActionBarDrawerToggle(this, drawer, binding.listMoviesToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()
        binding.listMoviesNavView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.nav_signout -> {
                startActivity(Intent(this@ListMoviesActivity, LoginActivity::class.java))
                finish()
            }
        }
        return true
    }

    override fun onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START)
            return
        }

        super.onBackPressed()
    }

    private fun configureMenuInformations(){
        user?.let {
            val menuUserUsername = binding.listMoviesNavView.getHeaderView(0).findViewById<TextView>(R.id.nav_header_username)
            menuUserUsername.text = getString(R.string.header_hello, it.username)
            val menuUserAvatar = binding.listMoviesNavView.getHeaderView(0).findViewById<ImageView>(R.id.nav_header_user_avatar)
            Glide.with(menuUserAvatar).load(it.avatar).into(menuUserAvatar)
        }
    }

    private fun listMovies(){
        listMoviesViewModel.listAllMovies()
    }

    private fun onObserverState(){
        listMoviesViewModel.viewState.observe(this) {
            when(it){
                is ListMoviesState.IDLE -> {}
                is ListMoviesState.onError -> showError(it.message)
                is ListMoviesState.onListLoaded -> onListLoaded(it.movies)
                is ListMoviesState.onLoading -> onLoading()
                else -> {}
            }


        }
    }

    private fun onListLoaded(movies: List<Movie>){
        loadingDialog.finalizeLoadingDialog()
        configureAdapter(movies)
    }

    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun onLoading(){
        loadingDialog.startLoadingDialog(getString(R.string.progress_loading_movies_list))
    }
}
