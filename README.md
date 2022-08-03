[![](https://jitpack.io/v/famik/ormkv.svg)](https://jitpack.io/#famik/ormkv)
![](https://img.shields.io/badge/build-passing-green.svg)
![](https://img.shields.io/badge/license-MIT-orange.svg)

Ormkv is a `Object Relational Mapping` keyValue-saving helper. It automatically generates
object-oriented tool class that access key-value pairs based on the bean class.

## Usage

```gradle
    allprojects {
        repositories {
          ...
          maven { url 'https://jitpack.io'}
        }
    }
    
    dependencies {
	   implementation "com.github.famik.ormkv:core:${latestVersion}""
	   
	   // if you use SharedPreferences
	   implementation "com.github.famik.ormkv:sp:${latestVersion}"
	   
	   // if you use MMKV
	   implementation "com.github.famik.ormkv:mmkv:${latestVersion}"
	   
	   // if you use kapt
	   kapt "com.github.famik.ormkv:compiler:${latestVersion}"
	   
	   // if you use ksp
	   ksp "com.github.famik.ormkv:compiler:${latestVersion}"
	}
```

### Create key-value store class

```kotlin
package com.lwjlol.ormkv.demo

import android.content.Context
import com.lwjlol.ormkv.mmkv.MmkvHandler
import com.lwjlol.ormkv.sp.SharedPreferencesHandler

object KvStore {
    //  if you user SharedPreferences, you can use SharedPreferencesHandler
    val sharedPreferencesHandler =
        SharedPreferencesHandler(App.context.getSharedPreferences("SP", Context.MODE_PRIVATE))

    //  if you user mmkv, you can use MmkvHandler
    val mmkvHandler = MmkvHandler(com.tencent.mmkv.MMKV.defaultMMKV())
}
```

you can write your custom handler implement `OrmKvHandler` class

```kotlin
class MyHandler : OrmKvHandler {
    // TODO: implement put an get method
}

```

### Create `KvClass` class

use `@KvClass` annotation this class.

```kotlin
@KvClass(
    name = "UserRegistry", // default name is className with suffix 'Registry', such as UserModelRegistry
    handler = "KvHandler.getDefault()",  // default handler is use ServiceLoader to found
    superclass = true // as superclass, default value will use constructor argument
)
open class UserModel(
    // KvField defaultValue is ignored for superclass specified
    @KvField(defaultValue = "\"ignored\"")
    open var name: String = "realDefaultValue",
    @Transient // ignore this property
    @KvField(defaultValue = "32")
    open var age: Int = Build.VERSION.SDK_INT,
    @KvField(defaultValue = "12312312", enableReset = false)
    open var id: Long = 0,
    @KvField(defaultValue = "false")
    open var isMan: Boolean = false
)
```

### Build and generate Registry class

build project and we will get a Registry class.

```kotlin
/**
 * this class is generated by https://github.com/famik/ormkv for [com.lwjlol.ormkv.demo.UserModel],
 * Please don't modify it!
 */
public class UserRegistry : UserModel() {
    private val kvHandler: KvHandler = KvHandler.getDefault()

    public override var name: String
        get() = super.name
        set(`value`) {
            super.name = value
            kvHandler.put("name", value)
        }

    public override var id: Long
        get() = super.id
        set(`value`) {
            super.id = value
            kvHandler.put("id", value)
        }

    public override var isMan: Boolean
        get() = super.isMan
        set(`value`) {
            super.isMan = value
            kvHandler.put("isMan", value)
        }

    init {
        refresh()
    }

    public fun refresh(): Unit {
        super.name = kvHandler.get("name", super.name)!!
        super.id = kvHandler.get("id", super.id)
        super.isMan = kvHandler.get("isMan", super.isMan)
    }

    public fun reset(): Unit {
        update(UserModel())
    }

    public override fun toString(): String = "name = $name"+
            "\nid = $id"+
            "\nisMan = $isMan"

    public fun update(model: UserModel): Unit {
        name = model.name
        id = model.id
        isMan = model.isMan
    }

    public fun toModel(): UserModel = UserModel(
        name = name,
        age = super.age,
        id = id,
        isMan = isMan)
}
```

## Set/get value

use the Registry instance to set/get value.

```kotlin
val user = UserRegistry()
user.name = "Mike"

Log.d(tag, user.toString())
Log.d(tag, user.name)

// refresh all values
user.refresh()
```
you can create and manage Registry singleton by you self or use dependency injection.

## KSP

KSP Generated Sources in IDE Note, that as of the current KSP version generated java sources are
detected by the IDE but NOT generated kotlin sources. This means that generated epoxy kotlin
extensions will not automatically be resolved in the IDE. You must manually configure your source
sets to include ksp generated folders.

You can add this to your root build.gradle file to work around this

```gradle
subprojects { project ->
    afterEvaluate {
        if (project.hasProperty("android")) {
            android {
                if (it instanceof com.android.build.gradle.LibraryExtension) {
                    libraryVariants.all { variant ->
                        def outputFolder = new File("build/generated/ksp/${variant.name}/kotlin")
                        variant.addJavaSourceFoldersToModel(outputFolder)
                        android.sourceSets.getAt(variant.name).java {
                            srcDir(outputFolder)
                        }
                    }
                } else if (it instanceof com.android.build.gradle.AppExtension) {
                    applicationVariants.all { variant ->
                        def outputFolder = new File("build/generated/ksp/${variant.name}/kotlin")
                        variant.addJavaSourceFoldersToModel(outputFolder)
                        android.sourceSets.getAt(variant.name).java {
                            srcDir(outputFolder)
                        }
                    }
                }
            }
        }
    }
}
```

Of if you use kotlin build files you can apply it like this to a project.

```kotlin
/**
 * Return the Android variants for this module, or error if this is not a module with a known Android plugin.
 */
fun Project.requireAndroidVariants(): DomainObjectSet<out com.android.build.gradle.api.BaseVariant> {
 return androidVariants() ?: error("no known android extension found for ${project.name}")
}

/**
 * Return the Android variants for this module, or null if this is not a module with a known Android plugin.
 */
fun Project.androidVariants(): DomainObjectSet<out com.android.build.gradle.api.BaseVariant>? {
 return when (val androidExtension = this.extensions.findByName("android")) {
  is com.android.build.gradle.LibraryExtension -> {
   androidExtension.libraryVariants
  }
  is com.android.build.gradle.AppExtension -> {
   androidExtension.applicationVariants
  }
  else -> null
 }
}

fun Project.registerKspKotlinOutputAsSourceSet() {
 afterEvaluate {
  val android by lazy {
   extensions.findByType(com.android.build.gradle.BaseExtension::class.java)
    ?: throw NullPointerException()
  }

  requireAndroidVariants().forEach { variant ->
   val variantName = variant.name
   val outputFolder = File("build/generated/ksp/$variantName/kotlin")
   variant.addJavaSourceFoldersToModel(outputFolder)
   android.sourceSets.getAt(variantName).java {
    srcDir(outputFolder)
   }
  }
 }
}
```
