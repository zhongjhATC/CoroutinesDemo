package com.zhongjh.coroutinesdemo.bean

/**
 *
 * @author zhongjh
 * @date 2022/3/30
 *
 * 封装接口的返回实体类
 */
class ApiEntity<T> {

    var code = 0
    var msg: String? = null
    var errorCode = 0
    var errorMsg: String? = null
    var data: T? = null
}