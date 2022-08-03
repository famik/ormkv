package com.lwjlol.ormkv.demo

import android.content.Context
import com.github.famik.ormkv.MmkvHandler
import com.github.famik.ormkv.SharedPreferencesHandler
import com.tencent.mmkv.MMKV

object KvStore {
    val sp =
        SharedPreferencesHandler(App.context.getSharedPreferences("ccsp", Context.MODE_PRIVATE))
    val mmkv = MmkvHandler(MMKV.defaultMMKV()!!)
}





