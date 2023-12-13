package days

import java.awt.Point
import java.io.File

class Day03 : Day {
    override fun executePart1(): Long {
        val numbers = mutableListOf<Number>()
        val symbols = mutableListOf<Symbol>()

        File("src/main/resources/day03-1.txt").useLines {
            val list = it.toList()
            val maxY = list.size - 1

            list.forEachIndexed { y, row ->
                var number = ""
                var startPoint = Point(0, 0)
                var endPoint = Point(0, 0)
                val maxX = row.length - 1
                row.forEachIndexed { x, char ->
                    if (char.isDigit()) {
                        if (number.isEmpty()) {
                            startPoint = Point(x, y)
                        }

                        endPoint = Point(x, y)
                        number += char
                    } else {
                        if (char != '.') {
                            symbols.add(Symbol(char, Point(x, y)))
                        }

                        if (number.isNotEmpty()) {
                            numbers.add(Number(number.toLong(), startPoint, endPoint, maxY, maxX))
                        }

                        number = ""
                    }
                }

                if (number.isNotEmpty()) {
                    numbers.add(Number(number.toLong(), startPoint, endPoint, maxY, maxX))
                }
            }
        }

        val filteredNumbers = numbers.filter { n ->
            n.getNeighbours().any { neighbour ->
                symbols.any { symbol ->
                    symbol.point == neighbour
                }
            }
        }

        return filteredNumbers.sumOf { it.number  }
    }

    override fun executePart2(): Long {
        val numbers = mutableListOf<Number>()
        val gears = mutableListOf<Symbol>()

        File("src/main/resources/day03-2.txt").useLines {
            val list = it.toList()
            val maxY = list.size - 1

            list.forEachIndexed { y, row ->
                var number = ""
                var startPoint = Point(0, 0)
                var endPoint = Point(0, 0)
                val maxX = row.length - 1
                row.forEachIndexed { x, char ->
                    if (char.isDigit()) {
                        if (number.isEmpty()) {
                            startPoint = Point(x, y)
                        }

                        endPoint = Point(x, y)
                        number += char
                    } else {
                        if (char == '*') {
                            gears.add(Symbol(char, Point(x, y)))
                        }

                        if (number.isNotEmpty()) {
                            numbers.add(Number(number.toLong(), startPoint, endPoint, maxY, maxX))
                        }

                        number = ""
                    }
                }

                if (number.isNotEmpty()) {
                    numbers.add(Number(number.toLong(), startPoint, endPoint, maxY, maxX))
                }
            }
        }


        val symbols: MutableMap<Symbol, Int> = mutableMapOf()
        val values: MutableMap<Symbol, Long> = mutableMapOf()
        numbers.forEach {number ->
            number.getNeighbours().forEach { neighbour ->
                gears.find { gear ->
                    gear.point == neighbour
                }?.let { gear ->
                    symbols[gear] = symbols.getOrDefault(gear, 0) + 1
                    values[gear] = values.getOrDefault(gear, 1) * number.number
                }
            }
        }

        return symbols.filter { it.value >= 2 }.map { values[it.key]!! }.sum()
    }
}

data class Number(val number: Long, val start: Point, val end: Point, val maxY: Int, val maxX: Int) {
    fun getNeighbours(): List<Point> {
        val neighbours = mutableListOf<Point>()

        for (y in (start.y - 1).coerceAtLeast(0) ..   (end.y + 1).coerceAtMost(maxY)) {
            for (x in (start.x - 1).coerceAtLeast(0) .. (end.x + 1).coerceAtMost(maxX)) {
                if ((x >= start.x && x <= end.x) && (y >= start.y && y <= end.y)) {
                    continue
                }

                neighbours.add(Point(x, y))
            }
        }

        return neighbours
    }

    fun isPartOfNumber(point: Point): Boolean {
        return (point.x >= start.x && point.x <= end.x)
    }
}

data class Symbol(val symbol: Char, val point: Point) {
    fun isSymbol(char: Char): Boolean {
        return symbol == char
    }

    fun getNeighbours(): List<Point> {
        val neighbours = mutableListOf<Point>()

        for (y in (point.y - 1).coerceAtLeast(0) ..   (point.y + 1)) {
            for (x in (point.x - 1).coerceAtLeast(0) .. (point.x + 1)) {
                if ((x >= point.x && x <= point.x) && (y >= point.y && y <= point.y)) {
                    continue
                }

                neighbours.add(Point(x, y))
            }
        }

        return neighbours
    }
}