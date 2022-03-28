package algorithm.genetic


class Individ(
    var start: Int,
    var end: Int,
    var x: Int = (start..end).random(),
    var y: Int = (start..end).random(),
    var score: Int = 0, // значение функции, которую реализует индивид
    var mutationSteps: Int, // количество шагов мутации
)
{
}