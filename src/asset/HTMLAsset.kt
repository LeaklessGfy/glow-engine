package asset

import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLImageElement
import kotlin.browser.window

class HTMLAsset(private val image: HTMLImageElement, private val obstacle: Boolean = true): Asset {
    private var loaded: Boolean = false
    var xOffset = 0.0
    var yOffset = 0.0

    companion object Factory {
        fun init(paths: Array<String>, callback: (assets: Map<String, HTMLAsset>) -> Unit) {
            var counter = 0
            val assets = HashMap<String, HTMLAsset>()

            for (path in paths) {
                val img = window.document.createElement("img") as HTMLImageElement
                val asset = HTMLAsset(img)
                img.onload = { _ ->
                    counter++
                    asset.loaded = true
                    assets[path] = asset
                    if (counter >= paths.size) {
                        callback(assets)
                    }
                }
                img.onerror = { _, _, _, _, _ ->
                    throw Exception("Bad url : $path")
                }
                img.src = path
            }
        }
    }

    override fun width(): Int {
        return image.width
    }

    override fun height(): Int {
        return image.height
    }

    override fun loaded(): Boolean {
        return loaded
    }

    override fun draw(ctx: CanvasRenderingContext2D, x: Double, y: Double) {
        ctx.drawImage(image, x + xOffset, y + yOffset)
    }

    override fun offset(x: Double, y: Double): Asset {
        xOffset = x
        yOffset = y
        return this
    }

    override fun obstacle(): Boolean {
        return obstacle
    }
}
