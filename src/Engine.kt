import asset.Drawable
import map.Map
import org.w3c.dom.CanvasRenderingContext2D

class Engine(private val map: Map): Drawable {
    override fun draw(ctx: CanvasRenderingContext2D, x: Double, y: Double) {
        map.draw(ctx, x, y)
    }
}
