package pe.richard.architecture.data.remote.auth.firebase

import pe.richard.architecture.core.data.From
import pe.richard.architecture.data.core.auth.firebase.FirebaseAuthMapper

internal object RemoteFirebaseAuthMapper {

    internal class DataSingleTransformer(from: From) :
        FirebaseAuthMapper.DataSingleTransformer(from)

}