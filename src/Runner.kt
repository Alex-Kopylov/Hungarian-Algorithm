fun main(args: Array<String>) {
    while (true) {
        Main.printMenu()
        println("Choice:")
        Main.menu(readLine()!!)
    }
}