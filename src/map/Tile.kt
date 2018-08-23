package map

import asset.Asset
import org.w3c.dom.CanvasRenderingContext2D

class Tile(val point: Point, private val background: Asset?, var overlay: Asset? = null): Asset {
    private var hover: Boolean = false

    fun onHover() {
        hover = true
    }

    fun onLeave() {
        hover = false
    }

    fun isFree(): Boolean {
        return background == null
    }

    override fun width(): Int {
        return background?.width() ?: Setting.TILE_WIDTH
    }

    override fun height(): Int {
        return background?.height() ?: Setting.TILE_HEIGHT
    }

    override fun loaded(): Boolean {
        return background?.loaded() ?: true
    }

    override fun draw(ctx: CanvasRenderingContext2D, x: Double, y: Double) {
        // Grid representation
        // val mX = point.x * background.width().toDouble()
        // val mY = point.y * background.height().toDouble()
        background?.draw(ctx, x, y)
        overlay?.draw(ctx, x, y)

        if (hover) {
            ctx.fillStyle = "red"
            ctx.fillRect(x, y, Setting.TILE_WIDTH.toDouble(), Setting.TILE_HEIGHT.toDouble())
        }
    }

    override fun offset(x: Double, y: Double): Asset {
        return background?.offset(x, y) ?: this
    }

    override fun obstacle(): Boolean {
        return ((background?.obstacle() ?: false) || (overlay?.obstacle() ?: false))
    }

    override fun toString(): String {
        return point.toString()
    }
}
