package com.developer.kulitku.data.source.remote.kubaca

object KubacaData {
    private val title = arrayOf(
        "Tips untuk Kulit Kering",
        "Tips untuk Kulit Berminyak",
        "Tips untuk Kulit Kering",
        "Tips untuk Kulit Berminyak",
        "Tips untuk Kulit Kering",
        "Tips untuk Kulit Berminyak",
        "Tips untuk Kulit Kering",
        "Tips untuk Kulit Berminyak"
    )

    private val date = arrayOf(
        "Selasa, 16 Agustus 2022",
        "Selasa, 16 Agustus 2022",
        "Selasa, 16 Agustus 2022",
        "Selasa, 16 Agustus 2022",
        "Selasa, 16 Agustus 2022",
        "Selasa, 16 Agustus 2022",
        "Selasa, 16 Agustus 2022",
        "Selasa, 16 Agustus 2022"
    )


    val listData: ArrayList<KubacaResponse>
        get() {
            val list = arrayListOf<KubacaResponse>()
            for (position in title.indices) {
                val hero = KubacaResponse()
                hero.title = title[position]
                hero.date = date[position]

                list.add(hero)
            }
            return list
        }
}