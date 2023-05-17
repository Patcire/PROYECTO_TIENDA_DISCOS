package CLASES

class Cassette : Disco {

    var segunda_mano="S"

    constructor(id: String, banda: String, titulo: String, segunda_mano: String):super(id, banda, titulo){
        this.segunda_mano=segunda_mano
    }
}