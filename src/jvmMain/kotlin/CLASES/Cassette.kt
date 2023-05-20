package CLASES

class Cassette : Disco {

    var segunda_mano="S"

    constructor(id: String, banda: String, titulo: String, segunda_mano: String):super(id, banda, titulo){
        this.segunda_mano=segunda_mano
    }

    //constructor secundario solo con id y segunda_mano utilizado para despúes poder ver 1 a 1
    constructor(id: String, segunda_mano: String):super(id, "defecto", "defecto"){
        this.segunda_mano=segunda_mano
    }

}