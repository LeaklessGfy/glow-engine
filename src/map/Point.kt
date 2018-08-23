package map

data class Point(val x: Int, val y: Int, val z: Int) {
    constructor(x: Int, y: Int) : this(x, y, 0)

    override fun toString(): String {
        return "x: $x, y: $y, z: $z"
    }
}