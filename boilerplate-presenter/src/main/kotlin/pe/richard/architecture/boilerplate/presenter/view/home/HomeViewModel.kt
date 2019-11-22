package pe.richard.architecture.boilerplate.presenter.view.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import pe.richard.architecture.boilerplate.presenter.model.auth.Auth
import pe.richard.architecture.boilerplate.presenter.model.auth.AuthMapper
import pe.richard.architecture.boilerplate.presenter.model.user.User
import pe.richard.architecture.boilerplate.presenter.model.user.UserMapper
import pe.richard.architecture.core.lifecycle.extensions.changed
import pe.richard.architecture.domain.auth.AuthDeleter
import pe.richard.architecture.domain.auth.AuthGetter
import pe.richard.architecture.domain.auth.AuthObserver
import pe.richard.architecture.domain.user.MyCreator
import pe.richard.architecture.domain.user.MyDeleter
import pe.richard.architecture.domain.user.MyGetter
import pe.richard.architecture.domain.user.MyUpdater
import pe.richard.architecture.domain.user.UserCreator
import pe.richard.architecture.domain.user.UserDeleter
import pe.richard.architecture.domain.user.UserGetter
import pe.richard.architecture.domain.user.UserObserver
import pe.richard.architecture.domain.user.UserUpdater

internal class HomeViewModel(
    application: Application,
    private val authObserver: AuthObserver,
    private val authGetter: AuthGetter,
    private val authDeleter: AuthDeleter,
    private val userObserver: UserObserver,
    private val userGetter: UserGetter,
    private val userCreator: UserCreator,
    private val userUpdater: UserUpdater,
    private val userDeleter: UserDeleter,
    private val myGetter: MyGetter,
    private val myCreator: MyCreator,
    private val myUpdater: MyUpdater,
    private val myDeleter: MyDeleter
) : HomePresenter(application) {

    //region ViewModelProvider.Factory

    class Factory(
        private val application: Application,
        private val authObserver: AuthObserver,
        private val authGetter: AuthGetter,
        private val authDeleter: AuthDeleter,
        private val userObserver: UserObserver,
        private val userGetter: UserGetter,
        private val userCreator: UserCreator,
        private val userUpdater: UserUpdater,
        private val userDeleter: UserDeleter,
        private val myGetter: MyGetter,
        private val myCreator: MyCreator,
        private val myUpdater: MyUpdater,
        private val myDeleter: MyDeleter
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(model: Class<T>): T =
            when (model.isAssignableFrom(HomePresenter::class.java)) {
                true -> HomeViewModel(
                    application,
                    authObserver,
                    authGetter,
                    authDeleter,
                    userObserver,
                    userGetter,
                    userCreator,
                    userUpdater,
                    userDeleter,
                    myGetter,
                    myCreator,
                    myUpdater,
                    myDeleter
                ) as T
                false -> throw IllegalArgumentException("$model was not founded.")
            }

    }

    //endregion

    //region Auth

    private val authLiveData = MutableLiveData<Auth?>()

    override val auth: LiveData<Auth?> = authLiveData

    override fun observeDynamicAuth() {
        Log.e("HomeViewModel", "observeDynamicAuth")
        addDisposable(
            authObserver.observeDynamic()
                .subscribeOn(Schedulers.io())
                .compose(AuthMapper.DataFlowableTransformer())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        Log.e("HomeViewModel", "observeDynamicAuth.subscribe: $it")
                        authLiveData.changed = it
                        errorLiveData.changed = null
                    },
                    {
                        Log.e("HomeViewModel", "observeDynamicAuth.subscribeError: $it", it)
                        authLiveData.changed = null
                        errorLiveData.changed = it
                    })
        )
    }

    override fun getSyncAuth() {
        Log.e("HomeViewModel", "getSyncAuth")
        addDisposable(
            authGetter.getSync()
                .subscribeOn(Schedulers.io())
                .compose(AuthMapper.DataSingleTransformer())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        Log.e("HomeViewModel", "getSyncAuth.subscribe: $it")
                        authLiveData.changed = it
                        errorLiveData.changed = null
                    },
                    {
                        Log.e("HomeViewModel", "getSyncAuth.subscribeError: $it", it)
                        authLiveData.changed = null
                        errorLiveData.changed = it
                    })
        )
    }

    override fun getStaticAuth() {
        Log.e("HomeViewModel", "getStaticAuth")
        addDisposable(
            authGetter.getStatic()
                .subscribeOn(Schedulers.io())
                .compose(AuthMapper.DataSingleTransformer())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        Log.e("HomeViewModel", "getStaticAuth.subscribe: $it")
                        authLiveData.changed = it
                        errorLiveData.changed = null
                    },
                    {
                        Log.e("HomeViewModel", "getStaticAuth.subscribeError: $it", it)
                        authLiveData.changed = null
                        errorLiveData.changed = it
                    })
        )
    }

    override fun deleteCacheAuth() {
        Log.e("HomeViewModel", "deleteCacheAuth")
        addDisposable(
            authDeleter.deleteCache()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        Log.e("HomeViewModel", "deleteCacheAuth.subscribe")
                        authLiveData.changed = null
                        errorLiveData.changed = null
                    },
                    {
                        Log.e("HomeViewModel", "deleteCacheAuth.subscribeError: $it", it)
                        authLiveData.changed = null
                        errorLiveData.changed = it
                    })
        )
    }

    override fun deleteAuth() {
        Log.e("HomeViewModel", "deleteAuth")
        addDisposable(
            authDeleter.delete()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        Log.e("HomeViewModel", "deleteAuth.subscribe")
                        authLiveData.changed = null
                        errorLiveData.changed = null
                    },
                    {
                        Log.e("HomeViewModel", "deleteAuth.subscribeError: $it", it)
                        authLiveData.changed = null
                        errorLiveData.changed = it
                    })
        )
    }

    //endregion

    //region User

    private val userLiveData = MutableLiveData<User?>()

    override val user: LiveData<User?> = userLiveData

    override fun observeUser(id: String) {
        Log.e("HomeViewModel", "observeUser")
        addDisposable(
            userObserver.observe(id)
                .subscribeOn(Schedulers.io())
                .compose(UserMapper.DataFlowableTransformer())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        Log.e("HomeViewModel", "observeUser.subscribe: $it")
                        userLiveData.changed = it
                        errorLiveData.changed = null
                    },
                    {
                        Log.e("HomeViewModel", "observeUser.subscribeError: $it", it)
                        userLiveData.changed = null
                        errorLiveData.changed = it
                    })
        )
    }

    override fun getUser(id: String) {
        Log.e("HomeViewModel", "getUser: $id")
        addDisposable(
            userGetter.get(id)
                .subscribeOn(Schedulers.io())
                .compose(UserMapper.DataSingleTransformer())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        Log.e("HomeViewModel", "getUser.subscribe")
                        userLiveData.changed = it
                        errorLiveData.changed = null
                    },
                    {
                        Log.e("HomeViewModel", "getUser.subscribeError: $it", it)
                        userLiveData.changed = null
                        errorLiveData.changed = it
                    })
        )
    }

    override fun getUserByEmail(email: String) {
        Log.e("HomeViewModel", "getUserByEmail: $email")
        addDisposable(
            userGetter.getByEmail(email)
                .subscribeOn(Schedulers.io())
                .compose(UserMapper.DataSingleTransformer())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        Log.e("HomeViewModel", "getUserByEmail.subscribe")
                        userLiveData.changed = it
                        errorLiveData.changed = null
                    },
                    {
                        Log.e("HomeViewModel", "getUserByEmail.subscribeError: $it", it)
                        userLiveData.changed = null
                        errorLiveData.changed = it
                    })
        )
    }

    override fun createUser(email: String) {
        Log.e("HomeViewModel", "createUser: $email")
        addDisposable(
            userCreator.create(email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        Log.e("HomeViewModel", "createUser.subscribe")
                        userLiveData.changed = null
                        errorLiveData.changed = null
                    },
                    {
                        Log.e("HomeViewModel", "createUser.subscribeError: $it", it)
                        userLiveData.changed = null
                        errorLiveData.changed = it
                    })
        )
    }

    override fun updateUser(user: User) {
        Log.e("HomeViewModel", "updateUser: $user")
        addDisposable(
            Single.just(user)
                .subscribeOn(Schedulers.io())
                .compose(UserMapper.DomainSingleTransformer())
                .flatMapCompletable { userUpdater.update(it) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        Log.e("HomeViewModel", "updateUser.subscribe")
                        userLiveData.changed = null
                        errorLiveData.changed = null
                        getUser(user.id)
                    },
                    {
                        Log.e("HomeViewModel", "updateUser.subscribeError: $it", it)
                        userLiveData.changed = null
                        errorLiveData.changed = it
                    })
        )
    }

    override fun deleteUser(id: String) {
        Log.e("HomeViewModel", "deleteUser: $user")
        addDisposable(
            userDeleter.delete(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        Log.e("HomeViewModel", "deleteUser.subscribe")
                        userLiveData.changed = null
                        errorLiveData.changed = null
                    },
                    {
                        Log.e("HomeViewModel", "deleteUser.subscribeError: $it", it)
                        userLiveData.changed = null
                        errorLiveData.changed = it
                    })
        )
    }

    //endregion

    //region My

    private val myLiveData = MutableLiveData<User?>()

    override val my: LiveData<User?> = myLiveData

    override fun getMy() {
        Log.e("HomeViewModel", "getMy")
        addDisposable(
            myGetter.get()
                .subscribeOn(Schedulers.io())
                .compose(UserMapper.DataSingleTransformer())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        Log.e("HomeViewModel", "getMy.subscribe")
                        myLiveData.changed = it
                        errorLiveData.changed = null
                    },
                    {
                        Log.e("HomeViewModel", "getMy.subscribeError: $it", it)
                        myLiveData.changed = null
                        errorLiveData.changed = it
                    })
        )
    }

    override fun createMy() {
        Log.e("HomeViewModel", "createMy")
        addDisposable(
            myCreator.create()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        Log.e("HomeViewModel", "createMy.subscribe")
                        myLiveData.changed = null
                        errorLiveData.changed = null
                    },
                    {
                        Log.e("HomeViewModel", "createMy.subscribeError: $it", it)
                        myLiveData.changed = null
                        errorLiveData.changed = it
                    })
        )
    }

    override fun updateMy(user: User) {
        Log.e("HomeViewModel", "updateMy: $user")
        addDisposable(
            Single.just(user)
                .subscribeOn(Schedulers.io())
                .compose(UserMapper.DomainSingleTransformer())
                .flatMapCompletable { myUpdater.update(it) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        Log.e("HomeViewModel", "updateMy.subscribe")
                        myLiveData.changed = null
                        errorLiveData.changed = null
                        getUser(user.id)
                    },
                    {
                        Log.e("HomeViewModel", "updateMy.subscribeError: $it", it)
                        myLiveData.changed = null
                        errorLiveData.changed = it
                    })
        )
    }

    override fun deleteMy() {
        Log.e("HomeViewModel", "deleteMy: $user")
        addDisposable(
            myDeleter.delete()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        Log.e("HomeViewModel", "deleteMy.subscribe")
                        myLiveData.changed = null
                        errorLiveData.changed = null
                    },
                    {
                        Log.e("HomeViewModel", "deleteUser.subscribeError: $it", it)
                        myLiveData.changed = null
                        errorLiveData.changed = it
                    })
        )
    }

    //endregion

}