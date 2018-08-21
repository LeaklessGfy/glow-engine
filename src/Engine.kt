import org.w3c.dom.CanvasRenderingContext2D

class Engine(private val map: Map): Drawable {
    override fun draw(ctx: CanvasRenderingContext2D) {
        ctx.fillStyle = "green"
        ctx.fillRect(0.0, 0.0, 50.0, 50.0)
        map.draw(ctx)
    }
}
