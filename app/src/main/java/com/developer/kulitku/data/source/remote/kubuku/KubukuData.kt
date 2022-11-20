package com.developer.kulitku.data.source.remote.kubuku

import com.developer.kulitku.R

object KubukuData {
    private val title = arrayOf(
        "Tinea Versicolor", "Jerawat Pustula", "Dermatitis", "Milia", "Bisul"
    )

    private val date = arrayOf(
        "Pengertian, Gejala, Bentuk Anatomi, Perawatan",
        "Pengertian, Gejala, Bentuk Anatomi, Perawatan",
        "Pengertian, Gejala, Bentuk Anatomi, Perawatan",
        "Pengertian, Gejala, Bentuk Anatomi, Perawatan",
        "Pengertian, Gejala, Bentuk Anatomi, Perawatan"
    )

    private val image = intArrayOf(
        R.drawable.tinea,
        R.drawable.pustula,
        R.drawable.dermatitis,
        R.drawable.milia,
        R.drawable.bisul_2
    )

    val listData: ArrayList<KubukuResponse>
        get() {
            val list = arrayListOf<KubukuResponse>()
            for (position in title.indices) {
                val hero = KubukuResponse()
                hero.title = title[position]
                hero.desc = date[position]
                hero.photo = image[position]

                list.add(hero)
            }
            return list
        }
}