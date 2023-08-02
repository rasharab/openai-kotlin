package com.aallam.openai.api.core

import com.aallam.openai.api.http.Timeout
import kotlinx.serialization.json.JsonObject

/**
 * Class representing custom options for a request.
 *
 * @property timeout specifies the request's timeout duration.
 * @property headers request's custom HTTP headers
 * @property urlParams URL parameters to be added to the request URL
 * @property body custom request body to be sent with POST/PUT requests
 */
public class RequestOptions(
    public val timeout: Timeout? = null,
    public val headers: Map<String, Any>? = null,
    public val urlParams: Map<String, Any>? = null,
    public val body: JsonObject? = null,
)
