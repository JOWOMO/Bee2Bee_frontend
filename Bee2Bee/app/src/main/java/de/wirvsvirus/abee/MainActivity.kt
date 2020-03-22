package de.wirvsvirus.abee

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import de.wirvsvirus.abee.data.AuthService
import de.wirvsvirus.abee.enums.CurrentFragment
import de.wirvsvirus.abee.fragments.*

class MainActivity : AppCompatActivity() {

    private var currentFragment: CurrentFragment = CurrentFragment.MAP_FRAGMENT

    private var selectedFragment: Fragment? = null

    private var bottomNavigationView: BottomNavigationView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*if (Build.VERSION.SDK_INT >= 21) {

            // Set the status bar to dark-semi-transparentish
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            // Set paddingTop of toolbar to height of status bar.
            // Fixes statusbar covers toolbar issue
        }*/



        showLoginAtStart()

        bottomNavigationView = findViewById<View>(R.id.bottom_navigation) as BottomNavigationView
        bottomNavigationView!!.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        selectedFragment = MapFragment()
        addFragmentOnTop(selectedFragment)

    }

    fun showLoginAtStart(){
        if (AuthService.authUser == null) {
            startActivity(Intent(this,LoginRegisterActivity::class.java))
        }
    }


    fun addFragmentOnTop(fragment: Fragment?) {
        val fragmentManager = supportFragmentManager
        val transaction =
            fragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment!!)
        transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
        if (fragment is MapFragment) {
            currentFragment = CurrentFragment.MAP_FRAGMENT
            if (bottomNavigationView != null) {
                bottomNavigationView!!.setSelectedItemId(R.id.navigation_map)
            }
        } else if (fragment is MerthanListFragment) {
            currentFragment = CurrentFragment.LIST_FRAGMENT
            if (bottomNavigationView != null) {
                bottomNavigationView!!.setSelectedItemId(R.id.navigation_list)
            }
        }
        else if (fragment is ProfileFragment) {
            currentFragment = CurrentFragment.PROFILE_FRAGMENT
            if (bottomNavigationView != null) {
                bottomNavigationView!!.setSelectedItemId(R.id.navigation_profile)
            }
        }
        transaction.commit()
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        var change = false
        when (item.itemId) {
            R.id.navigation_map -> if (currentFragment !== CurrentFragment.MAP_FRAGMENT) {
                selectedFragment = MapFragment()
                change = true
            }
            R.id.navigation_list -> if (currentFragment !== CurrentFragment.LIST_FRAGMENT) {
                selectedFragment = MerthanListFragment()
                change = true
            }
            R.id.navigation_profile -> if (currentFragment !== CurrentFragment.PROFILE_FRAGMENT) {
                selectedFragment = ProfileFragment()
                change = true
            }
        }
        if (change) {
            addFragmentOnTop(selectedFragment)
        }
        true
    }

}
