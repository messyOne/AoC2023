import days.*
import java.awt.Point

fun main() {
    listOf(
        Day04(),
    ).forEach {
        it.printHeader()
        println("Part 1: " + it.executePart1())
        println("Part 2: " + it.executePart2())
    }
}