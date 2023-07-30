package com.apex.codeassesment.ui.main

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.apex.codeassesment.R
import com.apex.codeassesment.data.model.Response.Info
import com.apex.codeassesment.data.model.Response.Results
import com.apex.codeassesment.data.model.Response.UserResponse
import com.apex.codeassesment.data.repository.UserRepository
import com.apex.codeassesment.data.model.User
import com.apex.codeassesment.databinding.ActivityMainBinding
import com.apex.codeassesment.di.DaggerMainComponent
import com.apex.codeassesment.di.MainComponent
import com.apex.codeassesment.di.ViewModelComponent
import com.apex.codeassesment.ui.details.DetailsActivity
import com.bumptech.glide.Glide
import javax.inject.Inject

// TODO (5 points): Move calls to repository to Presenter or ViewModel.
// TODO (5 points): Use combination of sealed/Dataclasses for exposing the data required by the view from viewModel .
// TODO (3 points): Add tests for viewmodel or presenter.
// TODO (1 point): Add content description to images
// TODO (3 points): Add tests
// TODO (Optional Bonus 10 points): Make a copy of this activity with different name and convert the current layout it is using in
//  Jetpack Compose.
class MainActivity : AppCompatActivity(), UserAdapter.OnItemViewClickListener {

    // TODO (2 points): Convert to view binding
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var userRepository: UserRepository

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var model: MainViewModel
    private  var userList: ArrayList<User> = arrayListOf()
    private var randomUser: User = User()
    private var randomSavedUser: User = User()
        set(value) {
            // TODO (1 point): Use Glide to load images after getting the data from endpoints mentioned in RemoteDataSource
            val imageUrl = randomUser.picture?.large
            Glide.with(this).load(imageUrl).into(binding.mainImage)
            binding.mainImage.contentDescription = "Picture of user in Large"

            // userImageView.setImageBitmap(refreshedUser.image)
            binding.mainName.text = value.name!!.first
            binding.mainEmail.text = value.email
            field = value
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedContext = this

        (applicationContext as MainComponent.Injector).mainComponent.inject(this)
        model = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        model.singleUserLiveData.observe(this) {
            randomUser = it
            binding.mainName.text = it.name!!.first.toString()
            val imageUrl = randomUser.picture?.large
            Glide.with(this).load(imageUrl).into(binding.mainImage)
            binding.mainImage.contentDescription = "Picture of user in Large"
            binding.mainEmail.text = it.email

        }
        model.userListLiveData.observe(this) {
            userList.addAll(it)
        }
        model.fetchUsers()
        binding.mainUserRv.layoutManager = LinearLayoutManager(this)
        val adapter = UserAdapter(userList, this, this)
        binding.mainUserRv.adapter = adapter

        randomSavedUser = userRepository.getSavedUser()

        binding.mainSeeDetailsButton.setOnClickListener { startActivity(randomSavedUser) }

        binding.mainRefreshButton.setOnClickListener {
            randomSavedUser = userRepository.getUser(true)
        }

        binding.mainUserListButton.setOnClickListener {
            val users = userRepository.getUsers()
            userList.clear()
            userList.addAll(users)
            adapter.notifyDataSetChanged()
        }
    }

    // TODO (2 points): Convert to extenstion function.
    private fun navigateDetails(user: User) {
        val putExtra = Intent(this, DetailsActivity::class.java).putExtra("saved-user-key", user)
        startActivity(putExtra)
    }

    private fun Activity.startActivity(user: User) {
        val putExtra = Intent(this, DetailsActivity::class.java).putExtra("saved-user-key", user)
        startActivity(putExtra)
    }

    companion object {
        var sharedContext: Context? = null
    }

    override fun onItemClick(user: User) {
        startActivity(user)
    }
}
