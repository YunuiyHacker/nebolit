package yunuiy_hacker.ryzhaya_tetenka.nebolit.data.net

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.realtime.Realtime
import io.github.jan.supabase.serializer.KotlinXSerializer

class SupabaseClient(
    val url: String = "https://hubxllvukplhaqzkivtd.supabase.co",
    val apiKey: String = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Imh1YnhsbHZ1a3BsaGFxemtpdnRkIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MjAxNzIyMzIsImV4cCI6MjAzNTc0ODIzMn0.lLwpAqntaF-vJrAxoU9i4gPetM_jY3rtlTJLrUKlauI"
) {
    lateinit var supabaseClient: SupabaseClient;

    init {
        supabaseClient = createSupabaseClient(url, apiKey) {
            install(Auth)
            install(Postgrest)

            KotlinXSerializer()
        }
    }
}