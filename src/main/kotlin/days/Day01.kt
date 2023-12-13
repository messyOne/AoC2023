package days

import java.io.File

class Day01 : Day {
    override fun executePart1(): Long {

        val data = File("src/main/resources/day01-1.txt").useLines { it.toList() }

        return data.sumOf { line: String ->
            val numbers = line.filter(Char::isDigit).map(Char::digitToInt)

            numbers.first() * 10 + numbers.last()
        }.toLong()
    }

    override fun executePart2(): Long {

        val data = File("src/main/resources/day01-2.txt").useLines { it.toList() }

        return data.sumOf { line: String ->
            var newLine = ""
            for (i in line.indices step 1) {
                newLine += line.substring(i, (i + 5).coerceAtMost(line.length))
                    .replace("one", "1")
                    .replace("two", "2")
                    .replace("three", "3")
                    .replace("four", "4")
                    .replace("five", "5")
                    .replace("six", "6")
                    .replace("seven", "7")
                    .replace("eight", "8")
                    .replace("nine", "9")
                    .replace("zero", "0")
            }
            val numbers = newLine.filter { char -> char.isDigit() }.map(Char::digitToInt)

            numbers.first() * 10 + numbers.last()
        }.toLong()
    }
}