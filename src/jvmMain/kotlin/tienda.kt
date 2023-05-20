import CLASES.Disco
import CLASES.Usuario
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application



@Composable
@Preview
fun pagina_inicio():String {
    var opcion by remember { mutableStateOf("inicio") }
    BoxWithConstraints(
        modifier = Modifier.background(Color.LightGray).fillMaxWidth().fillMaxHeight()
    ) {
        Image(
            painter = painterResource("pexels-tima-miroshnichenko-6827191.jpg"),
            contentDescription = "image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
        {
            Row {
                Button(modifier = Modifier.padding(5.dp), onClick = { opcion = "iniciar_sesion" }) {
                    Text("Iniciar sesión")
                }
            } //FIN 1º ROW

            Row {
                Button(modifier = Modifier.padding(5.dp), onClick = { opcion = "registrar" }) {
                    Text("Registrarse")
                }
            } //fin 2 row

        } //fin columna
    } //fin box

    return opcion
}


@Preview
@Composable
fun formulario_registro():String {
    var opcion by remember { mutableStateOf("registrar") }

    var dni by remember { mutableStateOf("") }
    var nombre by remember { mutableStateOf("") }
    var apellidos by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var contrasenia by remember { mutableStateOf("") }

    var error by remember { mutableStateOf("") }
    //con este mutable state haré que aparezca un mensaje de error

    val usuario_creado= Usuario(dni, nombre, apellidos, correo, contrasenia)
    //objeto tipo Usuario con el que vamos a mandar los datos a la BBDD

    //INTERFAZ GRÁFICA de registrar
    BoxWithConstraints(
        modifier = Modifier.background(Color.LightGray).fillMaxWidth().fillMaxHeight()
    ) {
        Image(
            painter = painterResource("pexels-karolina-grabowska-5208345.jpg"),
            contentDescription = "image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
        {
            Text("DNI", modifier = Modifier.padding(bottom = 8.dp))
            OutlinedTextField(
                value = dni,
                onValueChange = { dni = it },
                label = { Text("Introduzca su DNI") },
                modifier = Modifier.padding(bottom = 6.dp)
            )
            Text("Nombre", modifier = Modifier.padding(bottom = 6.dp))
            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Escriba su nombre") },
                modifier = Modifier.padding(bottom = 6.dp)
            )
            Text("Apellidos", modifier = Modifier.padding(bottom = 6.dp))
            OutlinedTextField(
                value = apellidos,
                onValueChange = { apellidos = it },
                label = { Text("Escriba sus apellidos") },
                modifier = Modifier.padding(bottom = 6.dp)
            )
            Text("Correo", modifier = Modifier.padding(bottom = 6.dp))
            OutlinedTextField(
                value = correo,
                onValueChange = { correo = it },
                label = { Text("Escriba su dirección de correo") },
                modifier = Modifier.padding(bottom = 6.dp)
            )
            Text("Contraseña", modifier = Modifier.padding(bottom = 6.dp))
            OutlinedTextField(
                value = contrasenia,
                onValueChange = { contrasenia = it },
                label = { Text("Escriba la contraseña") },
                modifier = Modifier.padding(bottom = 6.dp)
            )
            Text(error, modifier = Modifier.padding(bottom = 6.dp), color= Color.Red)
            Row() {
                Button( //este botón es para volver al menu principal
                    modifier = Modifier.padding(5.dp),
                    onClick = {
                        opcion="inicio"
                    },
                    colors= ButtonDefaults.buttonColors(backgroundColor= androidx.compose.ui.graphics.Color.LightGray,
                        contentColor = androidx.compose.ui.graphics.Color.Black)
                ) {
                    Text("Volver")
                }// fin 1º botón
                Button(
                    modifier = Modifier.padding(5.dp),
                    onClick = {
                        if (registrar_usuario(usuario_creado)==true){
                            opcion = "inicio"
                        }
                        else error="Error, el dni ya está usado por otra cuenta o alguno de los datos está incompleto"
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = androidx.compose.ui.graphics.Color.White,
                        contentColor = androidx.compose.ui.graphics.Color.Black
                    )
                ) {
                    Text("Enviar")
                }
            } //fin del row del botón
        } //fin de la columna

    } //fin box, FIN INTERFAZ GRÁFICA


    return opcion

}

@Composable
@Preview
fun iniciar_sesion(): MutableMap<String, String>{
    var opcion by remember { mutableStateOf("iniciar_sesion") }

    var correo by remember { mutableStateOf("") }
    var contrasenia by remember { mutableStateOf("") }

    val usuario=Usuario(correo, contrasenia) //usuario que le paso a la función

    var id_usuario_actual by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }

    //INTERFAZ GRÁFICA de iniciar_sesion
    BoxWithConstraints(
        modifier = Modifier.background(Color.LightGray).fillMaxWidth().fillMaxHeight()
    ) {
        Image(
            painter = painterResource("pexels-karolina-grabowska-5208345.jpg"),
            contentDescription = "image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
        {
            Text("Correo", modifier = Modifier.padding(bottom = 6.dp))
            OutlinedTextField(
                value = correo,
                onValueChange = { correo = it },
                label = { Text("Escriba su dirección de correo") },
                modifier = Modifier.padding(bottom = 6.dp)
            )
            Text("Contraseña", modifier = Modifier.padding(bottom = 6.dp))
            OutlinedTextField(
                value = contrasenia,
                onValueChange = { contrasenia = it },
                label = { Text("Escriba la contraseña") },
                modifier = Modifier.padding(bottom = 6.dp)
            )
            Text(error, modifier = Modifier.padding(bottom = 6.dp), color= Color.Red)
            Row() {
                Button( //este botón es para volver al menu principal
                    modifier = Modifier.padding(5.dp),
                    onClick = {
                        opcion="inicio"
                    },
                    colors= ButtonDefaults.buttonColors(backgroundColor= androidx.compose.ui.graphics.Color.LightGray,
                        contentColor = androidx.compose.ui.graphics.Color.Black)
                ) {
                    Text("Volver")
                }// fin 1º botón
                Button(
                    modifier = Modifier.padding(5.dp),
                    onClick = {
                        //primero almacenamos el return de la función comprobar_usuario
                        //Este return es una lista, cuya posicion 0 es el booleano true que cambie la ventana en el onclick
                        // y ven la posicion 1 el id del usuario (su dni), que necesitamos almacenar durante la sesion activa
                        //para saber que usuario está en sesión

                        var lista_recibida=comprobar_usuario(usuario)

                        if (lista_recibida[0]=="true"){ //el valor 0 de la lista recibida siempre va a ser el boolean.toString
                            opcion="menu_principal"
                            id_usuario_actual=lista_recibida[1] //el valor 1 de la lista recibida siempre va a ser el dni
                            println(id_usuario_actual) //print solo para comprobación en la terminal
                        }
                        else{
                            error="Correo y contraseña no coinciden o no existen"
                        }

                    }
                ) {
                    Text("Entrar")
                }
            } // fin row del botón
        } //fin columna
    } //fin box, FIN INTERFAZ GRÁFICA

    val mapa_usuario_opcion= mutableMapOf<String, String>(opcion to id_usuario_actual)
    return mapa_usuario_opcion
}


@Composable
@Preview
fun formulario_actualizacion_usuario(usuario_id: String):String{
    var opcion by remember { mutableStateOf("actualizar") }

    val dni_usuario_en_sesion=usuario_id
    var nombre by remember { mutableStateOf("") }
    var apellidos by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var contrasenia by remember { mutableStateOf("") }

    var error by remember { mutableStateOf("") }
    //con este mutable state haré que aparezca un mensaje de error

    val usuario_a_actualizar= Usuario(dni_usuario_en_sesion, nombre, apellidos, correo, contrasenia)
    //objeto tipo Usuario con el que vamos a mandar los datos a la BBDD

    //INTERFAZ GRÁFICA de registrar
    BoxWithConstraints(
        modifier = Modifier.background(Color.LightGray).fillMaxWidth().fillMaxHeight()
    ) {
        Image(
            painter = painterResource("pexels-karolina-grabowska-5208345.jpg"),
            contentDescription = "image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Text("Nombre", modifier = Modifier.padding(bottom = 6.dp))
            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Escriba su nombre") },
                modifier = Modifier.padding(bottom = 6.dp)
            )
            Text("Apellidos", modifier = Modifier.padding(bottom = 6.dp))
            OutlinedTextField(
                value = apellidos,
                onValueChange = { apellidos = it },
                label = { Text("Escriba sus apellidos") },
                modifier = Modifier.padding(bottom = 6.dp)
            )
            Text("Correo", modifier = Modifier.padding(bottom = 6.dp))
            OutlinedTextField(
                value = correo,
                onValueChange = { correo = it },
                label = { Text("Escriba su dirección de correo") },
                modifier = Modifier.padding(bottom = 6.dp)
            )
            Text("Contraseña", modifier = Modifier.padding(bottom = 6.dp))
            OutlinedTextField(
                value = contrasenia,
                onValueChange = { contrasenia = it },
                label = { Text("Escriba la contraseña") },
                modifier = Modifier.padding(bottom = 6.dp)
            )
            Text(error, modifier = Modifier.padding(bottom = 6.dp), color= Color.Red)
            Row() {
                Button( //este botón es para volver al menu principal
                    modifier = Modifier.padding(5.dp),
                    onClick = {
                        opcion="menu_principal"
                    },
                    colors= ButtonDefaults.buttonColors(backgroundColor= androidx.compose.ui.graphics.Color.LightGray,
                        contentColor = androidx.compose.ui.graphics.Color.Black)
                ) {
                    Text("Volver")
                }// fin 1º botón
                Button(
                    modifier = Modifier.padding(5.dp),
                    onClick = {
                        if (actualizar_usuario(usuario_a_actualizar)==true){
                            opcion = "inicio"
                        }
                        else error="Alguno de los datos está incompleto o incumple el formato"
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = androidx.compose.ui.graphics.Color.White,
                        contentColor = androidx.compose.ui.graphics.Color.Black
                    )
                ) {
                    Text("Enviar")
                } //fin botón enviar
            } //fin del row del botón
        } //fin de la columna

    } //fin box, FIN INTERFAZ GRÁFICA

    return opcion
}

@Composable
@Preview
fun pagina_compra(): String{

    var opcion by remember { mutableStateOf("comprar") }

    var banda by remember { mutableStateOf("") }
    var titulo by remember { mutableStateOf("") }

    val disco= Disco(banda, titulo) //usuario que le paso a la función

    var error by remember { mutableStateOf("") }

    //INTERFAZ GRÁFICA pag compra
    BoxWithConstraints(
        modifier = Modifier.background(Color.LightGray).fillMaxWidth().fillMaxHeight()
    ) {
        Image(
            painter = painterResource("pexels-karolina-grabowska-5208345.jpg"),
            contentDescription = "image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
        {
            Text("ID", modifier = Modifier.padding(bottom = 6.dp))
            OutlinedTextField(
                value = banda,
                onValueChange = { banda = it },
                label = { Text("ID del disco") },
                modifier = Modifier.padding(bottom = 6.dp)
            )
            Text("Título", modifier = Modifier.padding(bottom = 6.dp))
            OutlinedTextField(
                value = titulo,
                onValueChange = { titulo = it },
                label = { Text("Título del disco") },
                modifier = Modifier.padding(bottom = 6.dp)
            )
            Text(error, modifier = Modifier.padding(bottom = 6.dp), color= Color.Red)
            Row() {
                Button( //este botón es para volver al menu principal
                    modifier = Modifier.padding(5.dp),
                    onClick = {
                        opcion="menu_principal"
                    },
                    colors= ButtonDefaults.buttonColors(backgroundColor= androidx.compose.ui.graphics.Color.LightGray,
                        contentColor = androidx.compose.ui.graphics.Color.Black)
                ) {
                    Text("Volver")
                }// fin 1º botón
                Button(
                    modifier = Modifier.padding(5.dp),
                    onClick = {

                        if (comprar(disco)==true){
                            opcion="menu_principal"
                        }
                        else{
                            error="Ese artista o disco no lo tenemos"
                        }
                    }
                ) {
                    Text("Comprar")
                } //fin 2º botón
            } // fin row del botón
        } //fin columna
    } //fin box, FIN INTERFAZ GRÁFICA

    return opcion
}

@Composable
@Preview
fun opciones_ver_discos():String{
    var opcion by remember { mutableStateOf("ver_productos") }


    BoxWithConstraints(
        modifier = Modifier.background(Color.LightGray).fillMaxWidth().fillMaxHeight()
    ) {
        Image(
            painter = painterResource("pexels-karolina-grabowska-5208345.jpg"),
            contentDescription = "image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
        {
            Row {
                Button(modifier = Modifier.padding(5.dp), onClick = { opcion = "ver_cds" }) {
                    Text("Ver CDs")
                }
            } //FIN 1º ROW

            Row {
                Button(modifier = Modifier.padding(5.dp), onClick = { opcion = "ver_vinilos" }) {
                    Text("Ver Vinilos")
                }
            } //fin 2 row
            Row {
                Button(modifier = Modifier.padding(5.dp), onClick = { opcion = "ver_cassettes" }) {
                    Text("Ver Cassettes")
                }
            } //fin 2 row
            Button( //este botón es para volver al menu principal
                modifier = Modifier.padding(5.dp),
                onClick = {
                    opcion="menu_principal"
                },
                colors= ButtonDefaults.buttonColors(backgroundColor= androidx.compose.ui.graphics.Color.LightGray,
                    contentColor = androidx.compose.ui.graphics.Color.Black)
            ) {
                Text("Volver")
            }// fin 1º botón

        } //fin columna
    } //fin box

    return opcion
}

@Composable
@Preview
fun ver_cds():String{


    var opcion by remember { mutableStateOf("ver_cds") }

    var posicion_cd by remember { mutableStateOf(0) }


    val lista_cds=mostrar_cds()

    val id = ((lista_cds[posicion_cd]).id)
    val banda = ((lista_cds[posicion_cd]).banda)
    val titulo = ((lista_cds[posicion_cd]).titulo)
    val cod_descarga = ((lista_cds[posicion_cd]).cod_descarga)


    //INTERFAZ GRÁFICA pag compra
    BoxWithConstraints(
        modifier = Modifier.background(Color.LightGray).fillMaxWidth().fillMaxHeight()
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .border(1.dp, Color.Black)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
        
        {
            Text("ID: $id", modifier = Modifier.padding(bottom = 6.dp))
            Text("Banda: $banda", modifier = Modifier.padding(bottom = 6.dp))
            Text("Título: $titulo", modifier = Modifier.padding(bottom = 6.dp))
            Text("Código de descarga:: $cod_descarga", modifier = Modifier.padding(bottom = 6.dp))
        }
        Row() {
            Button( //este botón es para volver al menu principal
                modifier = Modifier.padding(5.dp),
                onClick = {
                    opcion="ver_productos"
                },
                colors= ButtonDefaults.buttonColors(backgroundColor= androidx.compose.ui.graphics.Color.White,
                    contentColor = androidx.compose.ui.graphics.Color.Black)
            ) {
                Text("Volver")
            }// fin 1º botón
            Button(
                modifier = Modifier.padding(5.dp),
                onClick = {
                    if (posicion_cd < lista_cds.size-1) {
                        posicion_cd += 1
                    } else {
                        posicion_cd = 0
                    }
                }
            ) {
                Text("Siguiente")
            }
        }
    }

    return opcion

}

@Composable
@Preview
fun ver_vinilos():String{
    var opcion by remember { mutableStateOf("ver_vinilos") }

    var posicion_vinilo by remember { mutableStateOf(0) }


    val lista_vinilos=mostrar_vinilos()

    val id = ((lista_vinilos[posicion_vinilo]).id)
    val banda = ((lista_vinilos[posicion_vinilo]).banda)
    val titulo = ((lista_vinilos[posicion_vinilo]).titulo)
    val pulgadas = ((lista_vinilos[posicion_vinilo]).pulgadas)


    //INTERFAZ GRÁFICA pag compra
    BoxWithConstraints(
        modifier = Modifier.background(Color.LightGray).fillMaxWidth().fillMaxHeight()
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .border(1.dp, Color.Black)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )

        {
            Text("ID: $id", modifier = Modifier.padding(bottom = 6.dp))
            Text("Banda: $banda", modifier = Modifier.padding(bottom = 6.dp))
            Text("Título: $titulo", modifier = Modifier.padding(bottom = 6.dp))
            Text("Pulgadas: $pulgadas", modifier = Modifier.padding(bottom = 6.dp))
        }
        Row() {
            Button( //este botón es para volver al menu principal
                modifier = Modifier.padding(5.dp),
                onClick = {
                    opcion="ver_productos"
                },
                colors= ButtonDefaults.buttonColors(backgroundColor= androidx.compose.ui.graphics.Color.White,
                    contentColor = androidx.compose.ui.graphics.Color.Black)
            ) {
                Text("Volver")
            }// fin 1º botón
            Button(
                modifier = Modifier.padding(5.dp),
                onClick = {
                    if (posicion_vinilo < lista_vinilos.size-1) {
                        posicion_vinilo += 1
                    } else {
                        posicion_vinilo = 0
                    }
                }
            ) {
                Text("Siguiente")
            }
        }
    }

    return opcion
}

@Composable
@Preview
fun ver_cassettes():String{
    var opcion by remember { mutableStateOf("ver_cassettes") }

    var posicion_cassette by remember { mutableStateOf(0) }


    val lista_cassetes=mostrar_cassettes()

    val id = ((lista_cassetes[posicion_cassette]).id)
    val banda = ((lista_cassetes[posicion_cassette]).banda)
    val titulo = ((lista_cassetes[posicion_cassette]).titulo)
    val segunda_mano = ((lista_cassetes[posicion_cassette]).segunda_mano)


    //INTERFAZ GRÁFICA pag compra
    BoxWithConstraints(
        modifier = Modifier.background(Color.LightGray).fillMaxWidth().fillMaxHeight()
    ) {
        Image(
            painter = painterResource("pexels-markus-spiske-92083.jpg"),
            contentDescription = "image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        Column(
            modifier = Modifier
                .padding(16.dp)
                .border(1.dp, Color.Black)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )

        {
            Text("ID: $id", modifier = Modifier.padding(bottom = 6.dp))
            Text("Banda: $banda", modifier = Modifier.padding(bottom = 6.dp))
            Text("Título: $titulo", modifier = Modifier.padding(bottom = 6.dp))
            Text("Segunda Mano: $segunda_mano", modifier = Modifier.padding(bottom = 6.dp))
        }
        Row() {
            Button( //este botón es para volver al menu principal
                modifier = Modifier.padding(5.dp),
                onClick = {
                    opcion="ver_productos"
                },
                colors= ButtonDefaults.buttonColors(backgroundColor= androidx.compose.ui.graphics.Color.White,
                    contentColor = androidx.compose.ui.graphics.Color.Black)
            ) {
                Text("Volver")
            }// fin 1º botón
            Button(
                modifier = Modifier.padding(5.dp),
                onClick = {
                    if (posicion_cassette < lista_cassetes.size-1) {
                        posicion_cassette += 1
                    } else {
                        posicion_cassette = 0
                    }
                }
            ) {
                Text("Siguiente")
            }
        }
    }

    return opcion
}

@Composable
@Preview
fun menu_principal(): String{
    var opcion by remember { mutableStateOf("menu_principal") }

    BoxWithConstraints(
        modifier = Modifier.background(Color.LightGray).fillMaxWidth().fillMaxHeight()
    ) {
        Image(
            painter = painterResource("pexels-tima-miroshnichenko-6827396.jpg"),
            contentDescription = "image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
        {
            Row {
                Button(modifier = Modifier.padding(5.dp), onClick = { opcion = "comprar" }) {
                    Text("Comprar Discos")
                }
            } //FIN 1º ROW

            Row {
                Button(modifier = Modifier.padding(5.dp), onClick = { opcion = "ver_productos" }) {
                    Text("Ver Discos")
                }
            } //fin 2º row
            Row {
                Button(modifier = Modifier.padding(5.dp), onClick = { opcion = "actualizar" }) {
                    Text("Actualizar usuario")
                }
            } //fin 3º row
            Row {
                Button(modifier = Modifier.padding(5.dp), onClick = { opcion = "inicio" },
                    colors= ButtonDefaults.buttonColors(backgroundColor= androidx.compose.ui.graphics.Color.LightGray,
                        contentColor = androidx.compose.ui.graphics.Color.Black)) {
                    Text("Salir")
                }
            } //fin 3º row

        } //fin columna
    } //fin box

    return opcion
}



@Preview
@Composable
fun programa_tienda(){
    var ventana by remember { mutableStateOf("inicio") }
    var dni_usuario_en_sesion by remember { mutableStateOf("") }

    when (ventana) {
        "inicio" -> ventana=pagina_inicio()
        "registrar" -> ventana=formulario_registro()
        "iniciar_sesion" -> {
            val info_recibida=iniciar_sesion() //nos devuelve un mapa <String, String> donde el KEY
            // es el string que nos dice si permanecemos o cambiamos de ventanada y el VALUE es el dni del usuario en sesión
            for ((clave, valor) in info_recibida){
                ventana=clave
                dni_usuario_en_sesion=valor //actualizamos el mutablestate con el dni del usuario que acaba de iniciar sesión
            }
        }
        "actualizar" -> ventana=formulario_actualizacion_usuario(dni_usuario_en_sesion)
        "menu_principal" -> ventana=menu_principal()
        "comprar" -> ventana=pagina_compra()
        "ver_productos" -> ventana=opciones_ver_discos()
        "ver_cds" -> ventana=ver_cds()
        "ver_cassettes" ->ventana=ver_cassettes()
        "ver_vinilos" -> ventana=ver_vinilos()
    }
}
fun main() = application {
    val titulo_ventana by remember { mutableStateOf("Melting Discos") }
    Window(onCloseRequest = ::exitApplication, title = titulo_ventana) {
       programa_tienda()
    }
}

