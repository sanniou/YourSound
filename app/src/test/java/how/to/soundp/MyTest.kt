package how.to.soundp

import how.to.soundp.http.BaiduMusicApi
import io.reactivex.Flowable
import org.junit.Test
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription


/**
 *author : jichang
 *time   : 2017/06/07
 *desc   :
 *version: 1.0
 */
class MyTest {

    @Test
    fun testGet() {
        testBase(BaiduMusicApi.instance
                .search("七里香"))
    }

    @Test
    fun testCatalogSug() {
        testBase(BaiduMusicApi.instance
                .requestCatalogSug("七里香"))
    }

    @Test
    fun testBillList() {
        testBase(BaiduMusicApi.instance
                .requestBillList(1))
    }


    fun <T> testBase(flowable: Flowable<T>) {
        flowable.doOnCancel { System.out.println("OnCancel") }
                .doOnSubscribe { System.out.println("OnSubscribe-flowable") }
                .subscribe(object : Subscriber<T> {
                    override fun onError(t: Throwable?) {
                        System.out.println("onError$t")
                    }

                    override fun onNext(t: T?) {
                        System.out.println("onNext $t")

                    }

                    override fun onComplete() {
                        System.out.println("onComplete")

                    }

                    override fun onSubscribe(s: Subscription?) {
                        System.out.println("onSubscribe-Subscriber")
                        s?.request(1)

                    }
                })
    }


}