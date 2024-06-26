package io.github.glailton.uolhost.rule

import com.google.gson.Gson
import io.github.glailton.uolhost.core.utils.ApiEndpoint
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.rules.ExternalResource
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class WebServerRule(
) : ExternalResource(), KoinComponent {

    private lateinit var webServer: MockWebServer
    private val apiEndpoint: ApiEndpoint by inject()

    override fun before() {
        webServer = MockWebServer()
        webServer.start(4007)
        apiEndpoint.baseUrl = MOCK_URL
    }

    override fun after() {
        webServer.shutdown()
    }

    private fun enqueueResponse(code: Int, request: String) = webServer.enqueue(
        MockResponse()
            .setResponseCode(code)
            .setBody(request)
    )

    fun <T> enqueueResponse(data: T) = enqueueResponse(200, Gson().toJson(data))

    fun enqueueError(code: Int = 400, error: String = "Error") = enqueueResponse(code, error)

    companion object {
        const val MOCK_URL = "http://127.0.0.1:4007/"
    }
}