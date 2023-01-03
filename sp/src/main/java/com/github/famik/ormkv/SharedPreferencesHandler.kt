package com.github.famik.ormkv

import android.content.SharedPreferences

open class SharedPreferencesHandler(val sp: SharedPreferences) : KvHandler {
  override fun set(key: String, value: Boolean) {
    sp.edit().putBoolean(key, value).apply()
  }

  override fun set(key: String, value: Int) {
    sp.edit().putInt(key, value).apply()
  }

  override fun set(key: String, value: Long) {
    sp.edit().putLong(key, value).apply()
  }

  override fun set(key: String, value: Float) {
    sp.edit().putFloat(key, value).apply()
  }

  override fun set(key: String, value: String?) {
    sp.edit().putString(key, value).apply()
  }

  override fun set(key: String, value: ByteArray?) {
    throw RuntimeException("unsupported ${ByteArray::class.qualifiedName}")
  }

  override fun set(key: String, value: Set<String>?) {
    sp.edit().putStringSet(key, value).apply()
  }

  override fun get(key: String, default: Boolean): Boolean {
    return sp.getBoolean(key, default)
  }

  override fun get(key: String, default: Int): Int {
    return sp.getInt(key, default)
  }

  override fun get(key: String, default: Long): Long {
    return sp.getLong(key, default)
  }

  override fun get(key: String, default: Float): Float {
    return sp.getFloat(key, default)
  }

  override fun get(key: String, default: String?): String? {
    return sp.getString(key, default)
  }

  override fun get(key: String, default: ByteArray?): ByteArray? {
    throw RuntimeException("unsupported ${ByteArray::class.qualifiedName}")
  }
  override fun get(key: String, default: Set<String>?): Set<String>? {
    return sp.getStringSet(key, default)
  }

  override fun remove(key: String?) {
    sp.edit().remove(key).apply()
  }

  override fun clear() {
    sp.edit().clear().apply()
  }
}
