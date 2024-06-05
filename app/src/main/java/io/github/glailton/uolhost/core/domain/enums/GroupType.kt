package io.github.glailton.uolhost.core.domain.enums

import androidx.annotation.StringRes
import io.github.glailton.uolhost.R

enum class GroupType(@StringRes val value: Int) {
    JUSTICE_LEAGUE(R.string.justice_league),
    AVENGERS(R.string.avengers);

    companion object {
        fun fromValue(value: Int) = entries.first { it.value == value }
        fun fromName(name: String) = entries.first { it.name == name }

        fun getList() = entries.map { it.value }
    }
}