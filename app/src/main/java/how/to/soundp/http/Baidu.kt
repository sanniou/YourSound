package how.to.soundp.http

import how.to.soundp.bean.*
import io.reactivex.Flowable
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.sql.Date

/**
 *author : jichang
 *time   : 2017/06/07
 *desc   :
 * https://github.com/aa112901/remusic/blob/master/app/src/main/java/com/wm/remusic/net/BMA.java
 *version: 1.0
 */

interface BaiduMusicApi {
    companion object {
        val instance by lazy {
            RetrofitFactory.create(BaiduMusicUtil.API_URL, BaiduMusicApi::class.java)
        }
        val SUCCESS_CODE = 22000
        val API_FROM = "android"
        val API_VERSION = "5.6.5.0"
        val API_FORMAT = "json"
        private val METHOD_GET_BILLLIST = "baidu.ting.billboard.billList"
        private val METHOD_GET_ADS = "baidu.ting.adv.showlist"
        private val METHOD_SEARCH_CATALOGSUG = "baidu.ting.search.catalogSug"
        private val METHOD_SEARCH_SUGGESTION = "baidu.ting.search.suggestion"
        private val METHOD_SEARCH_COMMON = "baidu.ting.search.common"
        private val METHOD_SEARCH_HOT = "baidu.ting.search.hot"

        private val METHOD_ARTIST_LIST = "baidu.ting.artist.getList" //order=1&offset=0&limit=5
        private val METHOD_ARTIST_HOT = "baidu.ting.artist.get72HotArtist"//order=1&offset=0&limit=50
        private val METHOD_ARTIST_INFO = "baidu.ting.artist.getinfo"
        private val METHOD_ARTIST_SONGLIST = "baidu.ting.artist.getSongList"

        private val METHOD_RADIO_LIST = "baidu.ting.radio.getCategoryList" //电台列表
        private val METHOD_RADIO_Channel = "baidu.ting.radio.getRecChannel" //电台列表 pageNo   pageSize
        private val METHOD_RADIO_SONG = "baidu.ting.radio.getChannelSong" //获取某个电台下的歌曲列表
        //pn=0&rn=10&channelname=public_tuijian_ktv
        private val METHOD_RADIO_RECOMMEND = "baidu.ting.radio.getRecommendRadioList" // 推荐电台（注意返回的都是乐播节目)
        private val METHOD_ALBUM_RECOMMEND = "baidu.ting.plaza.getRecommendAlbum"//新碟上架  offset=0&limit=50
        private val METHOD_ALBUM_INFO = "baidu.ting.album.getAlbumInfo"

        private val METHOD_SONG_NEW = "baidu.ting.plaza.getNewSongs" //新歌速递 limit=50

        private val METHOD_SONG_PLAY = "baidu.ting.song.play"
        private val METHOD_SONG_PLAYAAC = "baidu.ting.song.playAAC"
        private val METHOD_SONG_LRC = "baidu.ting.song.lry"
        private val METHOD_GET_RECOMMAND = "baidu.ting.song.getRecommandSongList"
        private val METHOD_GET_DOWNWEB = "baidu.ting.song.downWeb"


        private val METHOD_SONG_LRCPIC = "baidu.ting.search.lrcpic"
        private val METHOD_GET_SONGINFO = "baidu.ting.song.getInfos"
        private val METHOD_GET_FAVORITE = "baidu.ting.favorite.getFavoriteSong" //获取登陆用户的喜爱歌曲列表，其中bduss参数用来标示唯一的用户
        //pn=0&rn=50&bduss=UlXZ1dWbm9icDBrMm13aFcwZ282ejlTM1dyS1NEd2JPWXpQcDgyT0w0Vn5SUmhVQVFBQUFBJCQAAAAAAAAAAAEAAAB0L~cOeHl3MDQzNzM1AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAH-48FN~uPBTd
        private val METHOD_GET_ARTISTALUBMLIST = "baidu.ting.artist.getAlbumList"
        private val METHOD_QUERY_MERGE = "baidu.ting.search.merge"
    }


    /**
     *  获取列表
     *  @param type  : 1、新歌榜，2、热歌榜，6、KTV热歌榜  8、Billboard  11、摇滚榜，12、爵士，16、流行 18、Hito中文榜
     *  21、欧美金曲榜，22、经典老歌榜，23、情歌对唱榜，24、影视金曲榜，25、网络歌曲榜
     *
     *  @param size 返回条目数量
     *  @param offset 获取偏移
     *  type=1&offset=0&size=50
     */
    @GET("/v1/restserver/ting")
    fun requestBillList(
            @Query("type") type: Int,
            @Query("offset") offset: Int = 0,
            @Query("size") size: Int = 10,
            @Query("method") method: String = METHOD_GET_BILLLIST
    ): Flowable<ResponseBody>

    /**
     * 获取推广广告
     */
    @GET("/v1/restserver/ting")
    fun requestAds(
            @Query("method") method: String = METHOD_GET_ADS
    ): Flowable<ResponseBody>


    /**
     *  关键词建议
     *  @param query 搜索关键字
     *  query=%E5%B0%8F%E8%8B%B9%E6%9E%9C
     */
    @GET("/v1/restserver/ting")
    fun requestCatalogSug(
            @Query("query") query: String,
            @Query("method") method: String = METHOD_SEARCH_CATALOGSUG
    ): Flowable<CatalogSug>

    /**
     *  搜索建议：只有歌名
     *  @param query 搜索关键字
     *  query=%E5%B0%8F%E8%8B%B9%E6%9E%9C&page_no=1&page_size=30
     */
    @GET("/v1/restserver/ting")
    fun requestSuggestion(
            @Query("query") query: String,
            @Query("method") method: String = METHOD_SEARCH_SUGGESTION
    ): Flowable<CatalogSug>

    /**
     *  搜索结果
     *  @param query 搜索关键字
     *
     *  %E5%B0%8F%E8%8B%B9%E6%9E%9C&page_no=1&page_size=30
     */
    @GET("/v1/restserver/ting")
    fun requestCommon(
            @Query("query") query: String,
            @Query("method") method: String = METHOD_SEARCH_COMMON
    ): Flowable<CatalogSug>

    /**
     *  播放
     *  @param songId
     */
    @GET("/v1/restserver/ting")
    fun requestSongInfo(
            @Query("songid") songId: Int,
            @Query("method") method: String = METHOD_SONG_PLAY
    ): Flowable<SongPlayResp>

    /**
     *  播放
     *  @param songId
     */
    @GET("/v1/restserver/ting")
    fun requestSongAac(
            @Query("songid") songId: Int,
            @Query("method") method: String = METHOD_SONG_PLAYAAC
    ): Flowable<SongPlayResp>

    /**
     *  LRC歌词
     */
    @GET("/v1/restserver/ting")
    fun requestSongLrc(
            @Query("songid") songId: Int,
            @Query("method") method: String = METHOD_SONG_LRC
    ): Flowable<SongPlayResp>

    /**
     * 推荐列表
     *
     * @param num 返回条目数量
     */
    @GET("/v1/restserver/ting")
    fun requestSongRecommand(
            @Query("songid") songId: Int,
            @Query("num") num: Int = 5,
            @Query("method") method: String = METHOD_GET_RECOMMAND
    ): Flowable<SongPlayResp>

    /**
     * 下载
     *  @param songId
     * @param bit 码率 "24, 64, 128, 192, 256, 320, flac"
     * @param _t: //时间戳
     */
    @GET("/v1/restserver/ting")
    fun requestSongDown(
            @Query("songid") songId: Int,
            @Query("_t") date: Date,
            @Query("bit") bit: Int = 5,
            @Query("method") method: String = METHOD_GET_DOWNWEB
    ): Flowable<SongPlayResp>


    /**
     * 获取歌手信息
     *  @param artistId 歌手ting id
     */
    @GET("/v1/restserver/ting")
    fun requestArtistInfo(
            @Query("tinguid") artistId: Int,
            @Query("method") method: String = METHOD_ARTIST_INFO
    ): Flowable<ArtistInfo>

    /**
     * 获取歌手的歌曲列表
     *  tinguid: id, limits:6, use_cluster:1, order:2
     *  @param artistId 歌手ting id
     */
    @GET("/v1/restserver/ting")
    fun requestSongListFromArtist(
            @Query("tinguid") artistId: Int,
            @Query("order") order: Int,
            @Query("offset") offset: Int = 0,
            @Query("limits") limits: Int = 30,
            @Query("method") method: String = METHOD_ARTIST_SONGLIST): Flowable<SongListResp>

    /**
     * 获取歌曲信息
     * songid=8059247&ts=1408284347323&e=JoN56kTXnnbEpd9MVczkYJCSx%2FE1mkLx%2BPMIkTcOEu4%3D&nw=2&ucf=1&res=1
     */
    @GET("/v1/restserver/ting")
    fun requestMusicInfo(
            @Query("tinguid") artistId: Int,
            @Query("method") method: String = METHOD_GET_SONGINFO
    ): Flowable<ArtistInfo>

    /**
     * 获取歌手的专辑列表
     */
    @GET("/v1/restserver/ting")
    fun requestAlbumByArtist(
            @Query("method") method: String = METHOD_GET_ARTISTALUBMLIST,
            @Query("order") order: Int = 1,
            @Query("tinguid") artistId: Int,
            @Query("offset") offset: Int = 0,
            @Query("limits") limits: Int = 30): Flowable<ArtistAlbumListResp>

    /**
     * 专辑信息
     *
     * album_id=122314357
     */
    @GET("/v1/restserver/ting")
    fun requestAlbumDetail(
            @Query("method") method: String = METHOD_ALBUM_INFO,
            @Query("album_id") albumId: Long): Flowable<Album>


    /**
     * 关键字搜索
     */
    @GET("/v1/restserver/ting")
    fun search(
            @Query("query") keyword: String,
            @Query("method") method: String = METHOD_QUERY_MERGE,
            @Query("page_no") page: Int = 1,
            @Query("page_size") size: Int = 50,
            @Query("type") queryType: Int = -1,
            @Query("data_source") dataSource: Int = 0,
            @Query("use_cluster") useCluster: Int = 1): Flowable<QueryResult>
}


object RetrofitFactory {

    fun <T> create(url: String, clz: Class<T>): T {

        return Retrofit.Builder()
                .baseUrl(url)
                .client(OkHttpClient.Builder()
                        .addNetworkInterceptor {
                            val request = it.request()

                            val t1 = System.nanoTime()
                            println("============================\n" +
                                    "Sending request ${request.url()} \n" +
                                    "on ${it.connection()}\n" +
                                    "${request.headers()}\n" +
                                    "=========================\n")

                            val response = it.proceed(request)

                            val t2 = System.nanoTime()
                            val string = response.body()?.string()
                            println("============================\n" +
                                    "Received response for ${response.request().url()} \n" +
                                    "in ${(t2 - t1) / 1e6}ms\n" +
                                    "${response.headers()}\n" +
                                    "$string\n" +
                                    "============================\n"
                            )
                            //response.body().string() 只能使用一次，需要重新创建 response
                            response.newBuilder()
                                    .body(ResponseBody.create(response.body()?.contentType(), string))
                                    .build()
                        }
                        .addInterceptor {
                            val httpUrl = it.request().url().run {
                                newBuilder().scheme(scheme())
                                        .host(host())

                                        .addQueryParameter("from", BaiduMusicApi.API_FROM)
                                        .addQueryParameter("version", BaiduMusicApi.API_VERSION)
                                        .addQueryParameter("format", BaiduMusicApi.API_FORMAT)
                                        .build()
                            }
                            //拦截器设置 User-Agent ，否则会返导致403 error
                            val request = it.request()
                                    .newBuilder()
                                    .url(httpUrl)
                                    .addHeader("User-Agent", "a")
                                    .build()
                            it.proceed(request)
                        }.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(clz)
    }
}

/**
 * 使用百度音乐API的工具类
 */
object BaiduMusicUtil {
    val API_URL = "http://tingapi.ting.baidu.com"
    /**
     * 通过歌曲Id获取歌曲下载地址  同在线播放的地址
     */
    fun getDownloadUrlBySongId(songId: String): String {
        return "http://ting.baidu.com/data/music/links?songIds=$songId"
    }
}