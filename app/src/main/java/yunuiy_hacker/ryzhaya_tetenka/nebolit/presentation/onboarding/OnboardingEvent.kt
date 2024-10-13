package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.onboarding

sealed class OnboardingEvent {
    data object SaveAppEntry : OnboardingEvent()
}