import java.util.Scanner


interface Name {
    val name: String
}

data class Archive(override val name: String) : Name {
    val noteMap: MutableMap<Int, String> = mutableMapOf()
}

data class Note(override val name: String) : Name {
    val content: String = ""
}

fun <T : Name> printMenu(map: MutableMap<Int, T>) {
    println("Список архивов: \n0. Создать архив \n$map \n${map.size + 1}. Выход")
}

fun <T : Name> commandScanner(map: MutableMap<Int, T>) {
    while (true) {
        val command = Scanner(System.`in`).nextLine()
        when (command.toInt()) {
            0 -> create(map)
            map.size + 1 -> return
        }
    }
}

fun <T : Name> create(map: MutableMap<Int, T>) {
    println("Введите название:")
    val name: String = Scanner(System.`in`).nextLine()
    // map[map.size + 1] =
    println("$name создан")
}

fun main() {

    val archiveMap: MutableMap<Int, Archive> = mutableMapOf()
    printMenu(archiveMap)
    commandScanner(archiveMap)

}