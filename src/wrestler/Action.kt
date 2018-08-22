package wrestler

import map.Point

class Action(private val name: String, private val image: String, private val positions: List<Position>) {
    fun isAvaiable(src: Point, dst: Point): Boolean {
        for (position in positions) {
            if (position.isAvailable(src, dst)) {
                return true
            }
        }
        return false
    }
}
