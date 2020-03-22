package de.wirvsvirus.abee

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import de.wirvsvirus.abee.fragments.LoginFragment

class LoginRegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_register)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.entry_container, LoginFragment(), "tag1")
            .commit()
    }
}
