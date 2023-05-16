import CLASES.Usuario
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.SQLException

fun comprobar_usuario(usuario_recibido: Usuario): Boolean{

    //Para conectarme a mi bbdd
    val url = "jdbc:oracle:thin:@localhost:1521:xe"
    val mi_usuario = "usuariot"
    val mi_contra = "tusuario2"

    var respuesta=false // variable que almacena el return. Por defecto es false
    try {
        //establezco conexion
        Class.forName("oracle.jdbc.driver.OracleDriver")
        val conexion = DriverManager.getConnection(url, mi_usuario, mi_contra)
        println("Conexión con ORACLE exitosa")

        //CONSULTA Y COMPROBACIÓN CON EL USUARIO RECIBIDO
        val preparar_consulta = conexion.createStatement()
        val consulta_oracle="SELECT CORREO, CONTRASENIA FROM USUARIOS WHERE CORREO= '${usuario_recibido.correo}'"
        val resultado_consulta= preparar_consulta.executeQuery(consulta_oracle)

        while (resultado_consulta.next()) {

            val correo_devuelto = resultado_consulta.getString("CORREO") //columna correo
            println(correo_devuelto)
            val contrasenia_devuelta = resultado_consulta.getString("CONTRASENIA") //columna contraseña
            println(contrasenia_devuelta)

            if (correo_devuelto==usuario_recibido.correo && contrasenia_devuelta==usuario_recibido.contrasenia){
                respuesta=true
            }
        }
        conexion.close()
    }
    catch (e: SQLException) {
        println("Error en la conexión: ${e.message}")
    } catch (e: ClassNotFoundException) {
        println("No se encontró el driver JDBC: ${e.message}")
    }

    return respuesta
}