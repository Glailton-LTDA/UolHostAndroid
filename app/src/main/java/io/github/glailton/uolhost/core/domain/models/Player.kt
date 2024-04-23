package io.github.glailton.uolhost.core.domain.models

import io.github.glailton.uolhost.R

data class Player(
    val id: Long,
    val name: String,
    val email: String,
    val codiname: Pair<String, Int>,
    val groupType: Int
) {
    companion object {
        fun handleCodiname(codiname: String): Pair<String, Int> {
            return when(codiname) {
                "Hulk" -> Pair(codiname, R.drawable.hulk)
                "Capitão América" -> Pair(codiname, R.drawable.capitao_america)
                "Pantera Negra" -> Pair(codiname, R.drawable.pantera_negra)
                "Homem de Ferro" -> Pair(codiname, R.drawable.homem_de_ferro)
                "Thor" -> Pair(codiname, R.drawable.thor)
                "Feiticeira Escarlate" -> Pair(codiname, R.drawable.feiticeira_escarlate)
                "Visão" -> Pair(codiname, R.drawable.visao)
                "Lanterna Verde" -> Pair(codiname, R.drawable.lanterna_verde)
                "Flash" -> Pair(codiname, R.drawable.flash)
                "Aquaman" -> Pair(codiname, R.drawable.aquaman)
                "Batman" -> Pair(codiname, R.drawable.batman)
                "Superman" -> Pair(codiname, R.drawable.superman)
                "Mulher Maravilha<" -> Pair(codiname, R.drawable.mulher_maravilha)
                else -> Pair(codiname, R.drawable.oh_no)
            }
        }
    }
}