package map

data class Point(var x: Int, var y: Int, val z: Int) {
    constructor(x: Int, y: Int) : this(x, y, 0)

    override fun toString(): String {
        return "x: $x, y: $y, z: $z"
    }
}