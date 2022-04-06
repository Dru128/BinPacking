package algorithm

import Container
import Item
import java.lang.StrictMath.pow
import kotlin.math.sqrt


abstract class Algorithm(
    var objects: Array<Item>,
    var containers: Array<Container>,
)
{
    var bestSolutionItems: MutableList<Item> = mutableListOf()
    var bestSolutionRate: Float = Float.MAX_VALUE

    abstract fun start(): MutableList<Item>

    fun calcSolutionRate(): Float
    {
        return containers.size.toFloat()
    }

    fun showContainers()
    {
        println()
        for (cont in containers.indices)
        {
            print("${cont+1}.  free weight: ${containers[cont].freeWeight()}  free size: ${containers[cont].freePlace()}    ")
            containers[cont].objects.forEach { item ->
                print(item.weight.toString() + " ")
            }
            println()
        }
        println("containers = ${containers.size}")
    }

    fun saveSolution(rate : Float)
    {
        bestSolutionRate = rate

        bestSolutionItems = mutableListOf<Item>()
        var newItem : Item? = null
        for (curItem in objects)
        {
            newItem = Item(curItem.weight)
            newItem.cont = curItem.cont
            bestSolutionItems.add(newItem)
        }


    }



    /**
     * если isSort == true сортируется от большего к меньшиму,
     * если false, то по возрастанию
     **/
    fun sortObj(isBigToSmall: Boolean)
    {
        objects.sortBy { it.weight }
        if (isBigToSmall) objects.reverse()
    }

    fun clearEmptyContainers()
    {
        var solution = arrayOf<Container>()
        for (i in containers)
            if (i.objects.isNotEmpty()) solution += i
        containers = solution
    }
}