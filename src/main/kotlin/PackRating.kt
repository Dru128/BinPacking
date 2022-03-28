class PackRating(val binsUsed: Int, val score: Int, val overflow: Int) : Comparable<PackRating>
{
    override operator fun compareTo(other: PackRating) =
        if (overflow != other.overflow) other.overflow - overflow
        else if (binsUsed != other.binsUsed) other.binsUsed - binsUsed
        else score - other.score
}

/**
 * overflow — сумма квадратов перегрузок контейнеров
 * binsUsed — количество занятых контейнеров
 * score — сумма квадратов свободного места в контейнерах
 * */