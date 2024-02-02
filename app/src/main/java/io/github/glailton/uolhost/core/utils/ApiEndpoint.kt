package io.github.glailton.uolhost.core.utils

class ApiEndpoint {
    var baseUrl: String = "http://10.0.2.2:8080/players/"
        set(url) {
            field = url
            if (!url.endsWith("/")) {
                field += "/"
            }
        }
}