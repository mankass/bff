package com.manka.bff.domain

import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDate


@Schema(description = "userDto")
data class UserDto (
    @Schema(description = "login")
    val login: String,

    @Schema(description = "name")
    val firstName: String,

    @Schema(description = "surname")
    val surname: String,

    @Schema(description = "createdData")
    val createdData: LocalDate,

    @Schema(description = "lastLoginDate")
    val lastLogin: LocalDate,

    @Schema(description = "email")
    val email: String,
)