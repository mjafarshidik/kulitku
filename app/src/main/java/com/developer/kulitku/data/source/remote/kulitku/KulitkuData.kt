package com.developer.kulitku.data.source.remote.kulitku

import com.developer.kulitku.R

object KulitkuData {
    private val title = arrayOf(
        "Bisul",
        "Bisul",
        "Jerawat",
        "Jerawat",
        "Bisul",
        "Bisul",
        "Jerawat",
        "Jerawat"
    )

    private val date = arrayOf(
        "18 Agustus 2022, 14:59:12",
        "19 Agustus 2022, 07:12:51",
        "20 Agustus 2022, 14:59:12",
        "21 Agustus 2022, 14:59:12",
        "22 Agustus 2022, 14:59:12",
        "23 Agustus 2022, 14:59:12",
        "24 Agustus 2022, 14:59:12",
        "25 Agustus 2022, 14:59:12"
    )

    private val image = intArrayOf(
        R.drawable.bisul,
        R.drawable.bisul,
        R.drawable.jerawat,
        R.drawable.jerawat,
        R.drawable.bisul,
        R.drawable.bisul,
        R.drawable.jerawat,
        R.drawable.jerawat
    )

    val listData: ArrayList<KulitkuResponse>
        get() {
            val list = arrayListOf<KulitkuResponse>()
            for (position in title.indices) {
                val hero = KulitkuResponse()
                hero.title = title[position]
                hero.dateTime = date[position]
                hero.photo = image[position]

                list.add(hero)
            }
            return list
        }
}