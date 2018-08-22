package asset

interface Asset: Drawable {
    fun width(): Int
    fun height(): Int
    fun loaded(): Boolean
}
