import asset.HTMLAsset
import map.Map
import map.TileHolder
import org.w3c.dom.HTMLCanvasElement
import org.w3c.dom.events.MouseEvent
import wrestler.Wrestler

class Client(private val canvas: HTMLCanvasElement) {
    private var map: Map? = null
    private var engine: Engine? = null

    fun init() {
        HTMLAsset.init(arrayOf("grass.png", "droid.png")) { assets ->
            map = Map.create(assets["grass.png"]!!).also { map ->
                val active = Wrestler("Triple H", assets["droid.png"]!!)
                active.offset(10.0, -5.0)

                val players = TileHolder()
                map.tile(0, 0, 1)?.let {
                    players.place(active, it)
                }

                engine = Engine(canvas, map, active, players).also { engine ->
                    canvas.onmousemove = { e -> engine.onHover(e as MouseEvent) }
                    canvas.onclick = { e -> engine.onClick(e as MouseEvent) }
                    engine.draw()
                }
            }
        }
    }
}
