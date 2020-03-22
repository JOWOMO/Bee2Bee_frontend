package de.wirvsvirus.abee.ui.onboarding

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.Fragment
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.input.input
import com.afollestad.materialdialogs.list.listItemsMultiChoice
import de.wirvsvirus.abee.DetailActivity
import de.wirvsvirus.abee.R
import de.wirvsvirus.abee.data.AuthService
import de.wirvsvirus.abee.data.MockData
import de.wirvsvirus.abee.data.Posting
import de.wirvsvirus.abee.data.PostingType
import kotlinx.android.synthetic.main.fragment_onboarding.*

class OnboardingFragment :Fragment(R.layout.fragment_onboarding) {

    var newPosting: Posting? = null

    val watcher=object:TextWatcher{
        override fun afterTextChanged(s: Editable?){
            if(s.toString().length>4){
                stadt.setText("Berlin")
            }
        }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        plz.addTextChangedListener(watcher)

        weiter.setOnClickListener { next() }
        close_button.setOnClickListener { activity!!.finish() }
    }

    private fun next() {
        val firmenname = firmenname.text.toString()
        val firmenadresse = firmenadresse.text.toString()
        val plz = plz.text.toString()
        val stadt = stadt.text.toString()

        if (firmenname.isNotEmpty() && plz.isNotEmpty() && stadt.isNotEmpty() && firmenadresse.isNotEmpty()) {
            newPosting = Posting(0, AuthService.authUser?.companyId ?: 0, "", plz, stadt, null, PostingType.DEMAND.value, "", "")
            showDialog()
        } else {
            // ???
        }
    }


    fun showDialog(){
        MaterialDialog(context!!).show {

            title(text="Suchart wählen")
            message(text="Auf der Suche nach Arbeitern oder auf der Suche nach Unternehmen die ihre Arbeiter wollen?")
            positiveButton(R.string.bestand){
                newPosting?.type = PostingType.DEMAND.value
                showListDialog()
            }
            negativeButton(R.string.bedarf){
                newPosting?.type = PostingType.HOLDING.value
                showListDialog()
            }
        }
    }

    fun showListDialog() {
        val titleText = if (newPosting?.type == PostingType.DEMAND.value) "Markieren Sie hier die Bereiche in denen Sie Arbeiter anbieten können" else "Markieren Sie hier die Bereiche in denen Sie Arbeiter suchen"
        var count = 0
        var skills = ""

        MaterialDialog(context!!).show {
            title(text="Bitte Bereiche markieren")
            message(text=titleText)
            input(allowEmpty = false, inputType = InputType.TYPE_CLASS_NUMBER) { dialog, text ->
                count = text.toString().toIntOrNull() ?: 1
            }
            listItemsMultiChoice(items= MockData.skillset, waitForPositiveButton = true, allowEmptySelection = false) { dialog, indices, items ->
                skills = items.joinToString(", ") // TODO: THIS IS NOT CALLED
            }
            positiveButton(text="Fertig"){
                addPersons(count, skills, true)
            }
            negativeButton(text = "Weiter hinuf.") {
                addPersons(count, skills)
                showListDialog()
            }
        }
    }

    private fun addPersons(count: Int, skills: String, shouldCancel: Boolean = false) {
        startActivity(Intent(context, DetailActivity::class.java))
        activity!!.finish()

        /* TODO....
        val person: HashMap<Int, String> = hashMapOf(count to skills)
        newPosting?.persons?.add(person)

        val newPosting = newPosting
        if (shouldCancel && newPosting != null) {
            activity!!.finish()

            APIManager.postPosting(newPosting, object : APIManager.OnBee2BeeVoidResponseCallback {
                override fun onResponse() {
                    activity!!.finish()
                    // TODO show detail view
                }

                override fun onError() {
                    // ???
                }
            })
        }
        */
    }
}