import other.SpeedometerAlgorithm
import other.Configuration
import other.DataManager
import other.ItemsToConts
import java.io.File


fun main()
{
    val speedometerAlgorithm = SpeedometerAlgorithm()
    val data = DataManager()
    val objFile = File("src/main/resources/objects_long")

/*
    data.generateObjects(5000, 10..65, objFile)
*/
    val objects: Array<Item> = data.getObjects(objFile) // предметы
    val containers: Array<Container> =
        arrayOf( Container(Configuration.PLACE_CONT, Configuration.WEIGHT_CONT) ) // контейнеры

    val algorithm = algorithm.FirstFit(objects, containers, false)
//    val algorithm = algorithm.BruteForce(objects, containers)
//    val algorithm = algorithm.NextFit(objects, containers, null)
//    val algorithm = algorithm.AlmostWorstFit(objects, containers, null)
//    val algorithm = algorithm.WorstFit(objects, containers, null)
//    val algorithm = algorithm.BestFit(objects, containers, true)

    speedometerAlgorithm.start()
    var solution = algorithm.start()

//    algorithm.containers = ItemsToConts().convertItemsToConts(solution)!!
    algorithm.showContainers()

    println("work time = " + speedometerAlgorithm.finish())
}
