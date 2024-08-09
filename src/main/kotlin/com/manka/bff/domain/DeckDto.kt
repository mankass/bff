package com.manka.bff.domain

import io.swagger.v3.oas.annotations.media.Schema

data class DeckDto(

    @Schema(description = "deck name")
    var name:String,

    @Schema(description = "is personal deck")
    var isPersonal: String,

    var listUsers: MutableList<UserDto>
)