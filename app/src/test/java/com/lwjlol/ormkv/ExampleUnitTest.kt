package com.lwjlol.ormkv

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
fun testNum(num: Int) {
    println(num.toString())
}

fun testNum(num: Any) {
    println(num.toString())
}

fun main() {
    val n: Number = 1
    testNum(n)
    val f = 0.1F
    testNum(f)
}

open class BaseTestModel(
    open var name: String = "gfm"
)
class TestModel : BaseTestModel() {
    override var name: String
        get() = super.name
        set(value) {
            super.name = value
            println("super.name: ${super.name}")
        }

    fun test() {
        super.name = "test"
    }

    init {
        println("TestModel name: $name")
    }
}


class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun test() {
        val model = TestModel()
        model.test()
        assertEquals(model.name, "test")
    }
}
