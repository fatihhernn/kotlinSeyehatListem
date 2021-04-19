package com.fatihhernn.kotlinseyehatlistem.Models

import java.io.Serializable

class Place(var address:String?, var latitute:Double?, var longitude:Double?):Serializable {

    //Serializable => activite arasında model göndermek için kullandık
}