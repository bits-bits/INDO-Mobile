package com.bitsandbits.presentation.screens.HomeScreen

interface HomeInteractionListener {
    fun onClickSearch()
    fun onChangeQuery(query: String)
    fun downloadImage(url: String)
}