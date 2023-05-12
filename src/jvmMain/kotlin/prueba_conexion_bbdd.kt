import java.sql.*
fun main() {
    //PARA CONECTARSE A ORACLE
    val url = "jdbc:oracle:thin:@localhost:1521:xe"
    val usuario = "usuariot"
    val contraseña = "tusuario2"
    try {
        Class.forName("oracle.jdbc.driver.OracleDriver")
        val conexion = DriverManager.getConnection(url, usuario, contraseña)
        println("Conexión con ORACLE exitosa")
        conexion.close()
    } catch (e: SQLException) {
        println("Error en la conexión: ${e.message}")
    } catch (e: ClassNotFoundException) {
        println("No se encontró el driver JDBC: ${e.message}")
    }
}