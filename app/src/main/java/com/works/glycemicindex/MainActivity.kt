package com.works.glycemicindex

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.ListView
import android.widget.SearchView
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.works.glycemicindex.databinding.ActivityMainBinding
import com.works.glycemicindex.db.DB
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var bind: ActivityMainBinding
    lateinit var DB: DB


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)
        setSupportActionBar(bind.appBarMain.toolbar)

        window.statusBarColor = ContextCompat.getColor(this, R.color.ic_launcher_background)

        DB = DB(this)

        if (DB.allFood().size > 0) {
            Log.d("DB", "Database Okundu")
        } else {
            Log.d("DB", "Html Parsed")
            val i = Intent(this, SplashActivity::class.java)
            startActivity(i)
        }

        val drawerLayout: DrawerLayout = bind.drawerLayout
        val navView: NavigationView = bind.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)


        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_add_food, R.id.nav_add_table, R.id.nav_settings
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

}