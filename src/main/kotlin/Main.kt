import days.*

fun main() {
    listOf(
        Day05(),
    ).forEach {
        it.printHeader()
        println("Part 1: " + it.executePart1())
        println("Part 2: " + it.executePart2())
    }
}