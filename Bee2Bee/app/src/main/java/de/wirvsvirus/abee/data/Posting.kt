package de.wirvsvirus.abee.data

data class Posting (
    var id: Int,
    var companyId: Int,
    var description: String?,
    var zipCode: String,
    var location: String,
    var persons: ArrayList<HashMap<Int, String>>?,
    var type: Int,
    var start_time: String = "",
    var daterage: String = ""
)

enum class PostingType(val value: Int) {
    DEMAND(1),
    HOLDING(2)
}

data class ListPosting(
    var id:Int,
    var name:String,
    var plzStadt:String,
    var type:String,
    var demandTrue:Boolean,
    var personAmountString:String,
    var skillAmountString:String
)
