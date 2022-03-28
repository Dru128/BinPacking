import kotlinx.serialization.Serializable

@Serializable
data class Item(var weight: Float) {
    var cont: Container? = null
}