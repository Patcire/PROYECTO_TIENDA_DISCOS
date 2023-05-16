import CLASES.Usuario
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
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
import java.sql.DriverManager

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
fun registrar():String {
    var opcion by remember { mutableStateOf("registrar") }

    var dni by remember { mutableStateOf("") }
    var nombre by remember { mutableStateOf("") }
    var apellidos by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var contrasenia by remember { mutableStateOf("") }

    //PARA CONECTARSE A ORACLE
    val url = "jdbc:oracle:thin:@localhost:1521:xe"
    val mi_usuario = "usuariot"
    val mi_contra = "tusuario2"

    Class.forName("oracle.jdbc.driver.OracleDriver")
    val conexion = DriverManager.getConnection(url, mi_usuario, mi_contra)
    println("Conexión con ORACLE exitosa")

    val usuario_creado= Usuario(dni, nombre, apellidos, correo, contrasenia)
     //objeto tipo Usuario con el que vamos a mandar los datos a la BBDD

    //INTERFAZ GRÁFICA de registrar
    BoxWithConstraints(
        modifier = Modifier.background(Color.LightGray).fillMaxWidth().fillMaxHeight()
    ) {
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
            Row() {
                Button(
                    modifier = Modifier.padding(5.dp), onClick = { opcion = "inicio" },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = androidx.compose.ui.graphics.Color.White,
                        contentColor = androidx.compose.ui.graphics.Color.Black
                    )
                ) {
                    Text("Enviar")
                }
            }
        }
    } //fin box, FIN INTERFAZ GRÁFICA

    //SI OPCION CAMBIA A INICIO, entonces es que se ha enviado el formulario y las variables ya no están vacía
    //y no hay error con la bbdd y los valores nulos
    if (opcion=="inicio"){
        val pasar_usuario_basedatos=conexion.prepareStatement("INSERT INTO USUARIOS (dni, nombre, apellidos, " +
                "correo, contrasenia) VALUES (?, ?, ?, ?, ?)")
        pasar_usuario_basedatos.setString(1, usuario_creado.dni)
        pasar_usuario_basedatos.setString(2, usuario_creado.nombre)
        pasar_usuario_basedatos.setString(3, usuario_creado.apellidos)
        pasar_usuario_basedatos.setString(4, usuario_creado.correo)
        pasar_usuario_basedatos.setString(5, usuario_creado.contrasenia)
        pasar_usuario_basedatos.executeUpdate()

    }
    conexion.close()
    return opcion

}

@Composable
@Preview
fun iniciar_sesion(): String{
    var opcion by remember { mutableStateOf("iniciar_sesion") }

    var correo by remember { mutableStateOf("") }
    var contrasenia by remember { mutableStateOf("") }

    val usuario=Usuario(correo, contrasenia)

    //INTERFAZ GRÁFICA de iniciar_sesion
    BoxWithConstraints(
        modifier = Modifier.background(Color.LightGray).fillMaxWidth().fillMaxHeight()
    ) {
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
            Row() {
                Button(
                    modifier = Modifier.padding(5.dp),
                    onClick = {
                        if (comprobar_usuario(usuario)==true){
                            opcion="menu_principal"
                        }
                    },

                ) {
                    Text("Entrar")
                }
            }
        }
    } //fin box, FIN INTERFAZ GRÁFICA


    return opcion
}

@Composable
@Preview
fun menu_principal(): String{
    var opcion by remember { mutableStateOf("menu_principal") }

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
                Button(modifier = Modifier.padding(5.dp), onClick = { opcion = "iniciar" }) {
                    Text("op1")
                }
            } //FIN 1º ROW

            Row {
                Button(modifier = Modifier.padding(5.dp), onClick = { opcion = "registrar" }) {
                    Text("op2")
                }
            } //fin 2 row

        } //fin columna
    } //fin box

    return opcion
}



@Preview
@Composable
fun programa_tienda(){
    var ventana by remember { mutableStateOf("inicio") }

    when (ventana) {
        "inicio" -> ventana=inicio()
        "registrar" -> ventana=registrar()
        "iniciar_sesion" -> ventana=iniciar_sesion()
        "menu_principal" -> ventana=menu_principal()
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

