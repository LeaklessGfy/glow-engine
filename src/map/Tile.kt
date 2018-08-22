package map

import asset.Asset
import org.w3c.dom.CanvasRenderingContext2D

class Tile(private val point: Point, private val background: Asset, var overlay: Asset? = null): Asset {
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
        //val mX = point.x * background.width().toDouble()
        //val mY = point.y * background.height().toDouble()

        val w = background.width() / 2
        val h = (background.height() / 3.5f)

        val dx = (point.x - point.y) * w
        val dy = (point.x + point.y) * h

        background.draw(ctx, dx.toDouble(), dy.toDouble())
        overlay?.draw(ctx, dx.toDouble(), dy.toDouble())
    }
}
