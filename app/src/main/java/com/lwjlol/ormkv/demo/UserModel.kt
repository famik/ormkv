package com.lwjlol.ormkv.demo

import android.os.Build
import com.github.famik.ormkv.KvField
import com.github.famik.ormkv.KvClass

/**
 * @author luwenjie on 2019-08-11 20:16:58
 */
@KvClass(
    className = "User",
    handler = "com.lwjlol.ormkv.demo.KvStore.sp",
    superclass = true
)
open class UserModel(
    @KvField(defaultValue = "\"test\"")
    open var name: String = "",
    @Transient // ignore this property
    @KvField(defaultValue = "12")
    open var age: Int = Build.VERSION.SDK_INT,
    @KvField(defaultValue = "12312312", enableReset = false)
    open var id: Long = 0,
    @KvField(defaultValue = "false")
    open var isMan: Boolean = false,
    @KvField(defaultValue = "1231", enableReset = false)
    open var temperature: Float = 0.0F

)

@KvClass
class TestModel(val a: String, val b: String)