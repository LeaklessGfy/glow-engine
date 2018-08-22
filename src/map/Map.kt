package map

import asset.Asset
import org.w3c.dom.CanvasRenderingContext2D

class Map(private val tiles: Array<Array<Tile>>) : Asset {
    companion object Factory {
        @JsName("create")
        fun create(background: Asset): Map {
            val tiles = Array(4) { x -> Array(4) { y -> Tile(Point(x, y, 0), background)} }
            return Map(tiles)
        }
    }

    fun onHover(pos: PointD, offset: PointD) {
        val iso = screenToIso(pos, offset)
        if ((iso.x >= 0 && iso.y < tiles.size) && (iso.x >= 0 && iso.y < tiles.size)) {
            tiles[iso.x][iso.y].onHover()
        }
    }

    override fun width(): Int {
        return tiles[0][0].width() * tiles.size
    }

    override fun height(): Int {
        return tiles[0][0].height() * tiles.size
    }

    override fun loaded(): Boolean {
        return tiles.all { a -> a.all { t -> t.loaded() } }
    }

    override fun draw(ctx: CanvasRenderingContext2D, x: Double, y: Double) {
        ctx.fillStyle = "black"
        ctx.fillRect(0.0, 0.0, ctx.canvas.width.toDouble(), ctx.canvas.height.toDouble())

        for (mY in 0 until tiles.size) {
            for (mX in 0 until tiles.size) {
                val screen = isoToScreen(mX, mY)
                tiles[mX][mY].draw(ctx, screen.x + x, screen.y + y)
            }
        }
    }

    private fun isoToScreen(x: Int, y: Int): PointD {
        val w = Setting.TILE_WIDHT / 2
        val h = Setting.TILE_HEIGT / 3.5f

        val dx = ((x - y) * w) - w // - alphaWidth
        val dy = (x + y) * h

        return PointD(dx.toDouble(), dy.toDouble())
    }

    private fun screenToIso(pos: PointD, offset: PointD): Point {
        val tileWidth = Setting.TILE_WIDHT
        val tileHeight = Setting.TILE_HEIGT // - alphaHeight
        val realX = pos.x - offset.x
        val realY = pos.y - offset.y

        val fx = ((realX / tileWidth) + (realY / (tileHeight / 1.75))).toInt()
        val fy = ((realY / (tileHeight / 1.75)) - (realX / tileWidth)).toInt()

        return Point(fx, fy)
    }
}
