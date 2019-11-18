package pe.richard.architecture.core.view.extensions

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities

@SuppressLint("MissingPermission")
fun Context.isNetworkConnected(): Boolean =
    isNetworkWifi() || isNetworkCellular() || isNetworkEthernet()

@SuppressLint("MissingPermission")
fun Context.getConnectivityManager(): ConnectivityManager? =
    getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager

@SuppressLint("MissingPermission")
fun Context.getActiveNetwork(): Network? =
    getConnectivityManager()?.activeNetwork

@SuppressLint("MissingPermission")
fun Context.getActiveNetworkCapabilities(): NetworkCapabilities? =
    getConnectivityManager()?.getNetworkCapabilities(getActiveNetwork())

@SuppressLint("MissingPermission")
fun Context.isNetworkWifi(): Boolean =
    getActiveNetworkCapabilities()?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ?: false

@SuppressLint("MissingPermission")
fun Context.isNetworkCellular(): Boolean =
    getActiveNetworkCapabilities()?.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ?: false

@SuppressLint("MissingPermission")
fun Context.isNetworkEthernet(): Boolean =
    getActiveNetworkCapabilities()?.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) ?: false

