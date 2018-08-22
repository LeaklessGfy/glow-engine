package map

import asset.Asset
import org.w3c.dom.CanvasRenderingContext2D

class Map(private val tiles: Array<Array<Tile>>) : Asset {
    @JsName("Factory")
    companion object Factory {
        @JsName("create")
        fun create(background: Asset): Map {
            val tiles = Array(4) { x -> Array(4) { y -> Tile(Point(x, y, 0), background)} }
            return Map(tiles)
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
        for (mY in 0 until tiles.size) {
            for (mX in 0 until tiles.size) {
                tiles[mX][mY].draw(ctx, x, y)
            }
        }
    }
}
