import other.SpeedometerAlgorithm
import other.Configuration
import other.DataManager
import other.ItemsToConts
import java.io.File


fun main()
{
    val speedometerAlgorithm = SpeedometerAlgorithm()
    val data = DataManager()
    val objFile = File("src/main/resources/objects")
//    val objFile = File("src/main/resources/objects_short")

//    data.generateObjects(20, 10..150, objFile)
    val objects: Array<Item> = data.getObjects(objFile) // предметы
    val containers: Array<Container> =
        arrayOf( Container(Configuration.PLACE_CONT, Configuration.WEIGHT_CONT) ) // контейнеры

//    val algorithm = algorithm.Enumeration(objects, containers)
//    val algorithm = algorithm.FirstFit(objects, containers, true)
    val algorithm = algorithm.BestFit(objects, containers, true)
//    val algorithm = algorithm.BruteForce(objects, containers)
//    val algorithm = algorithm.NextFit(objects, containers)
//    val algorithm = algorithm.WorstFit(objects, containers)

    speedometerAlgorithm.start()
    var solution = algorithm.start()
    algorithm.showContainers()
    println(speedometerAlgorithm.finish())

//    algorithm.containers = ItemsToConts().convertItemsToConts(solution)!!
//    algorithm.showContainers()
}
