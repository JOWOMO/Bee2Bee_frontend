package de.wirvsvirus.abee.data

object MockData {

    val skillset = listOf(
        "Führerscheine",
        "Kann lange stehen",
        "Kann lange sitzen",
        "Kann viel laufen",
        "Ersthelfer" ,
        "Kann \"schwere Gegenstände\" tragen",
        "Medizinische Erfahrung",
        "Sanitätshelfer" ,
        "Arzt" ,
        "Praxismitarbeitern" ,
        "Krankenschwester" ,
        "Kriesenmanagement" ,
        "Desinfektion & Reinigung" ,
        "HomeOffice" ,
        "Arbeitszeitraum" ,
        "Vormittag" ,
        "Nachmittag" ,
        "Nachtschicht" ,
        "Reisebereitschaft"
    )

    fun getUser(userId: Int) = User(userId, 2345, "email@wirvsvirus.de", "Lastname", "Firstname", "this_is_secret")

    fun getCompany(companyId: Int) = Company(2345, "Kleinunternehmen GbR")

    fun getPosting(postingId: Int) =
        Posting(
            postingId,
            1111,
            "Dies ist eine Beschreibung des Postings $postingId",
            "11011",
            "Berlin",
            arrayListOf(hashMapOf(5 to "Skill_1, Skill_2, Skill_3, Skill_4"), hashMapOf(2 to "Skill_2, Skill_3, Skill_5"), hashMapOf(15 to "Skill_1, Skill_8, Skill_6, Skill_9, Skill_5, Skill_10, Skill_16"), hashMapOf(1 to "Skill_1, Skill_9")),
            PostingType.DEMAND.value,
            "",
            ""
        )

    fun getPostings(type: Int, longitude: String, latitude: String, radius: Int) = arrayListOf(
        Posting(
            9111,
            1111,
            "Dies ist eine Beschreibung des Postings 9111",
            "11011",
            "Berlin",
            arrayListOf(hashMapOf(5 to "Skill_1, Skill_2, Skill_3, Skill_4"), hashMapOf(2 to "Skill_2, Skill_3, Skill_5"), hashMapOf(15 to "Skill_1, Skill_8, Skill_6, Skill_9, Skill_5, Skill_10, Skill_16"), hashMapOf(1 to "Skill_1, Skill_9")),
            PostingType.DEMAND.value,
            "",
            ""
        ),
        Posting(
            9112,
            1112,
            "Dies ist eine Beschreibung des Postings 9112",
            "11011",
            "Berlin",
            arrayListOf(hashMapOf(5 to "Skill_1, Skill_2, Skill_3, Skill_4"), hashMapOf(2 to "Skill_2, Skill_3, Skill_5"), hashMapOf(15 to "Skill_1, Skill_8, Skill_6, Skill_9, Skill_5, Skill_10, Skill_16"), hashMapOf(1 to "Skill_1, Skill_9")),
            PostingType.HOLDING.value,
            "",
            ""
        ),
        Posting(
            9113,
            1113,
            "Dies ist eine Beschreibung des Postings 9113",
            "11011",
            "Berlin",
            arrayListOf(hashMapOf(5 to "Skill_1, Skill_2, Skill_3, Skill_4"), hashMapOf(2 to "Skill_2, Skill_3, Skill_5"), hashMapOf(15 to "Skill_1, Skill_8, Skill_6, Skill_9, Skill_5, Skill_10, Skill_16"), hashMapOf(1 to "Skill_1, Skill_9")),
            PostingType.DEMAND.value,
            "",
            ""
        ),
        Posting(
            9114,
            1114,
            "Dies ist eine Beschreibung des Postings 9114",
            "11011",
            "Berlin",
            arrayListOf(hashMapOf(5 to "Skill_1, Skill_2, Skill_3, Skill_4"), hashMapOf(2 to "Skill_2, Skill_3, Skill_5"), hashMapOf(15 to "Skill_1, Skill_8, Skill_6, Skill_9, Skill_5, Skill_10, Skill_16"), hashMapOf(1 to "Skill_1, Skill_9")),
            PostingType.HOLDING.value,
            "",
            ""
        ),
        Posting(
            9115,
            1115,
            "Dies ist eine Beschreibung des Postings 9115",
            "11011",
            "Berlin",
            arrayListOf(hashMapOf(5 to "Skill_1, Skill_2, Skill_3, Skill_4"), hashMapOf(2 to "Skill_2, Skill_3, Skill_5"), hashMapOf(15 to "Skill_1, Skill_8, Skill_6, Skill_9, Skill_5, Skill_10, Skill_16"), hashMapOf(1 to "Skill_1, Skill_9")),
            PostingType.DEMAND.value,
            "",
            ""
        )
    )
}