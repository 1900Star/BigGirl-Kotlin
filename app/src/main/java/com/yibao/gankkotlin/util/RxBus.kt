package com.yibao.gankkotlin.util

import io.reactivex.Flowable
import io.reactivex.processors.FlowableProcessor
import io.reactivex.processors.PublishProcessor


/**
 *  @项目名：  MyKotlin
 *  @包名：    com.yibao.gankkotlin.util
 *  @文件名:   RxBus
 *  @author:   Stran
 *  @Email:   www.strangermy@outlook.com / www.stranger98@gmail.com
 *  @创建时间:  2018/1/20 17:17
 *  @描述：    {TODO}
 */
object RxBus {


    private val bus: FlowableProcessor<Any> = PublishProcessor.create<Any>().toSerialized()


    // 发送数据
    fun post(o: Any) {
        bus.onNext(o)
    }


    // 根据传递的 eventType 类型返回特定类型(eventType)的 被观察者
    fun <T> toFlowable(eventType: Class<T>): Flowable<T> = bus.ofType(eventType)


}