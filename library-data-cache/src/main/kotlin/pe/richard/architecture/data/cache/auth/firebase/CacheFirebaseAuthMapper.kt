package pe.richard.architecture.data.cache.auth.firebase

import pe.richard.architecture.core.data.From
import pe.richard.architecture.data.core.auth.firebase.FirebaseAuthMapper

internal object CacheFirebaseAuthMapper {

    internal class SingleDataTransformer(from: From) :
        FirebaseAuthMapper.SingleDataTransformer(from)

}