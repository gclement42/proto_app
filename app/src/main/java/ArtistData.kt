class ArtistData(name: String, image: Int, subscribers: Int) {
    private val _name: String = name
    private val _image: Int = image
    private val _subscribers: Int = subscribers

    fun getName(): String {
        return _name
    }

    fun getImage(): Int {
        return _image
    }

    fun getSubscribers(): Int {
        return _subscribers
    }
}