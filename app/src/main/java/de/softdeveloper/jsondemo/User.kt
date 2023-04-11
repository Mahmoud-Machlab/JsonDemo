package de.softdeveloper.jsondemo

data class User(
    val name:String?,
    val job: String?,
    val age: Int,
    val hobbys: ArrayList<String?>,
    val phone:Phone?
)

data class Phone(
    val private:String?,
    val office:String?,
    val mobile:String?
)