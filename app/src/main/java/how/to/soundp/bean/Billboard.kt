package how.to.soundp.bean

/**
 *author : jichang
 *time   : 2017/06/09
 *desc   :
 *version: 1.0
 */
data class BillboardP(val error_code: Long,
                      val song_list: List<Song>,
                      val billboard: Billboard)

data class Song(val artist_id: Long,
                val language: String,
                val pic_big: String,
                val pic_small: String,
                val country: String,
                val area: String,
                val publishtime: String,
                val album_no: Long,
                val lrclink: String,
                val copy_type: Long,
                val hot: Long,
                val all_artist_ting_uid: String,
                val resource_type: Long,
                val is_nem: Long,
                val rank_change: Long,
                val rank: Long,
                val all_artist_id: String,
                val style: String,
                val del_status: Long,
                val relate_status: Long,
                val toneid: Long,
                val all_rate: String,
                val file_duration: Long,
                val has_mv_mobile: Long,
                val version: Long,
                val bitrate_fee: String,
                val biaoshi: String,
                val song_id: Long,
                val title: String,
                val ting_uid: Long,
                val author: String,
                val album_id: Long,
                val album_title: String,
                val is_first_publish: Long,
                val havehigh: Long,
                val charge: Long,
                val has_mv: Long,
                val learn: Long,
                val song_source: String,
                val piao_id: Long,
                val korean_bb_song: Long,
                val resource_type_ext: Long,
                val mv_provider: Long,
                val artist_name: String)

data class Billboard(val billboard_type: Int,
                     val billboard_no: Int,
                     val update_date: String,
                     val billboard_songnum: Int,
                     val havemore: Int,
                     val name: String,
                     val comment: String,
                     val pic_s640: String,
                     val pic_s440: String,
                     val pic_s260: String,
                     val pic_s210: String,
                     val web_url: String)