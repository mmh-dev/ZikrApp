package com.mmh.zikrapp.entity

data class DuaItem(
    val arabic: String,
    var clickedCount: Int,
    val totalCount: Int,
    val title: String,
    val transliteration: String,
    val uzbek: String
)