package asset

import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLImageElement
import kotlin.browser.window

class HTMLAsset(private val image: HTMLImageElement): Asset {
    private var loaded: Boolean = false

    companion object Factory {
        @JsName("init")
        fun init(paths: Array<String>, callback: (assets: dynamic, errors: dynamic) -> Nothing) {
            var counter = 0
            val assets: dynamic = object{}
            val errors = ArrayList<String>()

            for (path in paths) {
                val img = window.document.createElement("img") as HTMLImageElement
                val asset = HTMLAsset(img)
                img.onload = { _ ->
                    counter++
                    asset.loaded = true
                    assets[path] = asset
                    if (counter >= paths.size) {
                        callback(assets, errors.toTypedArray())
                    }
                }
                img.onerror = { _, _, _, _, _ ->
                    counter++
                    errors.add(path)
                    if (counter >= paths.size) {
                        callback(assets, errors.toTypedArray())
                    }
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
        ctx.drawImage(image, x, y)
    }
}
