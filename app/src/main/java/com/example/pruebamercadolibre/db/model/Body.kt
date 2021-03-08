package com.example.pruebamercadolibre.db.model

class Body {

    var title: String? = null
    var price: Int? = null
    var pictures = ArrayList<Picture>()
    var attributes = ArrayList<Attribute>()
}