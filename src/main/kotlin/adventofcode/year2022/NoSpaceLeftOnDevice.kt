package adventofcode.year2022

import adventofcode.Solver

object NoSpaceLeftOnDevice: Solver(year = 2022, day = 7) {


    override fun part1(input: List<String>): String {
        val explorer = TerminalExplorer()
        explorer.explore(input)
        return explorer.allDirectories
            .map(Directory::getTotalSize)
            .filter { it <= 100000 }
            .sum().toString()
    }

    override fun part2(input: List<String>): String {
        val explorer = TerminalExplorer()
        explorer.explore(input)
        return explorer.allDirectories
            .map(Directory::getTotalSize)
            .sorted()
            .first { 40000000 + it >= explorer.root.getTotalSize() }
            .toString()
    }

    class TerminalExplorer {
        companion object {
            private val cdPattern = Regex("^\\\$ cd (.+)$")
            private val lsResultPattern = Regex("(?:dir|\\d+) (.+)")
        }
        fun explore(input: List<String>) {
            for (next in input) {
                cdPattern.matchEntire(next)?.let {
                    cd(it.groupValues[1])
                }
                if (lsResultPattern.matches(next)) {
                    ls(next)
                }
            }
        }
        val root = Directory()
        val allDirectories = mutableListOf(root)
        var current: Directory? = null
        fun cd(dir: String) {
            if (dir == "..") {
                current = current?.previous
                return
            }
            if (dir == "/") {
                current = root
                return
            }
            val file = current?.get(dir) ?: throw Exception("Cannot navigate from current to $dir")
            if (file is Directory) {
                current = file
            }
            else throw Exception("Cannot visit a file!")
        }
        fun ls(result: String) {
            val (size, name) = result.split(" ")
            if (size == "dir") current!!.computeIfAbsent(name) { Directory(current).apply { allDirectories.add(this) } }
            else current!!.computeIfAbsent(name) { File(size.toInt()) }
        }
    }

    interface FileOrDirectory { fun getTotalSize(): Int }

    class File(private val size: Int) : FileOrDirectory {
        override fun getTotalSize() = size
        override fun toString() = size.toString()
    }
    class Directory(val previous: Directory? = null) : FileOrDirectory, MutableMap<String, FileOrDirectory> by mutableMapOf() {
        override fun getTotalSize() = values.sumOf { it.getTotalSize() }
        override fun toString() = entries.joinToString(prefix = "(", postfix = ")")
    }
}