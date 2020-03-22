package de.wirvsvirus.abee.data

object AuthService {

    data class AuthUser (
        var email: String,
        var session: String,
        var companyName: String,
        var companyId: Int
    )

    var authUser: AuthUser? = null
        get() {
            val email = SettingsManager.getPreference("email", "")
            val session = SettingsManager.getPreference("session", "")
            val companyName = SettingsManager.getPreference("companyName", "")
            val companyId = SettingsManager.getPreference("companyId", "").toIntOrNull()
            return if (email.isNotEmpty() && session.isNotEmpty() && companyName.isNotEmpty() && companyId != null) {
                AuthUser(email, session, companyName, companyId)
            } else {
                null
            }
        }
        private set

    fun loginUser(email: String, session: String, conpanyName: String, conpanyId: Int) {
        SettingsManager.savePreference("email", email)
        SettingsManager.savePreference("session", session)
        SettingsManager.savePreference("companyName", conpanyName)
        SettingsManager.savePreference("companyId", conpanyId)
    }

    fun logoutUser() {
        SettingsManager.savePreference("email", "")
        SettingsManager.savePreference("session", "")
        SettingsManager.savePreference("companyName", "")
        SettingsManager.savePreference("companyId", "")
        authUser = null
    }
}