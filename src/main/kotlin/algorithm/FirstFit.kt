package algorithm

import Container
import Item
import Result
import other.Configuration


// последовательное заполнение от большего к меньшему
class FirstFit(
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

            val action = appendObject(obj, containers)
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

    fun appendObject(obj: Item, containers: Array<Container>): Result
    {
        for (cont in containers.indices)
        {
            if (obj.weight <= containers[cont].freeWeight() && containers[cont].freePlace() > 0)
            {
                return if (cont == containers.lastIndex)
                    Result(true, cont)
                else {
                    Result(false, cont)
                }
            }
        }
        return Result(true, -1)
    }
}