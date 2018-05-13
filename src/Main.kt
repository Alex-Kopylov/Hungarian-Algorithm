import java.io.File
import kotlin.system.exitProcess
object Main {
    private val Matrices: MutableList<Matrix> = mutableListOf()



     internal fun printMenu() {
        println("1-> Add new matrix\n" +
                "2-> Print matrix\n" +
                "3-> Print matrices\n" +
                "4-> Add matrix from file\n" +
                "Any other-> Exit")
    }

     internal fun menu(choice: Any) {
        when (choice) {
            "1" -> Matrices.add(newMatrix())
            "2" -> {
                if (Matrices.isEmpty()) {
                    println("You entered not any matrix")
                    return
                }
                println("Choose matrix to print it on screen")
                for (k in 0 until Matrices.size) {
                    println("$k->${Matrices[k].getName()}")
                }
                var i: Int
                while (true) {
                    i = readLine()!!.toInt()
                    try {
                        if (i < 0 || i > Matrices.size)
                            throw Exception("Out of range")
                    } catch (e: Exception) {
                        print("ERROR:$e\n" +
                                "enter correct number:")
                        continue
                    }
                    break
                }
                printMatrix(i)
            }
            "3" -> {
                if (Matrices.isEmpty()) {
                    println("You entered not any matrix")
                    return
                }
                printMatrix()
            }
            "4" -> {
                val file:File
                val Matrix:Array<IntArray>
                 try {
                     print("Enter name of file: ")
                     file=File("${readLine()}.txt")
                     println("Your file is: ${file.name} Trying to get matrix...")
                     val list = file.readLines()
                     Matrix=Array(list.size, { i -> list[i].removeSurrounding("[", "]").split(",", " ", "\t").map { it.toInt() }.toIntArray() })
                }
                 catch (e: Exception) {
                     println("ERROR: $e")
                     return
                 }
                println("Matrix loaded successfully")
                Matrices.add(newMatrix(Matrix))
                 }
            else -> exitProcess(0)
        }
        Matrices.last().writeHungarianInFile()
    }

    private fun newMatrix(Matrix:Array<IntArray>): Matrix=Matrix(Matrix, setNameOfMatrix(), setFile())

    private fun newMatrix(): Matrix {
        var size: Int
        while (true) {
            println("Enter size of matrix")
            try {
                size = readLine()!!.toInt()
                if (size <= 0)
                    throw Exception("Size can't be <=0")
            } catch (e: Exception) {
                println("ERROR:$e")
                continue
            }
            break
        }


        val matrix = Array(size, { IntArray(size) })
        println("Enter matrix (separate elements by ' '(space) or ','")

        var i = 0
        while (i < size) {
            print("[$i]\t")
            try {
                matrix[i] = readLine()!!.split(",", " ", "\t").map { it.toInt() }.toIntArray()
                if (matrix[i].size != size) {
                    throw
                    Exception("Not enough elements (Matrix[$i].size:${matrix[i].size} != $size)")
                }
            } catch (e: Exception) {
                println("ERROR:$e")
                i--
            }
            i++
        }
        return Matrix(matrix, setNameOfMatrix(), setFile())
    }

    private fun printMatrix() {
        for (matrixCounter in 0 until Matrices.size) {
            Matrices[matrixCounter].printMatrix()
            Matrices[matrixCounter].printHungarian()
            print("\n\n")
        }
    }

    private fun printMatrix(i: Int) {
        Matrices[i].printMatrix()
        Matrices[i].printHungarian()
    }

    private fun setNameOfMatrix(): String {
        var name: String
        println("Type name of matrix: ")
        try {
            name = readLine()!!
        } catch (e: Exception) {
            println("ERROR: $e")
            name = "Matrix ${(Matrices.size)}"
            println("Name of this matrix will be ${(Matrices.size)}")
        }
        return name
    }

    private fun setFile(): File? {
        println("\nWould you like to write this matrix and assignment in file? Type YES")
        if (readLine() == "YES") {
            println("Enter name of file")
            return File("${readLine()}.txt")
        }
        return null
    }
}
