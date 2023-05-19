package CLASES

class CD: Disco{

    var cod_descarga="000"

    constructor(id: String, banda: String, titulo: String, cod_descarga: String):super(id, banda, titulo){
        this.cod_descarga=cod_descarga
    }

    //constructor solo con id producto y código DE DESCARGA necesario para la función de ver productos 1 a 1
    constructor(id: String, cod_descarga:String):super( id,"defecto", "defecto"){
        this.cod_descarga=cod_descarga
    }
}