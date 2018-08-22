import asset.Drawable
import map.Map
import map.PointD
import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLCanvasElement
import org.w3c.dom.events.MouseEvent

class Engine(private val map: Map): Drawable {
    @JsName("onHover")
    fun onHover(e: MouseEvent, canvas: HTMLCanvasElement) {
        val ctx = canvas.getContext("2d") as CanvasRenderingContext2D
        val r = canvas.getBoundingClientRect()
        val pos = PointD(e.clientX - r.left, e.clientY - r.top)
        val offset = offset(ctx)

        map.onHover(pos, offset)
    }

    override fun draw(ctx: CanvasRenderingContext2D, x: Double, y: Double) {
        val offset = offset(ctx)
        map.draw(ctx, offset.x, offset.y)
    }

    private fun offset(ctx: CanvasRenderingContext2D): PointD {
        val canvas = ctx.canvas
        return PointD((canvas.width / 2).toDouble(), ((canvas.height / 2) - (map.height() / 3)).toDouble())
    }
}
