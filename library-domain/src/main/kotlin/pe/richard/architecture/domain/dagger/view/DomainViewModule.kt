package pe.richard.architecture.domain.dagger.view

import dagger.Binds
import dagger.Module
import pe.richard.architecture.core.dagger.scope.ViewScope
import pe.richard.architecture.domain.auth.AuthDeleter
import pe.richard.architecture.domain.auth.AuthGetter
import pe.richard.architecture.domain.auth.AuthObserver
import pe.richard.architecture.domain.auth.firebase.FirebaseAuthDeleter
import pe.richard.architecture.domain.auth.firebase.FirebaseAuthGetter
import pe.richard.architecture.domain.auth.firebase.FirebaseAuthObserver
import pe.richard.architecture.domain.dagger.view.test.DomainViewTest
import pe.richard.architecture.domain.dagger.view.test.IDomainViewTest
import pe.richard.architecture.domain.user.MyCreator
import pe.richard.architecture.domain.user.MyDeleter
import pe.richard.architecture.domain.user.MyGetter
import pe.richard.architecture.domain.user.MyUpdater
import pe.richard.architecture.domain.user.UserCreator
import pe.richard.architecture.domain.user.UserDeleter
import pe.richard.architecture.domain.user.UserGetter
import pe.richard.architecture.domain.user.UserObserver
import pe.richard.architecture.domain.user.UserUpdater
import pe.richard.architecture.domain.user.firestore.FirestoreMyCreator
import pe.richard.architecture.domain.user.firestore.FirestoreMyDeleter
import pe.richard.architecture.domain.user.firestore.FirestoreMyGetter
import pe.richard.architecture.domain.user.firestore.FirestoreMyUpdater
import pe.richard.architecture.domain.user.firestore.FirestoreUserCreator
import pe.richard.architecture.domain.user.firestore.FirestoreUserDeleter
import pe.richard.architecture.domain.user.firestore.FirestoreUserGetter
import pe.richard.architecture.domain.user.firestore.FirestoreUserObserver
import pe.richard.architecture.domain.user.firestore.FirestoreUserUpdater
import javax.inject.Named

internal object DomainViewModule {

    @Module
    class Provider

    @Module
    abstract class Binder {

        @Binds
        @ViewScope
        @Named("FirebaseAuthObserver")
        abstract fun bindFirebaseAuthObserver(inject: FirebaseAuthObserver): AuthObserver

        @Binds
        @ViewScope
        @Named("FirebaseAuthGetter")
        abstract fun bindFirebaseAuthGetter(inject: FirebaseAuthGetter): AuthGetter

        @Binds
        @ViewScope
        @Named("FirebaseAuthDeleter")
        abstract fun bindFirebaseAuthDeleter(inject: FirebaseAuthDeleter): AuthDeleter

        @Binds
        @ViewScope
        @Named("FirestoreUserObserver")
        abstract fun bindFirestoreUserObserver(inject: FirestoreUserObserver): UserObserver

        @Binds
        @ViewScope
        @Named("FirestoreUserGetter")
        abstract fun bindFirestoreUserGetter(inject: FirestoreUserGetter): UserGetter

        @Binds
        @ViewScope
        @Named("FirestoreUserCreator")
        abstract fun bindFirestoreUserCreator(inject: FirestoreUserCreator): UserCreator

        @Binds
        @ViewScope
        @Named("FirestoreUserUpdater")
        abstract fun bindFirestoreUserUpdater(inject: FirestoreUserUpdater): UserUpdater

        @Binds
        @ViewScope
        @Named("FirestoreUserDeleter")
        abstract fun bindFirestoreUserDeleter(inject: FirestoreUserDeleter): UserDeleter

        @Binds
        @ViewScope
        @Named("FirestoreMyGetter")
        abstract fun bindFirestoreMyGetter(inject: FirestoreMyGetter): MyGetter

        @Binds
        @ViewScope
        @Named("FirestoreMyCreator")
        abstract fun bindFirestoreMyCreator(inject: FirestoreMyCreator): MyCreator

        @Binds
        @ViewScope
        @Named("FirestoreMyUpdater")
        abstract fun bindFirestoreMyUpdater(inject: FirestoreMyUpdater): MyUpdater

        @Binds
        @ViewScope
        @Named("FirestoreMyDeleter")
        abstract fun bindFirestoreMyDeleter(inject: FirestoreMyDeleter): MyDeleter

        @Binds
        @ViewScope
        abstract fun bindDomainViewTest(inject: DomainViewTest): IDomainViewTest

    }

}