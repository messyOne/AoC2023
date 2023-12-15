package days

import java.io.File

class Day05 : Day {

    override fun executePart1(): Long {
        val lines = File("src/main/resources/day05-1.txt").useLines { it.toList() }
        val maps = mutableMapOf<Int, MutableList<Map>>()

        val seeds = lines.first().split(":").last().trim().split(" ").map { Seed(it.toLong()) }
        var start = ""
        var target = ""
        var counter = -1
        lines.drop(2).forEach { line ->
            if (line.isNotEmpty()) {
                if (line.contains("map")) {
                    start = line.split(" ").first().split("-").first()
                    target = line.split(" ").first().split("-").last()
                    maps[++counter] = mutableListOf()
                } else {
                    val destinationStart = line.split(" ")[0]
                    val sourceStart = line.split(" ")[1]
                    val rangeLength = line.split(" ")[2]

                    maps[counter]!!.add(
                        Map(
                            start,
                            target,
                            destinationStart.toLong(),
                            sourceStart.toLong(),
                            rangeLength.toLong()
                        )
                    )
                }
            }
        }

        seeds.forEach { seed ->
            maps.forEach { (_, maps) ->
                var isStopped = false
                maps.forEach { map ->
                    if (!isStopped) {
                        val newSeed = map.transform(seed)

                        if (newSeed.value != seed.value) {
                            seed.value = newSeed.value
                            isStopped = true
                        }
                    }

                }
            }
        }

        return seeds.minOf { it.value }
    }

    override fun executePart2(): Long {
        return 0
    }

}

data class Seed(var value: Long)

data class Map(
    val start: String,
    val target: String,
    val destinationStart: Long,
    val sourceStart: Long,
    val rangeLength: Long
) {
    fun transform(seed: Seed): Seed {
        return if (seed.value >= sourceStart && seed.value < sourceStart + rangeLength) {
            Seed(seed.value + destinationStart - sourceStart)
        } else
            seed
    }
}