package how.to.soundp.http

import java.net.URLEncoder
import java.security.MessageDigest
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

/**
 *author : jichang
 *time   : 2017/06/07
 *desc   :
 * https://github.com/aa112901/remusic/blob/master/app/src/main/java/com/wm/remusic/net/AESTools.java
 *version: 1.0
 */

private val INPUT = "2012171402992850"
private val IV = "2012061402992850"
private val CHARS = charArrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F')

fun encrpty(param: String): String {

    val str = MessageDigest.getInstance("MD5")
            .run {
                update(INPUT.toByteArray())
                digest()
            }.fold(StringBuilder(INPUT.length * 2)) {
        st, byte ->
        st.append(CHARS[(byte.toInt() and 0xF0).ushr(4)])
        st.append(CHARS[byte.toInt() and 0xF])
        st
    }.toString()
    val keySpec = SecretKeySpec(str.substring(INPUT.length).toByteArray(), "AES")
    Cipher.getInstance("AES/CBC/PKCS5Padding")
            .init(1, keySpec, IvParameterSpec(IV.toByteArray()))
    URLEncoder.encode(BytesHandler.getChars)
    return ""
}