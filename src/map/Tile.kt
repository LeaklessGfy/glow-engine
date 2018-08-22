package map

import asset.Asset
import org.w3c.dom.CanvasRenderingContext2D

class Tile(private val point: Point, private val background: Asset, var overlay: Asset? = null): Asset {
    fun onHover() {
        println(point)
    }

    override fun width(): Int {
        return background.width()
    }

    override fun height(): Int {
        return background.height()
    }

    override fun loaded(): Boolean {
        return background.loaded()
    }

    override fun draw(ctx: CanvasRenderingContext2D, x: Double, y: Double) {
        // Grid representation
        // val mX = point.x * background.width().toDouble()
        // val mY = point.y * background.height().toDouble()

        background.draw(ctx, x, y)
        overlay?.draw(ctx, x, y)
    }

    override fun toString(): String {
        return point.toString()
    }
}
