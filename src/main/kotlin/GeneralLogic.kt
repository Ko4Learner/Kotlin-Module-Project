import java.util.Scanner
import kotlin.system.exitProcess

enum class Menu {
    ArchiveSelection,
    NoteSelection,
    NoteScreen,
    CreateArchive,
    CreateNote
}

data class Archive(val name: String, val noteList: MutableList<Note> = mutableListOf())

data class Note(val name: String, var content: String = "")

var menu: Menu = Menu.ArchiveSelection

var archiveItem: Int? = null
var noteItem: Int? = null


fun <T> printList(list: MutableList<T>): String {
    var print = ""
    if (list.isNotEmpty()) {
        for ((index, element) in list.withIndex()) {
            print += "${index + 1}. ${element}\n"
        }
    }
    return print
}

fun printScreen() {
    when (menu) {
        Menu.ArchiveSelection -> println(
            "\nВведите номер пункта: \n0. Создать новый архив \n${
                printList(
                    archiveList
                )
            }${archiveList.size + 1}. Выход"
        )

        Menu.CreateArchive -> println("\nВведите название массива:")

        Menu.NoteSelection -> println(
            "\nВведите номер пункта: \n0. Создать новую заметку \n${
                printList(
                    archiveList[archiveItem!!].noteList
                )
            }${archiveList[archiveItem!!].noteList.size + 1}. Выход"
        )

        Menu.CreateNote -> println("\nВведите название заметки и её содержание через Enter:")

        Menu.NoteScreen -> println(
            "\nЗаметка: ${archiveList[archiveItem!!].noteList[noteItem!!].name} \nСодержание: " +
                    "${archiveList[archiveItem!!].noteList[noteItem!!].content} \nВведите номер пункта: \n1. Изменить содержание \n2. Выход"
        )
    }
}


fun scannerException(): Int {
    while (true) {
        try {
            return Scanner(System.`in`).nextLine().toInt()
        } catch (e: NumberFormatException) {
            println("\nНеобходимо ввести число из представленных пунктов меню\n")
            printScreen()
            continue
        }
    }
}

fun commandScanner() {
    printScreen()
    when (menu) {
        Menu.ArchiveSelection -> {
            while (true) {
                when (val command = scannerException()) {
                    0 -> {
                        menu = Menu.CreateArchive
                        commandScanner()
                    }

                    in 0..archiveList.size -> {
                        menu = Menu.NoteSelection
                        archiveItem = command - 1
                        commandScanner()
                    }

                    archiveList.size + 1 -> exitProcess(0)
                    else -> {
                        println("\nВыбранный номер пункта меню отсутствует\n")
                        printScreen()
                    }
                }
            }
        }

        Menu.CreateArchive -> {
            var name: String
            while (true) {
                name = Scanner(System.`in`).nextLine()
                if (name != "") break
                else println("\nНеобходимо ввести название архива\n")
            }
            create(name)
        }

        Menu.NoteSelection -> {
            while (true) {
                when (val command = scannerException()) {
                    0 -> {
                        menu = Menu.CreateNote
                        commandScanner()
                    }

                    in 0..archiveList[archiveItem!!].noteList.size -> {
                        menu = Menu.NoteScreen
                        noteItem = command - 1
                        commandScanner()
                    }

                    archiveList[archiveItem!!].noteList.size + 1 -> {
                        menu = Menu.ArchiveSelection
                        commandScanner()
                    }

                    else -> {
                        println("\nВыбранный номер пункта меню отсутствует\n")
                        printScreen()
                    }
                }
            }
        }

        Menu.CreateNote -> {
            var name: String
            var content: String
            while (true) {
                name = Scanner(System.`in`).nextLine()
                content = Scanner(System.`in`).nextLine()
                if (name != "" && content != "") break
                else println("\nНеобходимо ввести название и содержание заметки\n")
            }
            create(name, content)
        }

        Menu.NoteScreen -> {
            while (true) {
                when (scannerException()) {
                    1 -> {
                        while (true) {
                            println("\nВведите новое содержание заметки\n")
                            val content = Scanner(System.`in`).nextLine()
                            if (content != "") {
                                archiveList[archiveItem!!].noteList[noteItem!!].content = content
                                commandScanner()
                                break
                            } else println("\nСодержание заметки не должно быть пустым\n")
                        }
                    }

                    2 -> {
                        menu = Menu.NoteSelection
                        printScreen()
                        commandScanner()
                    }

                    else -> {
                        println("\nВыбранный номер пункта меню отсутствует\n")
                        printScreen()
                    }
                }
            }
        }
    }
}

fun create(name: String, content: String = "") {

    if (menu == Menu.CreateArchive) {
        archiveList.add(Archive(name))
        println("\nАрхив $name успешно создан\n")
        menu = Menu.ArchiveSelection
        printScreen()
    } else {
        archiveList[archiveItem!!].noteList.add(Note(name, content))
        println("\nЗаметка $name в архиве ${archiveList[archiveItem!!].name} успешно создана\n")
        menu = Menu.NoteSelection
        printScreen()
    }
    return
}