package yunuiy_hacker.ryzhaya_tetenka.nebolit.presentation.main.disease_history.composable

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.common.model.DiseaseHistory
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.toDateOfMobileFormat
import yunuiy_hacker.ryzhaya_tetenka.nebolit.data.toPassportDate
import java.text.SimpleDateFormat

@SuppressLint("SimpleDateFormat")
@Composable
fun DiseaseHistoryComposable(modifier: Modifier = Modifier, diseaseHistory: DiseaseHistory) {
    ElevatedCard(
        modifier = modifier,
        colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(text = buildAnnotatedString {
                    append("Диагноз: ")
                    withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                        append(diseaseHistory.diagnosis)
                    }
                }, color = MaterialTheme.colorScheme.onSurface)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(text = buildAnnotatedString {
                    append("Врач: ")
                    withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                        append(diseaseHistory.doctor?.user?.passport?.let { passport ->
                            "${passport.surname} ${passport.name} ${passport.lastname}"
                        })
                    }
                }, color = MaterialTheme.colorScheme.onSurface)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(text = buildAnnotatedString {
                    append("Специализация: ")
                    withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                        append(diseaseHistory.doctor?.specialization?.title)
                    }
                }, color = MaterialTheme.colorScheme.onSurface)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(text = buildAnnotatedString {
                    append("Прием: ")
                    withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                        append(
                            diseaseHistory.visitPurpose?.title
                        )
                    }
                }, color = MaterialTheme.colorScheme.onSurface)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(text = buildAnnotatedString {
                    append("Вид оплаты: ")
                    withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                        append(
                            diseaseHistory.typeOfPayment?.title
                        )
                    }
                }, color = MaterialTheme.colorScheme.onSurface)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(text = buildAnnotatedString {
                    append("Дата приема: ")
                    withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                        append(
                            SimpleDateFormat("dd.MM.yyyy").format(
                                diseaseHistory.date.toPassportDate().toDateOfMobileFormat()
                            )
                        )
                    }
                }, color = MaterialTheme.colorScheme.onSurface)
            }
            if (diseaseHistory.visitPurposeId != 1) {
                Spacer(modifier = Modifier.height(16.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(text = buildAnnotatedString {
                        append("Результат лечения: ")
                        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                            append(
                                diseaseHistory.treatmentResult?.title
                            )
                        }
                    }, color = MaterialTheme.colorScheme.onSurface)
                }
            }
        }
    }
}