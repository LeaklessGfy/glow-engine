import map.Map
import map.PointD
import map.TileHolder
import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLCanvasElement
import org.w3c.dom.events.MouseEvent
import wrestler.Wrestler

class Engine(
        private val canvas: HTMLCanvasElement,
        private val map: Map,
        private val active: Wrestler,
        private val players: TileHolder
) {
    private val ctx: CanvasRenderingContext2D = canvas.getContext("2d") as CanvasRenderingContext2D
    private val offset: PointD = PointD((canvas.width / 2).toDouble(), ((canvas.height / 2) - (map.height() / 3)).toDouble())

    fun onHover(e: MouseEvent) {
        val pos = realPos(e)
        val tile = map.screenToIso(pos.x, pos.y)
        map.applyOn { if(it != tile) it.onLeave() }
        tile?.run {
            map.isFree(point.x, point.y, point.z)
            onHover()
        }
        draw()
    }

    fun onClick(e: MouseEvent) {
        val pos = realPos(e)
        map.screenToIso(pos.x, pos.y)?.run {
            if (map.isFree(point.x, point.y, point.z)) {
                players.place(active, this)
            }
        }
        draw()
    }

    fun draw() {
        map.draw(ctx, offset.x, offset.y)
    }

    fun active(): Wrestler {
        return active
    }

    private fun realPos(e: MouseEvent): PointD {
        val r = canvas.getBoundingClientRect()
        return PointD(e.clientX - r.left - offset.x, e.clientY - r.top - offset.y)
    }
}
