package com.github.famik.ormkv

import com.tencent.mmkv.MMKV

open class MmkvHandler(private val mmkv: MMKV) : KvHandler {
  override fun set(key: String, value: Boolean) {
    mmkv.encode(key, value)
  }

  override fun set(key: String, value: Int) {
    mmkv.encode(key, value)
  }

  override fun set(key: String, value: Long) {
    mmkv.encode(key, value)
  }

  override fun set(key: String, value: Float) {
    mmkv.encode(key, value)
  }

  override fun set(key: String, value: String?) {
    mmkv.encode(key, value)
  }

  override fun set(key: String, value: ByteArray?) {
    mmkv.encode(key, value)
  }

  override fun set(key: String, value: Set<String>?) {
    mmkv.encode(key, value)
  }

  override fun get(key: String, default: Boolean): Boolean {
    return mmkv.decodeBool(key, default)
  }

  override fun get(key: String, default: Int): Int {
    return mmkv.decodeInt(key, default)
  }

  override fun get(key: String, default: Long): Long {
    return mmkv.decodeLong(key, default)
  }

  override fun get(key: String, default: Float): Float {
    return mmkv.decodeFloat(key, default)
  }

  override fun get(key: String, default: String?): String? {
    return mmkv.decodeString(key, default)
  }

  override fun get(key: String, default: ByteArray?): ByteArray? {
    return mmkv.decodeBytes(key, default)
  }

  override fun get(key: String, default: Set<String>?): Set<String>? {
    return mmkv.decodeStringSet(key, default)
  }

}
