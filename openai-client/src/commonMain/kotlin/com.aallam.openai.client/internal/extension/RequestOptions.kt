package com.aallam.openai.client.internal.extension

import com.aallam.openai.api.core.RequestOptions
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlin.time.DurationUnit

/**
 * Set [RequestOptions] values if any to [HttpRequestBuilder].
 */
internal fun HttpRequestBuilder.requestOptions(requestOptions: RequestOptions?) {
    requestOptions?.run {
        if (method == HttpMethod.Post || method == HttpMethod.Put) {
            body?.let(::setBody)
        }
        timeout?.let { timeout ->
            timeout {
                timeout.socket?.let { socketTimeout -> socketTimeoutMillis = socketTimeout.toLong(DurationUnit.MILLISECONDS) }
                timeout.connect?.let { connectTimeout -> connectTimeoutMillis = connectTimeout.toLong(DurationUnit.MILLISECONDS) }
                timeout.request?.let { requestTimeout -> requestTimeoutMillis = requestTimeout.toLong(DurationUnit.MILLISECONDS) }
            }
        }
        headers?.let { headers -> headers.onEach { (key, value) -> header(key, value) } }
        urlParams?.let { urlParams -> urlParams.onEach { (key, value) -> parameter(key, value) } }
    }
}
