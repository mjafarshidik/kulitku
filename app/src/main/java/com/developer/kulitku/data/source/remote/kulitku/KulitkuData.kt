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
                hero.photo = image[position]

                list.add(hero)
            }
            return list
        }
}