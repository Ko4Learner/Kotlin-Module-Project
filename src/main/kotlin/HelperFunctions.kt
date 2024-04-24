import java.util.Scanner

fun <T> printList(list: MutableList<T>): String { //оставил отображение архива с полным содержанием для наглядности привязки заметок к архиву
    var print = ""
    if (list.isNotEmpty()) {
        for ((index, element) in list.withIndex()) {
            print += "${index + 1}. ${element}\n"
        }
    }
    return print
}

fun scannerException(): Int {
    while (true) {
        try {
            return Scanner(System.`in`).nextLine().toInt()
        } catch (e: NumberFormatException) {
            println("\nНеобходимо ввести число из представленных пунктов меню")
            printScreen()
            continue
        }
    }
}