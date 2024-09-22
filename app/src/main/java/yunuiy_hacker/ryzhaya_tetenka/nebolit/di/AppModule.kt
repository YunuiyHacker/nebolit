package yunuiy_hacker.ryzhaya_tetenka.nebolit.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.serializer.KotlinXSerializer
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.use_case.SignInUseCase
import yunuiy_hacker.ryzhaya_tetenka.nebolit.domain.auth.use_case.SignUpUseCase
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
            supabaseUrl = URL,
            supabaseKey = API_KEY
        ) {
            install(Auth)
            install(Postgrest)

            KotlinXSerializer()
        }
    }

    @Singleton
    @Provides
    fun provideSignInUseCase(supabaseClient: SupabaseClient): SignInUseCase =
        SignInUseCase(supabaseClient)

    @Singleton
    @Provides
    fun provideSignUpUseCase(supabaseClient: SupabaseClient): SignUpUseCase =
        SignUpUseCase(supabaseClient)
}