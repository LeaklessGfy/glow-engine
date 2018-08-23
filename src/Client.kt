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
        HTMLAsset.init(arrayOf("res/tiles/grass.png", "res/players/droid.png", "res/players/monster.png")) { assets ->
            map = Map.create(assets["res/tiles/grass.png"]!!).also { map ->
                val active = Wrestler("Triple H", assets["res/players/droid.png"]!!)
                active.offset(10.0, -5.0)

                val enemy = Wrestler("Randy Orton", assets["res/players/monster.png"]!!)
                enemy.offset(0.0, 0.0)

                val players = TileHolder()
                map.tile(0, 0, 1)?.let {
                    players.place(active, it)
                }
                map.tile(0, 1, 1)?.let {
                    players.place(enemy, it)
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
