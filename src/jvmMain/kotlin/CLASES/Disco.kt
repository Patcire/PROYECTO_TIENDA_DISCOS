package CLASES

open class Disco (id: String, banda: String, titulo: String) {

    var id="1234"

    var banda="defecto"

    var titulo="defecto"


    init {
        this.id=id
        this.banda=banda
        this.titulo=titulo
    }

    //constructor solo id/titulo
    constructor(id: String, nombre_disco: String): this(id, "defecto", nombre_disco)

    //constructor vacío
    constructor(): this("1111", "defecto", "defecto")

}