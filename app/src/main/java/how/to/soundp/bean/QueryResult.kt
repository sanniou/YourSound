package how.to.soundp.bean

/**
 *author : jichang
 *time   : 2017/06/07
 *desc   :
 *version: 1.0
 */
data class QueryResult(val error_code: Int, val result: Result)

data class Result(val query: String, val syn_words: String, val rqt_type: Int)