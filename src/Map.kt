import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLImageElement
import kotlin.browser.window
import kotlin.math.PI

class Map(image: String) : Drawable {
    private val img: HTMLImageElement = window.document.createElement("img") as HTMLImageElement

    init {
        img.src = image
    }

    override fun draw(ctx: CanvasRenderingContext2D) {
        ctx.fillStyle = "red"
        ctx.beginPath()
        ctx.strokeStyle = "red"
        ctx.ellipse(100.0, 100.0, 50.0, 75.0, 0.0, 0.0, 2 * PI)
        ctx.fill()
        ctx.stroke()
        img.onload = { _ ->
            ctx.drawImage(img, 100.0, 100.0)
        }
    }
}