package com.example.myapplication.data_structs

data class Order(val entrees: ArrayList<Entree>, val sides: ArrayList<Side>, val drinks: ArrayList<Drink>)

    /*
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Order

        if (!entrees.contentEquals(other.entrees)) return false

        return true
    }

    override fun hashCode(): Int {
        return entrees.contentHashCode()
    }

     */
