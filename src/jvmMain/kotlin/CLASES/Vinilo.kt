package CLASES

class Vinilo:Disco {

    var pulgadas=12

    //constructor principal
    constructor(id: String, banda: String, titulo: String, pulgadas:Int):super(id, banda, titulo){
        this.pulgadas=pulgadas
    }

    //constructor secundario solo con id y pulgadas para la función de ver productos 1 a 1
    constructor(id:String, pulgadas: Int):super(id, "defecto", "defecto"){
        this.pulgadas=pulgadas
    }
}