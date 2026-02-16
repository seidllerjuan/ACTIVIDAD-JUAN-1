package com.example.actividad

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true)
@Composable
fun Juego2048() {
    // ESTADOS GLOBALES (Estilo spagueti: todo junto)
    var pantallaActual by remember { mutableStateOf("JUEGO") } // JUEGO, PANTALLA1, PANTALLA2, GAMEOVER
    var nombreUsuario by remember { mutableStateOf("") }

    // El tablero: 16 espacios (4x4)
    val tablero = remember { mutableStateListOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0) }

    // Cálculos directos
    val score = tablero.sum()
    val best = tablero.maxOrNull() ?: 0

    // Lógica de colores (Un color único para cada potencia de 2)
    fun obtenerColorReferencia(num: Int): Color {
        return when (num) {
            2 -> Color(0xFFEEE4DA)
            4 -> Color(0xFFEDE0C8)
            8 -> Color(0xFFF2B179)
            16 -> Color(0xFFF59563)
            32 -> Color(0xFFF67C5F)
            64 -> Color(0xFFF65E3B)
            128 -> Color(0xFFEDCF72)
            256 -> Color(0xFFEDCC61)
            512 -> Color(0xFFEDC850)
            1024 -> Color(0xFFEDC53F)
            2048 -> Color(0xFFEDC22E)
            else -> Color(0xFFCDC1B4) // Vacío
        }
    }

    if (pantallaActual == "JUEGO") {
        Column(
            modifier = Modifier.fillMaxSize().background(Color(0xFFFAF8EF)).padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("2048", fontSize = 48.sp, fontWeight = FontWeight.Bold, color = Color(0xFF776E65))

            // Fila de Score y Best
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("SCORE", fontWeight = FontWeight.Bold)
                    Text("$score", fontSize = 20.sp)
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("BEST", fontWeight = FontWeight.Bold)
                    Text("$best", fontSize = 20.sp)
                }
            }

            Spacer(Modifier.height(20.dp))

            // Botones de Iconos (Simulados con texto/botones)
            Row {
                Button(onClick = { pantallaActual = "PANTALLA1" }) { Text("Pantalla 1") }
                Spacer(Modifier.width(10.dp))
                Button(onClick = { pantallaActual = "PANTALLA2" }) { Text("Pantalla 2") }
            }

            Spacer(Modifier.height(20.dp))

            // El Tablero 4x4
            for (fila in 0..3) {
                Row {
                    for (columna in 0..3) {
                        val index = fila * 4 + columna
                        val valor = tablero[index]

                        Box(
                            modifier = Modifier
                                .padding(4.dp)
                                .size(70.dp)
                                .background(obtenerColorReferencia(valor), RoundedCornerShape(8.dp))
                                .clickable {
                                    // Lógica de aumento: nada -> 2 -> 4 ...
                                    if (tablero[index] == 0) tablero[index] = 2
                                    else if (tablero[index] < 2048) tablero[index] *= 2
                                    else {
                                        // Si ya es 2048 y se le da clic, se excede y pierde
                                        pantallaActual = "GAMEOVER"
                                    }
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = if (valor == 0) "" else "$valor",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (valor <= 4) Color(0xFF776E65) else Color.White
                            )
                        }
                    }
                }
            }

            Spacer(Modifier.weight(1f))

            TextField(
                value = nombreUsuario,
                onValueChange = { nombreUsuario = it },
                label = { Text("Ingresa tu nombre") },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }

    else if (pantallaActual == "GAMEOVER") {
        Column(
            modifier = Modifier.fillMaxSize().background(Color(0xFFFAF8EF)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Game Over", fontSize = 40.sp, fontWeight = FontWeight.Bold, color = Color(0xFF776E65))
            Text("$nombreUsuario sigue participando", fontSize = 20.sp)
            Spacer(Modifier.height(20.dp))
            Button(onClick = {
                // Reiniciar todo
                for (i in 0..15) tablero[i] = 0
                pantallaActual = "JUEGO"
            }) {
                Text("Play Again")
            }
        }
    }

    else if (pantallaActual == "PANTALLA1") {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Pantalla Actividad 01", fontSize = 24.sp)
                Button(onClick = { pantallaActual = "JUEGO" }) { Text("Volver") }
            }
        }
    }

    else if (pantallaActual == "PANTALLA2") {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Segunda Pantalla Previa", fontSize = 24.sp)
                Button(onClick = { pantallaActual = "JUEGO" }) { Text("Volver") }
            }
        }
    }
}