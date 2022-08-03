package com.github.famik.ormkv

/**
 * Class annotation for OrmKv
 *
 * @param handler [KvHandler]
 * @param className The generated class name, can include package name.
 * @param prefix Custom string as key's prefix
 * @param superclass If true, use the class as generated class‘ superclass
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
annotation class KvClass(
    val className: String = "",
    val prefix: String = "",
    val superclass: Boolean = false,
    val handler: String = "KvHandler.getDefault()"
)
