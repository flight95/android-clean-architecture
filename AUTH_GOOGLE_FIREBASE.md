# Google and Firebase authentication.
> [Google Sign-in](https://developers.google.com/identity/sign-in/android/sign-in) with [Firebase](https://firebase.google.com/docs/auth/android/google-signin) will be used Google API and Firebase API.

## Firebase
> Using [Console](https://console.firebase.google.com)

### [Add Firebase to your Android project](https://firebase.google.com/docs/android/setup)
- Create Firebase project.
- Add Android project.
- Enable Authentication with Google.
- Download google-services.json and move file in android project: [The Google Services Gradle Plugin](https://developers.google.com/android/guides/google-services-plugin)

## Google Cloud Platform
> Using [Console](https://console.cloud.google.com/)

### [Start Integrating Google Sign-In into Your Android App](https://developers.google.com/identity/sign-in/android/start-integrating)
- Go API and Services > User authentication.
- Check OAuth 2.0

## Google Sign-In
> [Integrating Google Sign-In into Your Android App](https://developers.google.com/identity/sign-in/android/sign-in)

```kotlin
GoogleSignIn.getClient(
    activity,
    GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(firebaseClientId)
        .requestEmail()
        .build()
    )
    
@Provides
@ApplicationScope
@Named("firebaseClientId") // It is web client id in google cloud platform that will be used to authentication in google and firebase.
fun provideFirebaseClientId(application: Application): String = application.getString(R.string.default_web_client_id)
```

## Firebase Sign-In
> [Authenticate Using Google Sign-In on Android](https://firebase.google.com/docs/auth/android/google-signin)

```kotlin
FirebaseAuth.getInstance()
    .signInWithCredential(GoogleAuthProvider.getCredential(account.idToken, null))
```

## Sign-in and Sign-out with Cache and Remote
> Apply Logic and Functions naming.

```kotlin
interface AuthRepository {

    /**
     * Get signed in account from remote for http 401, and next sync it in cache.
     * Case Google with Firebase api, it will be supported auto sync.
     *
     * @return Cold Single auth data.
     */
    fun getSync(): Single<AuthData>

    /**
     * Get last signed in account from cache.
     * If an error occurs, sign in from remote, and next sync it in cache.
     * Case Google with Firebase api, it will be supported auto sync.
     *
     * @return Cold Single auth data.
     */
    fun getStatic(): Single<AuthData>

    /**
     * Get last signed in account from cache.
     * If an error occurs or not, sign in from remote, and next sync it in cache.
     * Case Google with Firebase api, it will be supported auto sync.
     *
     * @return Cold Single auth data.
     */
    fun observeDynamic(): Flowable<AuthData>

    /**
     * Sign out account from cache.
     *
     * @return Cold Completable.
     */
    fun deleteCache(): Completable

    /**
     * Revoke access from remote.
     *
     * @return Cold Completable.
     */
    fun delete(): Completable

}
```

## startActivityForResult in Fragment with Navigation
> startActivityForResult will be used for Google Sign-in, and we will be used to Android Navigation Architecture.

Define RequestFragment and RequestCode.
```kotlin
enum class RequestCode {
    None,

    //region TODO: If you want to add another request code that will be used Fragments, you have to add it here.

    GoogleAuth

    //endregion

}

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

when (fragment) {
    is RequestFragment -> {
        fragment.startActivityForResult(client.signInIntent, RequestCode.GoogleAuth) { _, data -> ... }
    }
    else -> emitter.onError(AuthError.ReadGoogle())
}
```

## With Rx
> For Stateless, We will be used to Rx.

```kotlin
fun getGoogleClient(activity: FragmentActivity, firebaseClientId: String): Single<GoogleSignInClient> =
    Single
        .create<GoogleSignInClient> { emitter ->
            try {
                emitter.onSuccess(
                    GoogleSignIn.getClient(
                        activity,
                        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                            .requestIdToken(firebaseClientId)
                            .requestEmail()
                            .build()
                    )
                )
            } catch (e: Throwable) {
                emitter.onError(AuthError.ReadGoogleClient(e))
            }
        }
        .subscribeOn(Schedulers.io())

fun getGoogle(): Single<GoogleSignInAccount> =
    getGoogleClient(activity, firebaseClientId)
        .flatMap<GoogleSignInClient, GoogleSignInAccount>(AndroidSchedulers.mainThread()) { client, emitter ->
            try {
                when (fragment) {
                    is RequestFragment -> {
                        fragment.startActivityForResult(client.signInIntent, RequestCode.GoogleAuth) { _, data ->
                            try {
                                GoogleSignIn
                                    .getSignedInAccountFromIntent(data)
                                    .getResult(ApiException::class.java)
                                    .let { account ->
                                        when (account) {
                                            null -> emitter.onError(AuthError.ReadGoogle())
                                            else -> emitter.onSuccess(account)
                                        }
                                    }
                            } catch (e: ApiException) {
                                emitter.onError(
                                    when (e.statusCode) {
                                        12501 -> AuthError.ReadGoogleCancel(e)
                                        else -> AuthError.ReadGoogle(e)
                                    }
                                )
                            } catch (e: Throwable) {
                                emitter.onError(AuthError.ReadGoogle(e))
                            }
                        }
                    }
                    else -> emitter.onError(AuthError.ReadGoogle())
                }
            } catch (e: Throwable) {
                emitter.onError(AuthError.ReadGoogle(e))
            }
        }
        .observeOn(Schedulers.io())

fun get(): Single<AuthData> =
    getGoogle()
        .flatMap<GoogleSignInAccount, GoogleSignInAccount>(Schedulers.io()) { account, emitter ->
            try {
                FirebaseAuth.getInstance()
                    .signInWithCredential(GoogleAuthProvider.getCredential(account.idToken, null))
                    .addOnCompleteListener(activity) { task ->
                        when (task.isSuccessful) {
                            true -> emitter.onSuccess(account)
                            false -> emitter.onError(AuthError.ReadFirebase())
                        }
                    }
            } catch (e: Throwable) {
                emitter.onError(AuthError.ReadFirebase(e))
            }
        }
        .observeOn(Schedulers.io())
        .flatMap(getFirebase()) { google, firebase -> Pair(firebase, google) }
        .compose(RemoteFirebaseAuthMapper.SingleDataTransformer(From.Remote))
```

For another information, check auth package in each modules.
