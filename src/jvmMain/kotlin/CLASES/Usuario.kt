package CLASES

class Usuario(dni: String, nombre:String, apellidos: String, correo: String, contrasenia: String) {

    var dni="12345678Z"
    var nombre="defecto"

    var apellidos="defecto"

    var correo="ejemplo@gmail.com"

    var contrasenia="defecto"

    init {
        this.dni=dni
        this.nombre=nombre
        this.apellidos=apellidos
        this.correo=correo
        this.contrasenia=contrasenia
    }

    //constructor solo con contraseña y usuario para comprobar registros
    constructor(correo: String, contrasenia: String) : this("12345678C", "defecto", "defecto", correo, contrasenia)

    //constructor por defecto para crear usuarios "vacíos"
    constructor():this("12345678D", "defecto", "defecto", "defecto", "defecto")

    fun dar_disco_alta(){
        // TODO:  
    }
    
    fun modificar_disco(){ 
    // TODO:  
    }
    
    fun comprar_disco(){
        // TODO:
    }
}