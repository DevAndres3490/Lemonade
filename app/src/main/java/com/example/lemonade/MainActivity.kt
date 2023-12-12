package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LemonadeImageButton()

                }

            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun LemonadeImageButton(modifier: Modifier = Modifier) {


        var step by remember { mutableStateOf(1) }

        val imageResource = when (step) {
            1 -> R.drawable.lemon_tree
            2 -> R.drawable.lemon_squeeze
            3 -> R.drawable.lemon_drink
            else -> R.drawable.lemon_restart
        }


        val textResource = when (step) {
            1 -> R.string.lemon_tree
            2 -> R.string.lemon_squeze
            3 -> R.string.lemon_drink
            else -> R.string.lemon_restart
        }

        val imageDescription = when(step) {

            1 -> stringResource(R.string.lemonTree)
            2 -> stringResource(R.string.lemon)
            3 -> stringResource(R.string.glassOfLemonade)
            else -> stringResource(R.string.emptyGlass)
        }
        
        var pulsaciones by remember { mutableStateOf(0) }
        var numeroRandom = (2..4).random()


        Column(
            modifier = modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top

        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Yellow)
                    .padding(16.dp)
            ) {
                Text(
                    text = "Lemonade",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                    fontSize = 40.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(Alignment.Center)

                )
            }
            Spacer(modifier = Modifier.height(200.dp))
            Image(

                painter = painterResource(id = imageResource),
                contentDescription = imageDescription.toString(),
                alpha = 10f,
                modifier = Modifier
                    .size(250.dp)
                    .border(
                        width = 5.dp,
                        color = Color(
                            red = 153,
                            green = 204,
                            blue = 209,
                            alpha = 1
                        ), // Color del borde RGB: 105, 205, 216
                        shape = RoundedCornerShape(CornerSize(20.dp))

                    )
                    .background(
                        Color(red = 151, green = 213, blue = 184, alpha = 255),
                        shape = RoundedCornerShape(CornerSize(20.dp))

                    )
                    .clickable {
                        if (step == 2) {
                            pulsaciones += 1
                            if (pulsaciones == numeroRandom) {
                                // Si la imagen 2 se ha repetido según el valor de numeroRandom, avanzar a la siguiente imagen
                                step += 1


                                // Reiniciar el contador para la próxima vez que se alcance la imagen 2
                                pulsaciones = 0
                            }
                        } else if (step == 4) {
                            step = 1

                        } else {
                            step += 1

                        }
                    }


            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = stringResource(id = textResource),
                fontSize = 18.sp

                )
        }
    }



    @Preview(showBackground = true)
    @Composable
    fun LemonadePreview() {
        LemonadeTheme {
            LemonadeImageButton(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            )
        }
    }
}