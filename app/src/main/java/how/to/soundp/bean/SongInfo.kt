package how.to.soundp.bean

/**
 *author : jichang
 *time   : 2017/06/07
 *desc   :
 *version: 1.0
 */
data class SongListResp(val s: String)

data class SongPlayResp(val a: String)

data class SongInfo(val error_code: Long,
                    val songurl: SongUrl)

data class SongUrl(val url: List<Url>)

data class Url(val show_link: String,
               val file_link: String)