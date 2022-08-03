package com.lwjlol.ormkv.demo

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import com.github.famik.ormkv.KvField
import com.github.famik.ormkv.KvClass

@KvClass(
    className = "ConfigMmkv",
    handler = "KvStore.mmkv",
)
data class ConfigModel(
    @Transient
    val value3: ByteArray,
    @KvField(defaultValue = "22")
    val value4: Long,
    @KvField(defaultValue = "false")
    val v4: Boolean,
    val v5: Long,
    val v6: Float,
    val v7: ByteArray,
    @KvField(defaultValue = "Constants.sdkVersion")
    val version: Int
)

class A(val a: String) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.readString() ?: "") {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(a)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Creator<A> {
        override fun createFromParcel(parcel: Parcel): A {
            return A(parcel)
        }

        override fun newArray(size: Int): Array<A?> {
            return arrayOfNulls(size)
        }
    }
}

