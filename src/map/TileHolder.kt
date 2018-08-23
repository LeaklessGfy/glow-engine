package map

import wrestler.Wrestler

class TileHolder {
    private val holder: HashMap<Wrestler, Tile> = HashMap()

    fun place(wrestler: Wrestler, tile: Tile) {
        holder[wrestler]?.overlay = null
        holder[wrestler] = tile
        tile.overlay = wrestler
    }
}
