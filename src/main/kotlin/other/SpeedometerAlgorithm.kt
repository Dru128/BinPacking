package other

class SpeedometerAlgorithm
{
    private var startTime = 0L

    fun start()
    {
        startTime = getCurTime()
    }

    fun finish() = getCurTime() - startTime

    private fun getCurTime() = System.currentTimeMillis()
}