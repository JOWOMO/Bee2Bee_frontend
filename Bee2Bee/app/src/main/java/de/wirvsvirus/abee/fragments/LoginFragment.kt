package de.wirvsvirus.abee.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import de.wirvsvirus.abee.R
import de.wirvsvirus.abee.ui.onboarding.OnboardingActivity
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_login.setOnClickListener { login() }

        link_signup.setOnClickListener{
            with(fragmentManager!!.beginTransaction()){
                replace(R.id.entry_container,
                    RegisterFragment()
                )
                addToBackStack(null)
                commit()
            }
        }
    }

    private fun login() {
        startActivity(Intent(context, OnboardingActivity::class.java))
        activity!!.finish()

        /* TODO...
        val userData = hashMapOf("email" to login_email.text.toString(), "secret" to login_secret.text.toString())
        APIManager.loginUser(userData, object : APIManager.OnBee2BeeResponseCallback<User> {
            override fun onResponse(data: User) {
                //APIManager.getCompany(data.)
                //AuthService.authUser = AuthService.AuthUser(data.email, "SOME_SESSION", )
            }

            override fun onError() {

            }
        })
         */
    }

}
