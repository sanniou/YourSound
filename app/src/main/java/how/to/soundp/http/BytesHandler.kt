package http


/**
 *author : jichang
 *time   : 2017/06/08
 *desc   :
 *version: 1.0
 */
object BytesHandler {
    private val chars = CharArray(64)
    private val bytes = ByteArray(128)

    init {

        var j = 0
        for (i in 65..90) {
            chars[j++] = i.toChar()
        }

        for (i in 97..122) {
            chars[j++] = i.toChar()
        }
        for (i in 48..57) {
            chars[j++] = i.toChar()
        }
        chars[j++] = 43.toChar()
        chars[j] = 47.toChar()
        for (i in 0 until 128) {
            bytes[i] = -1
        }

        for (i in 0 until 64) {
            bytes[chars[i].toInt()] = i.toByte()
        }

    }

    fun getChars(bytes: ByteArray, start: Int = 0, length: Int = bytes.size): CharArray {
        val num0 = (length * 4 + 2) / 3
        val CHAR = 61.toChar()
        val result = CharArray((length + 2) / 3 * 4)
        val max = start + length
        var bytesIndex = start
        var resultIndex = 0

        while (bytesIndex < max) {
            val n0 = bytes[bytesIndex++].toInt() and 0xFF
            var n1 = 0
            if (bytesIndex < max) {
                n1 = bytes[bytesIndex++].toInt() and 0xFF
            }

            var n2 = 0
            if (bytesIndex < max) {
                n2 = bytes[bytesIndex++].toInt() and 0xFF
            }

            val i1 = n0.ushr(2)
            val i2 = n0 and 0x3 shl 4 or n1.ushr(4)
            val i3 = n1 and 0xF shl 2 or n2.ushr(6)
            val i4 = n2 and 0x3F

            result[resultIndex++] = chars[i1]
            result[resultIndex++] = chars[i2]

            var c: Char
            c = if (resultIndex < num0) chars[i3] else CHAR
            result[resultIndex++] = c

            c = if (resultIndex < num0) chars[i4] else CHAR
            result[resultIndex++] = c
        }
        return result
    }


}
