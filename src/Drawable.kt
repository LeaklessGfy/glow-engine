import org.w3c.dom.CanvasRenderingContext2D

interface Drawable {
    @JsName("draw")
    fun draw(ctx: CanvasRenderingContext2D)
}
