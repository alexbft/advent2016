fun readLines(resourceName: String): List<String> {
    return {}::class.java.getResource(resourceName)!!.readText().lines()
}
