package other

class SpeedometerAlgorithm
{
    private var startTime = 0L

    fun start()
    {
        startTime = getCurTime()
    }

    fun finish() = (getCurTime() - startTime).toDouble() / 1000 // second

    private fun getCurTime() = System.currentTimeMillis()
}