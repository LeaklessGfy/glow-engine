package wrestler

import asset.Asset
import org.w3c.dom.CanvasRenderingContext2D

class Wrestler(
        private val name: String,
        private val image: Asset,
        private val gender: Int = 0,
        private val category: Int = 0,
        private val health: Bar = Bar(10, 100),
        private val stamina: Bar = Bar(10, 10),
        private val intensity: Bar = Bar(0, 10),
        private val actions: List<Action> = listOf(),
        private val combat: Combat = Combat(),
        private val moral: Moral = Moral(),
        private val crowd: Crowd = Crowd(0, 10)
): Asset {
    override fun width(): Int {
        return image.width()
    }

    override fun height(): Int {
        return image.height()
    }

    override fun loaded(): Boolean {
        return image.loaded()
    }

    override fun draw(ctx: CanvasRenderingContext2D, x: Double, y: Double) {
        image.draw(ctx, x, y)
    }

    override fun offset(x: Double, y: Double): Asset {
        return image.offset(x, y)
    }

    override fun obstacle(): Boolean {
        return true
    }
}
