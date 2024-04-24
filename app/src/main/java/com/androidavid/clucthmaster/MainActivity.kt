package com.androidavid.clucthmaster


import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.androidavid.clucthmaster.database.NoteDatabase
import com.androidavid.clucthmaster.databinding.ActivityMainBinding
import com.androidavid.clucthmaster.repository.DiscoRepositoryImpl
import com.androidavid.clucthmaster.repository.HomeRepositoryImpl
import com.androidavid.clucthmaster.repository.MainRepositoryImpl
import com.androidavid.clucthmaster.repository.NoteRepository
import com.androidavid.clucthmaster.repository.PrensaRepositoryImpl
import com.androidavid.clucthmaster.repository.RodamientoRepositoryimpl
import com.androidavid.clucthmaster.viewmodel.DiscosViewModel
import com.androidavid.clucthmaster.viewmodel.DiscosViewModelFactory
import com.androidavid.clucthmaster.viewmodel.HomeFragViewModel
import com.androidavid.clucthmaster.viewmodel.HomeFragViewModelFactory
import com.androidavid.clucthmaster.viewmodel.MainViewModel
import com.androidavid.clucthmaster.viewmodel.MainViewModelFactory
import com.androidavid.clucthmaster.viewmodel.NoteViewModel
import com.androidavid.clucthmaster.viewmodel.NoteViewModelFactory
import com.androidavid.clucthmaster.viewmodel.PrensasViewModel
import com.androidavid.clucthmaster.viewmodel.PrensasViewModelFactory
import com.androidavid.clucthmaster.viewmodel.RodamientosViewModel
import com.androidavid.clucthmaster.viewmodel.RodamientosViewModelFactory
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    lateinit var viewModel: MainViewModel
    lateinit var homeFragViewModel: HomeFragViewModel
    lateinit var prensaViewModel: PrensasViewModel
    lateinit var discosViewModel: DiscosViewModel
    lateinit var rodamientosViewModel: RodamientosViewModel
    lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        askNotificationPermission()
        tokenNew()
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.findNavController()

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.fragmentHome,
                R.id.fragmentPrensas,
                R.id.fragmentDiscos,
                R.id.fragmentRodamientos,
                R.id.fragmentNotes,
                R.id.detailsFragmentProducts
            )
        )
        setSupportActionBar(binding.materialToolbar)
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.bottomNavView.setupWithNavController(navController)
        setupViewModel()

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragmentContainerView)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_compartir -> {
                viewModel.compartirApp(this)
                true
            }

            R.id.menu_email -> {
                viewModel.enviarEmail(this)
                true
            }

            R.id.menu_settings -> {
                Toast.makeText(this, "Proximamente", Toast.LENGTH_LONG).show()
                true
            }

            else -> false
        }
    }

    private fun setupViewModel() {
        val noteRepository = NoteRepository(NoteDatabase(this))
        val api = RetrofitClient.getClient().create(ClutchAPI::class.java)
        val homeRepository = HomeRepositoryImpl(api)
        val repository = MainRepositoryImpl()
        val prensaRepository = PrensaRepositoryImpl(api)
        val discoRepository = DiscoRepositoryImpl(api)
        val rodamientoRepository = RodamientoRepositoryimpl(api)

        val viewModelProviderFactory = NoteViewModelFactory(application, noteRepository)
        val mainViewModelFactory = MainViewModelFactory(repository)
        val prensasViewModelFactory = PrensasViewModelFactory(prensaRepository)
        val discosViewModelFactory = DiscosViewModelFactory(discoRepository)
        val rodamientosViewModelFactory = RodamientosViewModelFactory(rodamientoRepository)
        val homeFragViewModelFactory = HomeFragViewModelFactory(homeRepository)

        noteViewModel = ViewModelProvider(this, viewModelProviderFactory)[NoteViewModel::class.java]
        viewModel = ViewModelProvider(this, mainViewModelFactory)[MainViewModel::class.java]
        prensaViewModel =
            ViewModelProvider(this, prensasViewModelFactory)[PrensasViewModel::class.java]
        discosViewModel =
            ViewModelProvider(this, discosViewModelFactory)[DiscosViewModel::class.java]
        rodamientosViewModel =
            ViewModelProvider(this, rodamientosViewModelFactory)[RodamientosViewModel::class.java]
        homeFragViewModel =
            ViewModelProvider (this,homeFragViewModelFactory)[HomeFragViewModel::class.java]

    }
    private fun askNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) ==
                PackageManager.PERMISSION_GRANTED) {

            } else {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission(),
    ) { isGranted: Boolean ->
        if (isGranted) {

        } else {

        }
    }

    private fun tokenNew() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("FCM TOKEN", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }
            val token = task.result
            Log.d("FCM TOKEN", token.toString())
        })
    }


}