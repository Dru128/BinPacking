package algorithm

import Container
import Item
import other.Configuration
import Result


class NextFit(
    objects: Array<Item>,
    containers: Array<Container>,
    var isSort: Boolean? = null
) : Algorithm(objects, containers)
{
    /**
     * если isSort == true сортируется от большего к меньшиму,
     * если false, то по возрастанию,
     * если null, то сортировка не выполняется
     **/
    override fun start(): MutableList<Item>
    {
        if (isSort != null) sortObj(isSort!!)

        for (obj in objects)
        {

            val action = appendObject(obj)
            if (action.isCreateNewCont)
                containers += Container(Configuration.PLACE_CONT, Configuration.WEIGHT_CONT)

            containers[action.num].objects.add(obj)
            obj.cont = containers[action.num]
        }

        clearEmptyContainers()
        val rate: Float = calcSolutionRate()
        if (rate < bestSolutionRate)
            saveSolution(rate)
        return bestSolutionItems
    }

    fun appendObject(obj: Item): Result
    {
        val container = containers.last()
        return if (obj.weight <= container.freeWeight() && container.freePlace() > 0)
            Result(false, containers.lastIndex)
        else
            Result(true, containers.lastIndex + 1)
    }
}