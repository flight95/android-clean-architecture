package pe.richard.architecture.core.view.request

import android.content.Intent
import androidx.fragment.app.Fragment

open class RequestFragment : Fragment() {

    private val listeners = mutableMapOf<RequestCode, ((resultCode: Int, data: Intent?) -> Unit)>()

    fun setOnActivityResultListener(request: RequestCode, listener: (resultCode: Int, data: Intent?) -> Unit) =
        listeners.set(request, listener)

    fun startActivityForResult(intent: Intent?, request: RequestCode, listener: (resultCode: Int, data: Intent?) -> Unit) {
        setOnActivityResultListener(request, listener)
        super.startActivityForResult(intent, request.ordinal)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        requestCode.convertRequestCode().let { request ->
            listeners[request]?.let { listener ->
                listener(resultCode, data)
                listeners.remove(request)
            }
        }
    }

    private fun Int.convertRequestCode(): RequestCode =
        try {
            RequestCode.values()[this]
        } catch (e: Throwable) {
            RequestCode.None
        }

}