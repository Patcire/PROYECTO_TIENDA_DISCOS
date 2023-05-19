import CLASES.*
import java.sql.DriverManager
import java.sql.SQLException


fun registrar_usuario(usuario_recibido: Usuario):Boolean{
    //PARA CONECTARSE A ORACLE
    val url = "jdbc:oracle:thin:@localhost:1521:xe"
    val mi_usuario = "usuariot"
    val mi_contra = "tusuario2"

    try {
        Class.forName("oracle.jdbc.driver.OracleDriver")
        val conexion = DriverManager.getConnection(url, mi_usuario, mi_contra)
        println("Conexión con ORACLE exitosa")

        val pasar_usuario_basedatos = conexion.prepareStatement(
            "INSERT INTO USUARIOS (dni, nombre, apellidos, correo, contrasenia) VALUES (?, ?, ?, ?, ?) ")

        pasar_usuario_basedatos.setString(1, usuario_recibido.dni)
        pasar_usuario_basedatos.setString(2, usuario_recibido.nombre)
        pasar_usuario_basedatos.setString(3, usuario_recibido.apellidos)
        pasar_usuario_basedatos.setString(4, usuario_recibido.correo)
        pasar_usuario_basedatos.setString(5, usuario_recibido.contrasenia)
        pasar_usuario_basedatos.executeUpdate()

        conexion.close()
    }
    catch (e: SQLException) {
         println("Error en la conexión: ${e.message}")
        return false
    } catch (e: ClassNotFoundException) {
     println("No se encontró el driver JDBC: ${e.message}")
    }

    return true
}



fun comprobar_usuario(usuario_recibido: Usuario): MutableList <String> {

    //Para conectarme a mi bbdd
    val url = "jdbc:oracle:thin:@localhost:1521:xe"
    val mi_usuario = "usuariot"
    val mi_contra = "tusuario2"

    var respuesta = false // variable que almacena el return. Por defecto es false

    var dni_devuelto = "" //almacenará el id del objeto usuario que devuelve luego la consulta
    //no es necesario para hacer la comprobación ya que el correo ya es único, pero lo vamos a devolver
    //para almacenarlo durante la sesión y que ese usuario solo pueda actualizarse a sí mismo


    var lista_respuesta_dni = mutableListOf<String>(respuesta.toString(), dni_devuelto)

    try {
        //establezco conexion
        Class.forName("oracle.jdbc.driver.OracleDriver")
        val conexion = DriverManager.getConnection(url, mi_usuario, mi_contra)
        println("Conexión con ORACLE exitosa")

        //CONSULTA Y COMPROBACIÓN CON EL USUARIO RECIBIDO
        val preparar_consulta = conexion.createStatement()
        val consulta_oracle = "SELECT CORREO, CONTRASENIA, DNI FROM USUARIOS WHERE CORREO= '${usuario_recibido.correo}'"
        val resultado_consulta = preparar_consulta.executeQuery(consulta_oracle)


        while (resultado_consulta.next()) {

            val correo_devuelto = resultado_consulta.getString("CORREO") //columna correo
            println(correo_devuelto) //este print ese solo para la terminal, para comprobar que se ha recuperado bien la info

            val contrasenia_devuelta = resultado_consulta.getString("CONTRASENIA") //columna contraseña
            println(contrasenia_devuelta) //este print ese solo para la terminal, para comprobar que se ha recuperado bien la info

            dni_devuelto = resultado_consulta.getString("DNI")
            println(dni_devuelto) //este print ese solo para la terminal, para comprobar que se ha recuperado bien la info

            if (correo_devuelto == usuario_recibido.correo && contrasenia_devuelta == usuario_recibido.contrasenia) {
                lista_respuesta_dni.add(0, true.toString())
                lista_respuesta_dni.add(1, dni_devuelto)
            }

        }
        conexion.close()
    }
        catch(e: SQLException) {
            println("Error en la conexión: ${e.message}")
        } catch (e: ClassNotFoundException) {
            println("No se encontró el driver JDBC: ${e.message}")
        }

        return lista_respuesta_dni

}

fun comprar(disco: Disco): Boolean{
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

        val consulta= "DELETE FROM DISCOS WHERE ID_DISCO=? AND TITULO=?"

        val preparar_borrado = conexion.prepareStatement(consulta)
        preparar_borrado.setString(1, disco.id)
        preparar_borrado.setString(2, disco.titulo)

        val orden_borrado = preparar_borrado.executeUpdate()
        //executeupdate nos devuelve el num de filas que se han eliminado

        if (orden_borrado==1){
            respuesta=true
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


fun mostrar_disco():MutableList<Disco>{
    //con esta lista saco la información común a los discos independientementes del tipo que sean

    var lista_discos= mutableListOf<Disco>() //esta lista almacenaré la info de la consulta y la pasaré
    //a las funciones de obtener info por cada tipo

    //Para conectarme a mi bbdd
    val url = "jdbc:oracle:thin:@localhost:1521:xe"
    val mi_usuario = "usuariot"
    val mi_contra = "tusuario2"

    try {
        //establezco conexion
        Class.forName("oracle.jdbc.driver.OracleDriver")
        val conexion = DriverManager.getConnection(url, mi_usuario, mi_contra)
        println("Conexión con ORACLE exitosa")

        //CONSULTA para sacar datos de todos los discos
        val preparar_consulta = conexion.createStatement()
        val consulta_oracle="SELECT ID_DISCO, NOMBRE_BANDA, TITULO  FROM DISCOS"
        //saco toda la info común a los discos

        val resultado_consulta= preparar_consulta.executeQuery(consulta_oracle)

        while (resultado_consulta.next()) {

            var id=resultado_consulta.getString(1)
            var banda=resultado_consulta.getString(2)
            var titulo=resultado_consulta.getString(3)

            //almaceno la info de los discos en una lista
            lista_discos.add(Disco(id, banda, titulo ))

        }
        conexion.close()
    }
    catch (e: SQLException) {
        println("Error en la conexión: ${e.message}")
    } catch (e: ClassNotFoundException) {
        println("No se encontró el driver JDBC: ${e.message}")
    }

    return lista_discos
}

fun mostrar_cds():MutableList<CD>{

    //Para conectarme a mi bbdd
    val url = "jdbc:oracle:thin:@localhost:1521:xe"
    val mi_usuario = "usuariot"
    val mi_contra = "tusuario2"


    var lista_info_disco= mostrar_disco() //ejecuta esa función y me devuelve todos los discos

    var lista_cd= mutableListOf<CD>() //para almacenar los discos que me devuelva la consulta

    try {
        //establezco conexion
        Class.forName("oracle.jdbc.driver.OracleDriver")
        val conexion = DriverManager.getConnection(url, mi_usuario, mi_contra)
        println("Conexión con ORACLE exitosa")

        //CONSULTA para sacar datos de los cds
        val preparar_consulta = conexion.createStatement()
        val consulta_oracle="SELECT COD_DESC_DIGITAL, ID_DISCO  FROM CDS" //saco toda la info común a los discos
        val resultado_consulta= preparar_consulta.executeQuery(consulta_oracle)


        while (resultado_consulta.next()) {
            //creo las variables que voy a querer almacenar de cada cd
            var cod_descarga=resultado_consulta.getString(1)
            var id=resultado_consulta.getString(2)

            //almaceno la info de los objetos en una lista mediante un objeto cd
            lista_cd.add(CD(id, cod_descarga))
            //cada resultado de la consulta lo almaceno en la lista gracias
            //a este constructor con solo esos dos parámetros que ponía los otros x defecto

        }
        conexion.close()
    }
    catch (e: SQLException) {
        println("Error en la conexión: ${e.message}")
    } catch (e: ClassNotFoundException) {
        println("No se encontró el driver JDBC: ${e.message}")
    }


    //ahora combinando dos bucles que recorra ambas listas de objetos
    for (cd in lista_cd){
        for (disco in lista_info_disco){
            if (disco.id==cd.id){
                //si los ids son iguales actualiza estos parámetros que son los que estaban dados por defectos
                //con el constructor secundario que se utilizó para almacenar primeramente los cds tras la consulta
                cd.banda=disco.banda
               cd.titulo=disco.titulo
            }
        }
    }


    return lista_cd

}

fun actualir_usuario(usuario_actualizar: Usuario): Boolean {
    //PARA CONECTARSE A ORACLE
    val url = "jdbc:oracle:thin:@localhost:1521:xe"
    val mi_usuario = "usuariot"
    val mi_contra = "tusuario2"

    var respuesta=false //por defecto devuelve false, para que no cambie de ventana

    try {
        Class.forName("oracle.jdbc.driver.OracleDriver")
        val conexion = DriverManager.getConnection(url, mi_usuario, mi_contra)
        println("Conexión con ORACLE exitosa")

        val actualizar_usuario_basedatos = conexion.prepareStatement(
            "UPDATE USUARIOS SET NOMBRE='${usuario_actualizar.nombre}'," +
                    " APELLIDOS='${usuario_actualizar.apellidos}', CORREO='${usuario_actualizar.correo}', " +
                    "CONTRASENIA='${usuario_actualizar.contrasenia}'" +
                    "WHERE dni='${usuario_actualizar.dni}'")

        val filas_actualizadas = actualizar_usuario_basedatos.executeUpdate() //ejecuta el update
        // y almacena cuantas filas han sido actualizadas

        if (filas_actualizadas==1) {
            println("Registro actualizado correctamente")
            respuesta=true
        }
        else{
            println("error en el update")
        }

        actualizar_usuario_basedatos.close()
        conexion.close()
    }
    catch (e: SQLException) {
        println("Error en la conexión: ${e.message}")
        respuesta= false
    } catch (e: ClassNotFoundException) {
        println("No se encontró el driver JDBC: ${e.message}")
    }

    return respuesta

}