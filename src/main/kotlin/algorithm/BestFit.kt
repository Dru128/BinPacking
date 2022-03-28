package algorithm

import Container
import Item
import other.Configuration


class BestFit(
    objects: Array<Item>,
    containers: Array<Container>,
    var isSort: Boolean? = null
) : Algorithm(objects, containers)
{

    override fun start(): MutableList<Item>
    {
        if (isSort != null) sortObj(isSort!!)

        for (item in objects)
        {
            val index = appendItem(item, containers)

            if (index == -1)
            {
                containers += Container(Configuration.PLACE_CONT, Configuration.WEIGHT_CONT)
                item.cont = containers[containers.lastIndex]
                containers[containers.lastIndex].objects.add(item)
            }
            else
            {
                item.cont = containers[index]
                containers[index].objects.add(item)
            }
        }

//        showContainers()
        val rate: Float = calcSolutionRate()
        if (rate < bestSolutionRate)
            saveSolution(rate)
        return bestSolutionItems
    }
    fun appendItem(obj: Item, containers: Array<Container>): Int
    {
        var bestCont: Int = -1

        for (cont in containers.indices)
        {
            if (obj.weight <= containers[cont].freeWeight() && containers[cont].freePlace() > 0)
            {
                if (bestCont == -1) bestCont = cont

                if (containers[cont].freeWeight() < containers[bestCont].freeWeight())
                {
                    bestCont = cont
                }

            }
        }
        return bestCont
    }
}