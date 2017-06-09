package how.to.soundp

import how.to.soundp.http.BaiduMusicApi
import io.reactivex.Flowable
import okio.Okio
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import java.io.File


/**
 *author : jichang
 *time   : 2017/06/07
 *desc   :
 *version: 1.0
 */
class MyTest {
    @Rule
    @JvmField var temporaryFolder = TemporaryFolder()

    @Test
    fun testStart() {
//        testBillList()
        testBase(BaiduMusicApi.instance
                .getSongInfo(541680641)) {
            println(it)
        }
    }

    fun testBillList() {
        testBase(BaiduMusicApi.instance
                .getBillList(1)) {
            val sid = it.song_list[0].song_id
            println(sid)
            testSongInfo(sid)
        }
    }

    fun testSongInfo(sid: Long) {
        testBase(BaiduMusicApi.instance
                .getSongInfo(sid)) {
            val link = it.songurl.url.first().file_link
            print(link)
            testDownload(link)
        }
    }

    fun testDownload(link: String) {
        testBase(BaiduMusicApi.instance
                .download(link)) {
            body ->
            //val newFile = temporaryFolder.newFile("sss")
            val newFile = File("D:\\asdfas.mp3")
            val sink = Okio.buffer(Okio.sink(newFile))
            sink.use {
                it.writeAll(body.source())
            }
            println(newFile.path + newFile.length())
        }
    }


    fun <T> testBase(flowable: Flowable<T>, block: (T) -> Unit) {
        flowable.doOnCancel { System.out.println("OnCancel") }
                .doOnSubscribe { System.out.println("OnSubscribe-flowable") }
                .subscribe(block)
    }


}