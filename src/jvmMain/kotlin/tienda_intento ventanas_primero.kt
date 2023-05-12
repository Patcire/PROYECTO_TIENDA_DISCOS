/*

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

@Composable
@Preview
fun inicio():String {
    var opcion by remember { mutableStateOf("inicio") }
    BoxWithConstraints(
        modifier = Modifier.background(Color.LightGray).fillMaxWidth().fillMaxHeight()
    ) {
        //var ancho_maximo=maxWidth.value.toInt()
        //var altura_maxima=maxHeight.value.toInt()
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
        {
            Row {
                Button(modifier = Modifier.padding(5.dp), onClick = { opcion = "1" }) {
                    Text("Iniciar sesión")
                }
            } //FIN 1º ROW

            Row {
                Button(modifier = Modifier.padding(5.dp), onClick = { opcion = "2" }) {
                    Text("Registrarse")
                }
            } //fin 2 row

        } //fin columna
    } //fin box

    if (opcion=="1"){
        return "arranca"
    }
    else{
        return "arranca"
    }
}

@Preview
@Composable
fun prueba():Boolean {
    var opcion by remember { mutableStateOf("arranca") }
    BoxWithConstraints(
        modifier = Modifier.background(Color.LightGray).fillMaxWidth().fillMaxHeight()
    ) {
        //var ancho_maximo=maxWidth.value.toInt()
        //var altura_maxima=maxHeight.value.toInt()
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
        {
            Row {
                Button(modifier = Modifier.padding(5.dp), onClick = { opcion = "empezar" }) {
                    Text("Empesar")
                }
            } //FIN 1º ROW

        } //fin columna
    } //fin box

    var x:Boolean=false
    if (opcion=="empezar"){
        x=true
    }
    return x

}

@Preview
@Composable
fun fin():String {
    var opcion by remember { mutableStateOf("fin") }
    BoxWithConstraints(
        modifier = Modifier.background(Color.LightGray).fillMaxWidth().fillMaxHeight()
    ) {
        //var ancho_maximo=maxWidth.value.toInt()
        //var altura_maxima=maxHeight.value.toInt()
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
        {
            Row {
                Button(modifier = Modifier.padding(5.dp), onClick = { opcion = "fin" }) {
                    Text("Empesar otra vez")
                }
            } //FIN 1º ROW

        } //fin columna
    } //fin box

    if (opcion=="fin") {
        return "fin"}

    else{
        return "arranca"
    }

}



@Preview
@Composable
fun programa_tienda(){
    var ventana by remember { mutableStateOf("arranca") }

    when (ventana) {
        "arranca" -> {
           ventana=prueba()
        }
        "inicio" -> {
             ventana=inicio()
        }
        "fin" ->
        {
            ventana=fin()
        }
    }
}



fun main() = application {
    val titulo_ventana by remember { mutableStateOf("Melting Discos") }
    Window(onCloseRequest = ::exitApplication, title = titulo_ventana) {
        //prueba()
        //inicio()
        programa_tienda()
    }
}

 */