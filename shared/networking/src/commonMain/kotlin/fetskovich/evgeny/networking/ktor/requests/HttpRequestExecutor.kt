package fetskovich.evgeny.networking.ktor.requests

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.statement.HttpResponse
import io.ktor.util.reflect.TypeInfo
import io.ktor.utils.io.core.Closeable

interface HttpRequestExecutor : Closeable {

    suspend fun <T : Any> perform(
        info: TypeInfo,
        block: suspend (HttpClient) -> HttpResponse
    ): T

    suspend fun <T : Any> perform(
        builder: HttpRequestBuilder,
        block: suspend (response: HttpResponse) -> T
    )
}
