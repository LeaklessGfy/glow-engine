package wrestler

import asset.Asset
import asset.Drawable
import org.w3c.dom.CanvasRenderingContext2D

class Wrestler(
        private val name: String,
        private val image: Asset,
        private val gender: Int,
        private val category: Int,
        private val health: Bar,
        private val stamina: Bar,
        private val intensity: Bar,
        private val actions: List<Action>,
        private val combat: Combat,
        private val moral: Moral,
        private val crowd: Crowd
): Drawable {
    override fun draw(ctx: CanvasRenderingContext2D, x: Double, y: Double) {
        image.draw(ctx, x, y)
    }
}
