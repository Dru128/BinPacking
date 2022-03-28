import kotlinx.serialization.Serializable

@Serializable
data class Container(
    var place: Int,
    var maxWeight: Float
)
{
    var id = 0
    var objects: MutableList<Item> = mutableListOf()

    fun freeWeight() = maxWeight - (objects.sumOf{ it.weight.toDouble() }).toFloat()
    fun freePlace() = place - objects.size

    init {
        id++
    }


    override fun equals(other: Any?): Boolean {
        return super.equals(other) && (other as Container).id == this.id
    }
}
