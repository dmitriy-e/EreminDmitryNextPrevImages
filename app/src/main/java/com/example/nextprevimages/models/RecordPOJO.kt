package com.example.nextprevimages.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RecordPOJO {
    @SerializedName("description")
    @Expose
    private var description: String? = null

    @SerializedName("gifURL")
    @Expose
    private var link: String? = null

    fun getDescription(): String? {
        return description
    }

    fun setDescription(description: String?) {
        this.description = description
    }

    fun getLink(): String? {
        return link
    }

    fun setLink(link: String?) {
        this.link = link
    }
}