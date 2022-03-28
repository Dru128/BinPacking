package algorithm

import Container
import Item
import other.Configuration

class AlmostWorstFit(
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
                containers[containers.lastIndex].objects.add(item)
                item.cont = containers[containers.lastIndex]
            }
            else
            {
                containers[index].objects.add(item)
                item.cont = containers[index]
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
        var firstCont: Int = -1
        var secondCont: Int = -1

        for (cont in containers.indices)
        {
            if (obj.weight <= containers[cont].freeWeight() && containers[cont].freePlace() > 0)
            {
                if (firstCont == -1) firstCont = cont

                if (containers[cont].freeWeight() > containers[firstCont].freeWeight())
                {
                    secondCont = firstCont
                    firstCont = cont
                }
            }
        }
        return if (secondCont != -1) secondCont
        else firstCont
    }
}