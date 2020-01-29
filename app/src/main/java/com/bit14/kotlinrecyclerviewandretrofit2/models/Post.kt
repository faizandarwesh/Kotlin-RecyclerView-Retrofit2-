package com.bit14.kotlinrecyclerviewandretrofit2.models

data class Post(val id:String,val title:String,val body:String){

    override fun equals(other: Any?): Boolean {
        if (javaClass != other?.javaClass)
            return false
        other as Post

        if (id != other.id)
            return false
        if (title != other.title)
            return false
        if (body != other.body)
            return false

        return true
    }
}

