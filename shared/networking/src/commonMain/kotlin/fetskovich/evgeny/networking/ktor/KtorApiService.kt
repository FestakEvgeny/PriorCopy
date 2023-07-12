package fetskovich.evgeny.networking.ktor

import fetskovich.evgeny.networking.ktor.requests.HttpRequestExecutor
import fetskovich.evgeny.networking.ktor.stream.createStreamRequest
import fetskovich.evgeny.networking.ktor.stream.createStreamResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.accept
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.contentType
import io.ktor.http.headers
import io.ktor.util.reflect.typeInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

private const val HEADER_STREAM_CACHE_CONTROL_HEADER = "no-cache"
private const val HEADER_STREAM_CONNECTION = "keep-alive"

internal suspend inline fun <reified Response> HttpRequestExecutor.get(
    url: String,
    params: Map<String, Any?> = emptyMap(),
): Response {
    return perform { client ->
        client.get {
            url(path = url)
            if (params.isNotEmpty()) {
                params.forEach { (name, value) ->
                    value?.let { parameter(name, value) }
                }
            }

            contentType(ContentType.Application.Json)
        }.body()
    }
}

internal suspend inline fun <reified Request, reified Response> HttpRequestExecutor.post(
    url: String,
    params: Map<String, Any?> = emptyMap(),
    request: Request,
): Response {
    return perform { client ->
        client.post {
            url(path = url)
            if (params.isNotEmpty()) {
                params.forEach { (name, value) ->
                    value?.let { parameter(name, value) }
                }
            }
            setBody(request)
            contentType(ContentType.Application.Json)
        }.body()
    }
}

internal suspend inline fun <reified Request, reified Response> HttpRequestExecutor.stream(
    url: String,
    request: Request
): Flow<Response> {
    val builder = HttpRequestBuilder().apply {
        method = HttpMethod.Post
        url(path = url)
        setBody(createStreamRequest(request))
        contentType(ContentType.Application.Json)
        accept(ContentType.Text.EventStream)
        headers {
            append(HttpHeaders.CacheControl, HEADER_STREAM_CACHE_CONTROL_HEADER)
            append(HttpHeaders.Connection, HEADER_STREAM_CONNECTION)
        }
    }

    return flow {
        perform(builder) { response ->
            createStreamResponse(response)
        }
    }
}

// Syntax's sugar to improve request execution readability
suspend inline fun <reified T> HttpRequestExecutor.perform(
    noinline block: suspend (HttpClient) -> HttpResponse
): T {
    return perform(typeInfo<T>(), block)
}
