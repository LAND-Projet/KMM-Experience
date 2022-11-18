package com.dardev.koinkmm.data

interface IUserRepository {
    fun findUser(name:String): User?
    fun addUsers(users: List<User>)
}

class UserRepository: IUserRepository {

    private val _users = arrayListOf<User>()

    override fun findUser(name: String): User? {
        return _users.firstOrNull { it.name == name }
    }

    override fun addUsers(users: List<User>) {
        _users.addAll(users)
    }

}