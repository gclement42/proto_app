class AlbumData(name: String, artist: String, year: Int, image: Int) {
    private val _name: String = name
    private val _artist: String = artist
    private val _image: Int = image
    private val _year: Int = year

    fun getArtist(): String {
        return _artist
    }

    fun getName(): String {
        return _name
    }

    fun getImage(): Int {
        return _image
    }

    fun getYear(): Int {
        return _year
    }
}