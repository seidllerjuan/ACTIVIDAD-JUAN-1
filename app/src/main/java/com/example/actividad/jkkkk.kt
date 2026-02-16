import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
@Preview(showBackground = true)

@Composable
fun Game2048Simple() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFAF8EF))
            .padding(16.dp)
    ) {
        // Fila superior: título y puntuaciones
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "2048",
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF776E65)
            )

            Column {
                ScoreBox("SCORE", "1692")
                ScoreBox("BEST", "7000")
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Tablero 4x4
        Board()
    }
}

@Composable
fun ScoreBox(label: String, value: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.background(Color(0xFFBBADA0))
            .padding(horizontal = 10.dp, vertical = 5.dp)
    ) {
        Text(label, color = Color.White, fontSize = 12.sp)
        Text(value, color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun Board() {
    val celdas = listOf(
        listOf("4", "32", "8", "32"),
        listOf("16", "64", "4", ""),
        listOf("2", "128", "64", ""),
        listOf("8", "", "", "")
    )

    Column(
        modifier = Modifier.background(Color(0xFFBBADA0))
            .padding(4.dp)
    ) {
        celdas.forEach { fila ->
            Row {
                fila.forEach { numero ->
                    Celda(numero)
                }
            }
        }
    }
}

@Composable
fun Celda(numero: String) {
    val colorFondo = when (numero) {
        "2" -> Color(0xFFEEE4DA)
        "4" -> Color(0xFFEDE0C8)
        "8" -> Color(0xFFF2B179)
        "16" -> Color(0xFFF59563)
        "32" -> Color(0xFFF67C5F)
        "64" -> Color(0xFFF65E3B)
        "128" -> Color(0xFFEDCF72)
        else -> Color(0xFFCDC1B4)
    }

    Box(
        modifier = Modifier
            .size(70.dp)
            .background(colorFondo)
            .padding(4.dp),
        contentAlignment = Alignment.Center
    ) {
        if (numero.isNotEmpty()) {
            Text(
                text = numero,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = if (numero in listOf("2", "4")) Color(0xFF776E65) else Color.White
            )
        }
    }
}