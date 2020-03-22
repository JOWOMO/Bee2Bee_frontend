package de.wirvsvirus.abee.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.common.api.internal.BaseImplementation
import de.wirvsvirus.abee.R
import de.wirvsvirus.abee.data.APIManager
import de.wirvsvirus.abee.data.AuthService
import de.wirvsvirus.abee.data.User
import kotlinx.android.synthetic.main.fragment_register.*

class RegisterFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_register.setOnClickListener { register() }

        link_login.setOnClickListener {
            fragmentManager!!.popBackStack()
        }
    }

    private fun register() {
        activity?.finish()

        /* TODO....
        val companyName = register_company_name.text.toString()
        val firstName = register_first_name.text.toString()
        val lastName = register_last_name.text.toString()
        val email = register_email.text.toString()
        val secret = register_secret.text.toString()

        if (companyName.isNotEmpty() && email.isNotEmpty() && secret.isNotEmpty()) {
            val user = User(0, 0, email, firstName, lastName, secret)
            APIManager.postUser(user, object : APIManager.OnBee2BeeVoidResponseCallback {
                override fun onResponse() {
                    //APIManager.loginUser()
                }

                override fun onError() {
                }

            })
        } else {
            // ???
        }
        */
    }

}
