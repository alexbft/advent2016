package bootstrap

fun readLines(resourceName: String): List<String> {
    return {}::class.java.getResource("/$resourceName")!!.readText().trimEnd().lines()
}
