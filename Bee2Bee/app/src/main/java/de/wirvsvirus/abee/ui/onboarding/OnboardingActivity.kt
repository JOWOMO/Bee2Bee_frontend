package de.wirvsvirus.abee.ui.onboarding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import de.wirvsvirus.abee.R.id
import de.wirvsvirus.abee.R.layout

class OnboardingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(layout.activity_onboarding)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(
                    id.root_layout,
                    OnboardingFragment(), "onboardingFragment")
                .commit()
        }
    }
}
