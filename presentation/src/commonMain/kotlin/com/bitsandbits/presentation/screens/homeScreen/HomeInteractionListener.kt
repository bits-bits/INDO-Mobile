package com.bitsandbits.presentation.screens.homeScreen

interface HomeInteractionListener {
    fun onClickSearch()
    fun onChangeQuery(query: String)
    fun onClickSearchBar()
    fun onClickScreen()
}