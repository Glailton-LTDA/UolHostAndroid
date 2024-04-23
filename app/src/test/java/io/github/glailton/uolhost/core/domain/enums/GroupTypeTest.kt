package io.github.glailton.uolhost.core.domain.enums

import io.github.glailton.uolhost.R
import io.kotest.matchers.shouldBe
import org.junit.Test

class GroupTypeTest {
    @Test
    fun `should return GroupType value`() {
        val groupType = GroupType.fromValue(R.string.justice_league)

        groupType shouldBe GroupType.JUSTICE_LEAGUE
    }
}