package rio.arj.data.network

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import rio.arj.data.ResponseCode.BAD_GATEWAY
import rio.arj.data.ResponseCode.BAD_REQUEST
import rio.arj.data.ResponseCode.FORBIDDEN
import rio.arj.data.ResponseCode.GATEWAY_TIMEOUT
import rio.arj.data.ResponseCode.INTERNAL_SERVER_ERROR
import rio.arj.data.ResponseCode.NOT_FOUND
import rio.arj.data.ResponseCode.REQUEST_TIMEOUT
import rio.arj.data.ResponseCode.RESPONSE_BAD_GATEWAY
import rio.arj.data.ResponseCode.RESPONSE_BAD_REQUEST
import rio.arj.data.ResponseCode.RESPONSE_FORBIDDEN
import rio.arj.data.ResponseCode.RESPONSE_GATEWAY_TIMEOUT
import rio.arj.data.ResponseCode.RESPONSE_INTERNAL_SERVER_ERROR
import rio.arj.data.ResponseCode.RESPONSE_NOT_FOUND
import rio.arj.data.ResponseCode.RESPONSE_REQUEST_TIMEOUT
import rio.arj.data.ResponseCode.RESPONSE_SERVICE_UNAVAILABLE
import rio.arj.data.ResponseCode.RESPONSE_SUCCESS
import rio.arj.data.ResponseCode.RESPONSE_UNAUTHORIZED
import rio.arj.data.ResponseCode.SERVICE_UNAVAILABLE
import rio.arj.data.ResponseCode.SUCCESS
import rio.arj.data.ResponseCode.UNAUTHORIZED
import java.io.IOException

class NetworkInterceptor() : Interceptor {

  @Throws(IOException::class)
  override fun intercept(chain: Interceptor.Chain): Response {
    val request = chain.request()
    val response = chain.proceed(request)

    when {
      response.code() == RESPONSE_SUCCESS -> Log.e(SUCCESS, SUCCESS)
      response.code() == RESPONSE_BAD_REQUEST -> Log.e(BAD_REQUEST, BAD_REQUEST)
      response.code() == RESPONSE_UNAUTHORIZED -> Log.e(UNAUTHORIZED, UNAUTHORIZED)
      response.code() == RESPONSE_FORBIDDEN -> Log.e(FORBIDDEN, FORBIDDEN)
      response.code() == RESPONSE_NOT_FOUND -> Log.e(NOT_FOUND, NOT_FOUND)
      response.code() == RESPONSE_REQUEST_TIMEOUT -> Log.e(REQUEST_TIMEOUT, REQUEST_TIMEOUT)
      response.code() == RESPONSE_INTERNAL_SERVER_ERROR -> Log.e(INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR)
      response.code() == RESPONSE_BAD_GATEWAY -> Log.e(BAD_GATEWAY, BAD_GATEWAY)
      response.code() == RESPONSE_SERVICE_UNAVAILABLE -> Log.e(SERVICE_UNAVAILABLE, SERVICE_UNAVAILABLE)
      response.code() == RESPONSE_GATEWAY_TIMEOUT -> Log.e(GATEWAY_TIMEOUT, GATEWAY_TIMEOUT)
    }

//    if (ConnectivityStatus.isConnected(context)) {
//      request.newBuilder()
//            .header("Cache-Control", "public, max-age=" + 60)
//            .build()
//
//    } else {
//      request.newBuilder()
//            .header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build()
//    }

    return chain.proceed(request)
  }

}