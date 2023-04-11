package de.softdeveloper.jsondemo

data class UserList(
    val description: String?,
    val isEmpty: Boolean?,
    val since: Int?,
    val users: ArrayList<User>,
    val version: Double?
)