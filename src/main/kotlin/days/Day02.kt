package days

import java.io.File

class Day02 : Day {
    override fun executePart1(): Long {
        val limits = mapOf(
            "red" to 12,
            "green" to 13,
            "blue" to 14
        )

        var total = 0L
        File("src/main/resources/day02-1.txt").useLines { it.toList().map { line ->
            val id = line.split(":").first().split(" ").last().toInt()
            var isValid = true

            line.split(":").last().split(";").forEach { set ->
                set.split(", ").forEach { draw ->
                    val color = draw.trim().split(" ").last()
                    val amount = draw.trim().split(" ").first().toInt()

                    if (amount > limits.getOrDefault(color, 0)) {
                        isValid = false
                    }
                }
            }

            if (isValid) {
                total += id
            }
        } }

        return total
    }

    override fun executePart2(): Long {
        var total = 0L
        File("src/main/resources/day02-2.txt").useLines { it.toList().map { line ->
            val limits = mutableMapOf(
                "red" to 0,
                "green" to 0,
                "blue" to 0
            )

            line.split(":").last().split(";").forEach { set ->
                set.split(", ").forEach { draw ->
                    val color = draw.trim().split(" ").last()
                    val amount = draw.trim().split(" ").first().toInt()

                    if (amount > limits.getOrDefault(color, 0)) {
                        limits[color] = amount
                    }
                }
            }

            total += limits.values.fold(1) { acc, i -> acc * i }
        } }

        return total
    }
}