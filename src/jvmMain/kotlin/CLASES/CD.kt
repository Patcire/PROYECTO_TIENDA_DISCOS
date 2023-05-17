package CLASES

class CD: Disco{

    var cod_descarga="111"

    constructor(id: String, banda: String, titulo: String, cod_descarga: String):super(id, banda, titulo){
        this.cod_descarga=cod_descarga
    }

}