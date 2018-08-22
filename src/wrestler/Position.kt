package wrestler

import map.Point
import kotlin.math.abs

class Position(private val xOffset: Int, private val yOffset: Int, private val zOffset: Int) {
    fun isAvailable(src: Point, dst: Point): Boolean {
        var offset = abs(dst.x - src.x)
        if (offset > xOffset) {
            return false
        }
        offset = abs(dst.y - src.y)
        if (offset > yOffset) {
            return false
        }
        offset = abs(dst.z - src.z)
        if (offset > zOffset) {
            return false
        }
        return true
    }
}