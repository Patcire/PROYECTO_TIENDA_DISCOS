package CLASES

class Usuario(nombre:String, apellidos: String, dni: String, correo: String, contrasenia: String) {

    var dni="12345678Z"
    var nombre="defecto"

    var apellidos="defecto"

    var correo="ejemplo@gmail.com"

    var contrasenia="defecto"

    init {
        this.nombre=nombre
        this.apellidos=apellidos
        this.dni=dni
        this.correo=correo
        this.contrasenia=contrasenia
    }
    
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