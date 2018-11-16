package com.yibao.gankkotlin.model

import android.os.Parcel
import android.os.Parcelable
import java.util.*

/**
 * Author：Sid
 * Des：${TODO}
 * Time:2017/10/17 00:20
 */
/*_id: "5a4af266421aa90fe50c02b6",
createdAt: "2018-01-02T10:45:58.490Z",
desc: "一个简洁、实用、方便的Android异步处理库，已应用到百万日活的线上项目中",
publishedAt: "2018-01-10T07:57:19.486Z",
source: "web",
type: "Android",
url: "https://github.com/SilenceDut/TaskScheduler",
used: true,
who: null*/
data class Meizi(val _id: String,
                 val createdAt: String,
                 val desc: String,
                 val images: Array<String>?,
                 val publishedAt: String,
                 val source: String,
                 val type: String,
                 val url: String,
                 val used: Boolean,
                 val who: String?

) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.createStringArray(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readByte() != 0.toByte(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(_id)
        parcel.writeString(createdAt)
        parcel.writeString(desc)
        parcel.writeStringArray(images)
        parcel.writeString(publishedAt)
        parcel.writeString(source)
        parcel.writeString(type)
        parcel.writeString(url)
        parcel.writeByte(if (used) 1 else 0)
        parcel.writeString(who)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Meizi

        if (_id != other._id) return false
        if (createdAt != other.createdAt) return false
        if (desc != other.desc) return false
        if (!Arrays.equals(images, other.images)) return false
        if (publishedAt != other.publishedAt) return false
        if (source != other.source) return false
        if (type != other.type) return false
        if (url != other.url) return false
        if (used != other.used) return false
        if (who != other.who) return false

        return true
    }

    override fun hashCode(): Int {
        var result = _id.hashCode()
        result = 31 * result + createdAt.hashCode()
        result = 31 * result + desc.hashCode()
        result = 31 * result + (images?.let { Arrays.hashCode(it) } ?: 0)
        result = 31 * result + publishedAt.hashCode()
        result = 31 * result + source.hashCode()
        result = 31 * result + type.hashCode()
        result = 31 * result + url.hashCode()
        result = 31 * result + used.hashCode()
        if (who != null) {
            result = 31 * result + who.hashCode()
        }
        return result
    }

    companion object CREATOR : Parcelable.Creator<Meizi> {
        override fun createFromParcel(parcel: Parcel): Meizi = Meizi(parcel)

        override fun newArray(size: Int): Array<Meizi?> = arrayOfNulls(size)
    }


}


