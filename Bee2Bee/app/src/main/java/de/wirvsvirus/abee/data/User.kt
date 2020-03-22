package de.wirvsvirus.abee.data

data class User (
    var id: Int,
    var companyId: Int,
    var email: String,
    var name: String?,
    var first_name: String?,
    var secret: String
)