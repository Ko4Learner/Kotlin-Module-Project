import java.util.Scanner

interface CreateNew {
    fun create()
}

data class Archive(val name: String) : {
    val noteMap: MutableList<Note> = mutableListOf()
}

data class Note(val name: String) {
    val content: String = ""
}

inline fun <reified T> printMenu(list: MutableList<T>) {
    println("Список архивов: \n0. Создать архив \n$list \n${list.size + 1}. Выход")
}

inline fun <reified T> commandScanner(list: MutableList<T>) {
    while (true) {
        val command = Scanner(System.`in`).nextLine()
        when (command.toInt()) {
            0 -> create(list)
            list.size + 1 -> return
        }
    }
}

inline fun <reified T> create(list: MutableList<T>) {
    println("Введите название:")

    val name: String = Scanner(System.`in`).nextLine()
    when (list.firstOrNull()) {
        is Archive -> list.add(Archive(name))
        is Note -> {
            println("Введите содержание заметки:")
            val content =
            list.add(Note(name, Scanner(System.`in`).nextLine()))
        }
    }

}

fun main() {

    val archiveList: MutableList<Archive> = mutableListOf()
    printMenu(archiveList)
    commandScanner(archiveList)

}