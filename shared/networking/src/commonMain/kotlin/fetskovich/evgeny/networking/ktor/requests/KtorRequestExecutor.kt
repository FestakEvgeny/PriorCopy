package fetskovich.evgeny.networking.ktor.requests

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.HttpStatement
import io.ktor.util.reflect.TypeInfo

internal class KtorRequestExecutor(
    private val httpClient: HttpClient
) : HttpRequestExecutor {

    /** Perform an HTTP request and get a result */
    override suspend fun <T : Any> perform(
        info: TypeInfo,
        block: suspend (
            HttpClient
        ) -> HttpResponse
    ): T {
        try {
            val response = block(httpClient)
            return response.body(info)
        } catch (e: Exception) {
            // Custom solution so we can parse some custom errors here if requires
            throw e
        }
    }

    override suspend fun <T : Any> perform(
        builder: HttpRequestBuilder,
        block: suspend (response: HttpResponse) -> T
    ) {
        try {
            HttpStatement(
                builder = builder,
                client = httpClient
            ).execute(block)
        } catch (e: Exception) {
            // Custom solution so we can parse some custom errors here if requires
            throw e
        }
    }

    override fun close() {
        httpClient.close()
    }
}