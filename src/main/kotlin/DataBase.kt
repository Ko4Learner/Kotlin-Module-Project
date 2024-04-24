data class Archive(val name: String, val noteList: MutableList<Note> = mutableListOf())

data class Note(val name: String, var content: String = "")

enum class Menu {
    ArchiveSelection,
    NoteSelection,
    NoteScreen,
    CreateArchive,
    CreateNote
}
var menu: Menu = Menu.ArchiveSelection


val archiveList: MutableList<Archive> = mutableListOf()
var archiveItem: Int? = null
var noteItem: Int? = null