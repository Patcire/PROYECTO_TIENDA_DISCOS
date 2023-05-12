import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application


import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

fun main()  = application {
    Window(onCloseRequest = ::exitApplication) {
        App2()
    }
}

@Composable
@Preview
fun App2() {


    MaterialTheme {
        var weight by remember { mutableStateOf("") }
        var height by remember { mutableStateOf("") }
        var bmi by remember { mutableStateOf("") }

        MaterialTheme { //este material no es necesario
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("Calculadora de IMC", modifier = Modifier.padding(bottom = 16.dp))
                OutlinedTextField(
                    value = weight,
                    onValueChange = { weight = it },
                    label = { Text("Peso en kg") },
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                OutlinedTextField(
                    value = height,
                    onValueChange = { height = it },
                    label = { Text("Altura en cm") },
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Button(onClick = { bmi = calculateBMI(weight.toFloat(), height.toFloat()) }) {
                    Text("Calcular")
                }
                if (bmi.isNotBlank()) {
                    Text(
                        "Tu IMC es de $bmi",
                        modifier = Modifier.padding(top = 16.dp)
                    )
                }
            }
        }
    }


}



fun calculateBMI(weight: Float, height: Float): String {
    val heightInMeters = height / 100
    val bmi = weight / (heightInMeters * heightInMeters)
    return "%.2f".format(bmi)
}