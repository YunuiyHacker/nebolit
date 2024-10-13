package yunuiy_hacker.ryzhaya_tetenka.nebolit.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.serializer.KotlinXSerializer
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.local.data_store.DataStoreHelper
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.local.shared_prefs.SharedPrefsHelper
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.use_case.CheckRegistrationByEmail
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.use_case.DefineRole
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.use_case.GetPassportById
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.use_case.LoadRoleObject
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.use_case.ReadPatient
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.use_case.ReadUser
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.use_case.RegistrationPatientUseCase
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.use_case.RegistrationUser
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.use_case.SavePatient
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.use_case.SaveReadPersonDataUseCase
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.use_case.SaveUser
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.use_case.LogIn
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.use_case.ReadDoctor
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.use_case.ReadPassport
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.use_case.RecordPassportUseCase
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.use_case.SaveDoctor
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.use_case.SavePassport
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.use_case.SignInUseCase
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.use_case.SignUpUseCase
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.use_case.UpdateUserPassportIdUseCase
import yunuiy_hacker.ryzhaya_tetenka.nebolit.utils.Constants.API_KEY
import yunuiy_hacker.ryzhaya_tetenka.nebolit.utils.Constants.URL
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideSupabaseClient(): SupabaseClient {
        return createSupabaseClient(
            supabaseUrl = URL, supabaseKey = API_KEY
        ) {
            install(Auth)
            install(Postgrest)

            KotlinXSerializer()
        }
    }

    @Singleton
    @Provides
    fun provideSignInUseCase(supabaseClient: SupabaseClient): SignInUseCase =
        SignInUseCase(
            logIn = LogIn(supabaseClient),
            defineRole = DefineRole(supabaseClient),
            loadRoleObject = LoadRoleObject(supabaseClient),
            getPassportById = GetPassportById(supabaseClient)
        )

    @Singleton
    @Provides
    fun provideSignUpUseCase(supabaseClient: SupabaseClient): SignUpUseCase = SignUpUseCase(
        checkRegistrationByEmail = CheckRegistrationByEmail(supabaseClient),
        registrationUser = RegistrationUser(supabaseClient)
    )

    @Singleton
    @Provides
    fun provideRecordPassportUseCase(supabaseClient: SupabaseClient): RecordPassportUseCase =
        RecordPassportUseCase(supabaseClient)

    @Singleton
    @Provides
    fun provideUpdateUserPassportIdUseCase(supabaseClient: SupabaseClient): UpdateUserPassportIdUseCase =
        UpdateUserPassportIdUseCase(supabaseClient)

    @Singleton
    @Provides
    fun provideRegistrationPatientUseCase(supabaseClient: SupabaseClient): RegistrationPatientUseCase =
        RegistrationPatientUseCase(supabaseClient)

    @Singleton
    @Provides
    fun provideSharedPrefsHelper(@ApplicationContext context: Context): SharedPrefsHelper =
        SharedPrefsHelper(context)

    @Singleton
    @Provides
    fun provideDataStoreHelper(@ApplicationContext context: Context): DataStoreHelper =
        DataStoreHelper(context)

    @Singleton
    @Provides
    fun provideSaveReadPersonDataUserUseCase(
        sharedPrefsHelper: SharedPrefsHelper
    ): SaveReadPersonDataUseCase =
        SaveReadPersonDataUseCase(
            saveUser = SaveUser(sharedPrefsHelper),
            readUser = ReadUser(sharedPrefsHelper),
            savePatient = SavePatient(sharedPrefsHelper),
            readPatient = ReadPatient(sharedPrefsHelper),
            saveDoctor = SaveDoctor(sharedPrefsHelper),
            readDoctor = ReadDoctor(sharedPrefsHelper),
            savePassport = SavePassport(sharedPrefsHelper),
            readPassport = ReadPassport(sharedPrefsHelper)
        )
}