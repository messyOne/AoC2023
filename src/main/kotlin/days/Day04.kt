package days

import java.io.File
import kotlin.math.pow

class Day04 : Day {
    override fun executePart1(): Long {
        val cards = File("src/main/resources/day04-1.txt").useLines { line ->
            line.toList().map { line ->
                val split1 = line.split(":")
                val split2 = split1.last().split("|")
                val id = split1.first().split(" ").last().toInt()
                val winningNumbers = split2.first().split(" ").filter { it.trim().isNotEmpty() }.map { it.toInt() }
                val numbers = split2.last().split(" ").filter { it.trim().isNotEmpty() }.map { it.toInt() }

                Card(id, winningNumbers, numbers)
            }
        }

        return cards.sumOf { it.getScore() }
    }

    override fun executePart2(): Long {
        return 0
    }
}

data class Card(val id: Int, val winningNumbers: List<Int>, val numbers: List<Int>) {
    fun getScore(): Long {
        val wins = numbers.intersect(winningNumbers.toSet()).count()

        if (wins == 0) {
            return 0
        }

        var start = 1L
        for (i in 1 until wins) {
            start *= 2
        }
        return start
    }
}