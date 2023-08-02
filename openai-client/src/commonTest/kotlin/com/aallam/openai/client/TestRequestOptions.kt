package com.aallam.openai.client

import com.aallam.openai.api.chat.ChatCompletionRequest
import com.aallam.openai.api.core.RequestOptions
import com.aallam.openai.api.model.ModelId
import kotlinx.serialization.json.addJsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import kotlinx.serialization.json.putJsonArray
import kotlin.test.Test
import kotlin.test.assertTrue

class TestRequestOptions : TestOpenAI() {

    @Test
    fun chatWithRequestOptions() = test {
        val request = ChatCompletionRequest(model = ModelId("non-existing-model"), messages = emptyList())
        val jsonObject = buildJsonObject {
            put("model", "gpt-3.5-turbo")
            putJsonArray("messages") {
                addJsonObject {
                    put("role", "system")
                    put("content", "You are a helpful assistant.!")
                }
                addJsonObject {
                    put("role", "user")
                    put("content", "Who won the world series in 2020?")
                }
            }
        }
        val requestOptions = RequestOptions(body = jsonObject)
        val completion = openAI.chatCompletion(request, requestOptions)
        assertTrue { completion.choices.isNotEmpty() }
    }
}
