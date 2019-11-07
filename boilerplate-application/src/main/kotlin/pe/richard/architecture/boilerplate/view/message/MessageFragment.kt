package pe.richard.architecture.boilerplate.view.message

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import pe.richard.architecture.boilerplate.R
import pe.richard.architecture.core.view.navigation.NavFragment

class MessageFragment : NavFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_message, container, false)
            ?.also { view -> initRootViewPadding(view) }

}