package other

import Item
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

class DataManager
{

    fun showObjects(obj: MutableList<Item>)
    {
        obj.forEach { print(" ${it.weight} ") }
    }

    fun generateObjects(n: Int, rage: IntRange, file: File)
    {
        file.writeText("")
        val objArray = Array<Item>(n) { i-> if (i < n/2) Item(rage.random().toFloat())else Item((20..50).random().toFloat()) }
        file.appendText(
            Json.encodeToString(objArray)
        )
    }

    fun getObjects(file: File): Array<Item> =
        Json.decodeFromString<Array<Item>>(file.readText())

}