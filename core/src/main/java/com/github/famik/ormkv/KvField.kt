package com.github.famik.ormkv

/**
 * Field annotation for OrmKv
 *
 * @param name Key name. Set the [KvClass] prefix if you want to set the prefix uniformly.
 *
 * @param defaultValue Default value. This option is ignored if [KvClass] superclass is true.
 *
 * @param lazyCache Whether to cache the field. This option is ignored if [KvClass] superclass is true.
 *
 * @param enableReset Whether the reset method is supported to reset the field to the default value.
 * This option is ignored if [KvClass] superclass is true.
 */
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.SOURCE)
annotation class KvField(
    val name: String = "",
    val defaultValue: String = "",
    val lazyCache: Boolean = true,
    val enableReset: Boolean = true
)
