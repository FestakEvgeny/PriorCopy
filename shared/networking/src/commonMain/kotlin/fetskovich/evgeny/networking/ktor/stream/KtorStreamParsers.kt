package fetskovich.evgeny.networking.ktor.stream

import fetskovich.evgeny.networking.ktor.setup.json.KtorJson
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.readUTF8Line
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.encodeToJsonElement
import kotlinx.serialization.json.jsonObject

private const val STREAM_PREFIX = "data:"
private const val STREAM_END_TRIGGER = "$STREAM_PREFIX [DONE]"

internal inline fun <reified T> createStreamRequest(serializable: T): JsonElement {
    val enableStream = "stream" to JsonPrimitive(true)
    val json = KtorJson.encodeToJsonElement(serializable)
    val map = json.jsonObject.toMutableMap().also { it += enableStream }
    return JsonObject(map)
}

internal suspend inline fun <reified T> FlowCollector<T>.createStreamResponse(response: HttpResponse) {
    val channel: ByteReadChannel = response.body()
    while (!channel.isClosedForRead) {
        val line = channel.readUTF8Line() ?: continue
        val value: T = when {
            line.startsWith(STREAM_END_TRIGGER) -> break
            line.startsWith(STREAM_PREFIX) -> KtorJson.decodeFromString(
                line.removePrefix(STREAM_PREFIX)
            )
            else -> continue
        }
        emit(value)
    }
}
