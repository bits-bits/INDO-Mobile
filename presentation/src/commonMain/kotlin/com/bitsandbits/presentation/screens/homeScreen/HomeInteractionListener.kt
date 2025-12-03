package com.bitsandbits.presentation.screens.homeScreen

interface HomeInteractionListener {
    fun onChangeQuery(query: String)
    fun onClickClearQuery()
    fun onClickSearchBar()
    fun onClickScreen()
}