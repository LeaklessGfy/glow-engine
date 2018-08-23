package map

import asset.Asset
import org.w3c.dom.CanvasRenderingContext2D

class Map(private val tiles: Array<Array<Array<Tile>>>) : Asset {
    companion object Factory {
        fun create(background: Asset): Map {
            val full = Array(3) { z -> Array(4 - z) { x -> Array(4 - z) { y -> Tile(Point(x, y, z), background)} } }
            return Map(full)
        }
    }

    fun tile(x: Int, y: Int, z: Int): Tile? {
        return tiles[toRealZ(z)][x][y]
    }

    fun isoToScreen(x: Int, y: Int, z: Int): PointD {
        val w = Setting.TILE_WIDTH / 2.0
        val h = Setting.TILE_HEIGHT / 3.5

        val dx = ((x - y) * w) - Setting.TILE_LEFT_MARGIN
        val dy = ((x + y) * h) - Setting.TILE_TOP_MARGIN - (z * Setting.TILE_HEIGHT / 2.4)

        return PointD(dx, dy)
    }

    fun screenToIso(x: Double, y: Double): Tile? {
        var tile: Tile? = null

        for (z in -1..1) {
            screenToIso(x, y, z)?.let {
                tile = it
            }
        }

        return tile
    }

    fun screenToIso(x: Double, y: Double, z: Int): Tile? {
        val tileWidth = Setting.TILE_WIDTH
        val tileHeight = Setting.TILE_HEIGHT / 1.75 // - alphaHeight

        val realY = y + (z * Setting.TILE_HEIGHT / 2.4)

        val fx = ((x / tileWidth) + (realY / tileHeight)).toInt()
        val fy = ((realY / tileHeight) - (x / tileWidth)).toInt()

        if (isValid(fx, fy, toRealZ(z))) {
            return tiles[toRealZ(z)][fx][fy]
        }

        return null
    }

    fun isValid(x: Int, y: Int, z: Int): Boolean {
        return z >= 0 && z < tiles.size && x >= 0 && x < tiles[z].size && y >= 0 && y < tiles[z].size
    }

    fun applyOn(applier: (t: Tile) -> Unit) {
        for (z in 0 until tiles.size) {
            for (y in 0 until tiles[z].size) {
                for (x in 0 until tiles[z].size) {
                    applier(tiles[z][x][y])
                }
            }
        }
    }

    override fun width(): Int {
        return Setting.TILE_WIDTH * tiles.size
    }

    override fun height(): Int {
        return Setting.TILE_HEIGHT * tiles.size
    }

    override fun loaded(): Boolean {
        return tiles.all { x -> x.all { y -> y.all { it.loaded() } } }
    }

    override fun draw(ctx: CanvasRenderingContext2D, x: Double, y: Double) {
        ctx.fillStyle = "black"
        ctx.fillRect(0.0, 0.0, ctx.canvas.width.toDouble(), ctx.canvas.height.toDouble())

        for (mZ in 0 until tiles.size) {
            for (mY in 0 until tiles[mZ].size) {
                for (mX in 0 until tiles[mZ].size) {
                    val screen = isoToScreen(mX, mY, mZ - 1)
                    tiles[mZ][mX][mY].draw(ctx, screen.x + x, screen.y + y)
                }
            }
        }
    }

    override fun offset(x: Double, y: Double): Asset {
        return this
    }

    override fun obstacle(): Boolean {
        return true
    }

    private fun toRealZ(z: Int): Int {
        return z + 1
    }
}
