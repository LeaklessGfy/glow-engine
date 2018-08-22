package asset

import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLImageElement
import kotlin.browser.window

class HTMLAsset(private val image: HTMLImageElement): Asset {
    private var loaded: Boolean = false

    companion object Factory {
        @JsName("init")
        fun init(paths: Array<String>, callback: (assets: dynamic) -> Nothing) {
            var counter = 0
            val assets: dynamic = object{}

            for (path in paths) {
                val img = window.document.createElement("img") as HTMLImageElement
                val asset = HTMLAsset(img)
                img.onload = { _ ->
                    counter++
                    asset.loaded = true
                    if (counter >= paths.size) {
                        callback(assets)
                    }
                }
                img.onerror = { _, _, _, _, _ ->
                    counter++
                    if (counter >= paths.size) {
                        callback(assets)
                    }
                }
                img.src = path
                assets[path] = asset
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
        ctx.drawImage(image, x, y)
    }
}
