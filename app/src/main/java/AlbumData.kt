package com.example.lunabee_proto

data class AlbumData(
    val title: String,
    val artist: String,
    val year: Int,
    val cover: String,
    val tracklist: List<String>
)
