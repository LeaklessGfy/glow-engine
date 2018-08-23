package asset

interface Asset: Drawable {
    fun width(): Int
    fun height(): Int
    fun loaded(): Boolean
    fun offset(x: Double, y: Double): Asset
    fun obstacle(): Boolean
}
