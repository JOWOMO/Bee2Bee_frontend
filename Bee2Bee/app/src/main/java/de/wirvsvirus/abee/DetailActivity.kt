package de.wirvsvirus.abee

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import de.wirvsvirus.abee.fragments.DetailFragment

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.detail_container, DetailFragment(), "tag1")
            .commit()
    }
}