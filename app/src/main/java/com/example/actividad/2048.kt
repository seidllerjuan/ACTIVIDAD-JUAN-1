package com.example.actividad

import android.R

import kotlin.random.Random

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
fun Juego() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFAF8EF))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "2048",
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF776E65)
        )

        Spacer(Modifier.size(24.dp))

        Row {
            Caja()
            Spacer(Modifier.size(12.dp))
            Caja()
            Spacer(Modifier.size(12.dp))
            Caja()
            Spacer(Modifier.size(12.dp))
            Caja()
        }

        Spacer(Modifier.size(12.dp))

        Row {
            Caja()
            Spacer(Modifier.size(12.dp))
            Caja()
            Spacer(Modifier.size(12.dp))
            Caja()
            Spacer(Modifier.size(12.dp))
            Caja()
        }

        Spacer(Modifier.size(12.dp))

        Row {
            Caja()
            Spacer(Modifier.size(12.dp))
            Caja()
            Spacer(Modifier.size(12.dp))
            Caja()
            Spacer(Modifier.size(12.dp))
            Caja()
        }

        Spacer(Modifier.size(12.dp))

        Row {
            Caja()
            Spacer(Modifier.size(12.dp))
            Caja()
            Spacer(Modifier.size(12.dp))
            Caja()
            Spacer(Modifier.size(12.dp))
            Caja()
        }
    }
}

@Composable
fun Caja() {

    val numero = (1..1000).random()
    val numeroRandom = numero.toString()

    var colorBlock = Color.Black

    when (numero) {
        in 100..200 -> colorBlock =  Color(0xFF8A3030)
        in 201..500 -> colorBlock =  Color(0xFF324B8F)
        in 501..700 -> colorBlock =  Color(0xFFA6BB3A)
        in 701..850 -> colorBlock = Color(0xFF189F1B)


        else -> colorBlock = Color.DarkGray
    }

    Box(
        modifier = Modifier
            .size(80.dp)
            .background(color = colorBlock, RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            numeroRandom,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}


