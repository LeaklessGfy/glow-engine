package map

import asset.Asset
import org.w3c.dom.CanvasRenderingContext2D

class Tile(private val point: Point, private val background: Asset, var overlay: Asset? = null): Asset {
    private var hover: Boolean = false

    fun onHover() {
        hover = true
    }

    fun onLeave() {
        hover = false
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

        if (hover) {
            ctx.fillStyle = "red"
            ctx.fillRect(x, y, Setting.TILE_WIDTH.toDouble(), Setting.TILE_HEIGHT.toDouble())
        }
    }

    override fun offset(x: Double, y: Double): Asset {
        return background.offset(x, y)
    }

    override fun obstacle(): Boolean {
        return background.obstacle() && overlay?.obstacle() ?: false
    }

    override fun toString(): String {
        return point.toString()
    }
}
