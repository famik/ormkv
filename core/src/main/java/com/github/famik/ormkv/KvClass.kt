package com.github.famik.ormkv

/**
 * Class annotation for OrmKv
 *
 * @param handler [KvHandler]
 * @param className The generated class name, can include package name.
 * @param prefix Custom string as key's prefix.
 * @param superclass If true, use the class as generated class‘ baseclass.
 * @param init If false, values should init by refresh manually (Ignored if superclass is false).
 * @param singleton If true, class keyword will be replaced by object.
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
annotation class KvClass(
    val className: String = "",
    val prefix: String = "",
    val superclass: Boolean = false,
    val init: Boolean = true,
    val singleton: Boolean = false,
    val handler: String = "KvHandler.getDefault()"
)
