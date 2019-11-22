package pe.richard.architecture.data.auth

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import pe.richard.architecture.data.core.auth.AuthData

interface AuthRepository {

    /**
     * Get last signed in account from cache.
     * If an error occurs or not, sign in from remote, and next sync it in cache.
     * Case Google with Firebase api, it will be supported auto sync.
     *
     * @return Cold Single auth data.
     */
    fun observeDynamic(): Flowable<AuthData>

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