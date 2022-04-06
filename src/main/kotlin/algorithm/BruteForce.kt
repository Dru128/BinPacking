package algorithm

import Container
import Item
import other.Configuration


class BruteForce(
    objects: Array<Item>,
    containers: Array<Container>
) : Algorithm(objects, containers)
{

    override fun start(): MutableList<Item>
    {
        appendObject(containers[0])

//        for (curObj in bestSolutionItems)
//            println("${curObj.weight}: ${curObj.cont}")

//        println("*".repeat(80))
//        println("Best solution rate: $bestSolutionRate")
//        println("*".repeat(80))
        clearEmptyContainers()
        return bestSolutionItems
    }

    var level : Int = 0

    // CurContainer - В который укладываем
    fun appendObject(curContainer: Container)
    {
        var isFreeItemExists: Boolean = false
        var isAppended: Boolean = false

        if (level > 1000) {
            printLog("!!!MaxLevel!!!")
            return
        }

        for (obj in objects)
            if (obj.cont == null)
            {
                isFreeItemExists = true
                if (curContainer.freePlace() > 0 && curContainer.freeWeight() >= obj.weight)
                {
                    isAppended = true
                    obj.cont = curContainer
                    curContainer.objects.add(obj)
                    printLog(obj.weight.toString())

                    level++
                    appendObject(curContainer)
                    level--

                    obj.cont = null
                    curContainer.objects.remove(obj)
                }
            }

        if (isFreeItemExists && !isAppended)
        {
            // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            // Для поддержки разных типов контейнеров добавить цикл по типам
            printLog("new container")

            level++
            appendObject( Container(Configuration.PLACE_CONT, Configuration.WEIGHT_CONT) )
            level--
        }

        // Неразмещённых нет - рещение найдено
        if (!isFreeItemExists)
        {
            //printLog("*** Solution ***")
            //this.showContainers()

            val rate : Float = calcSolutionRate()
            if (rate < bestSolutionRate)
                saveSolution(rate)
        }
    }

    var isShowLog : Boolean = false
    fun printLog(str : String)
    {
        if (isShowLog)
            println(" ".repeat(level*3) + str)
    }
}