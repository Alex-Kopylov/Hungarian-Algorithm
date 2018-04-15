import kotlin.system.exitProcess

object MainKt {
    private val Matrices: MutableList<MatrixClass> = mutableListOf()
//    val file: InputStream = File("Name.txt").inputStream()
    @JvmStatic
    fun main(args: Array<String>) {
        while (true) {
            printMenu()
            println("Choice:")
            menu(readLine()!!)
        }
    }
    private fun printMenu() {
        println("1-> Add new matrix\n" +
                "2-> Print matrix\n" +
                "3-> Print matrices\n" +
                "Any other-> Exit")
    }
    private fun menu(choice: Any) {
        when(choice) {
            "1"-> addNewMatrix()
            "2"->{
                if (Matrices.isEmpty()) {
                    println("You entered not any matrix")
                    return
                }
                println("Choose matrix to print it on screen")
                for (k in 0 until Matrices.size)
                {
                    println("$k->${Matrices[k].getName()}")
                }
                var i:Int
                while (true) {
                    i= readLine()!!.toInt()
                    try {
                        if ( i < 0 || i > Matrices.size)
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
            "3"->printMatrix ()
            else->exitProcess(0)
        }
    }
    private fun addNewMatrix() {
        Matrices.add(newMatrix())
    }
    private fun addNewMatrux
    private fun newMatrix(): MatrixClass {
        var size: Int
        while (true) {
            println("Enter size of matrix")
            try {
                size = readLine()!!.toInt()
                if ( size <= 0 )
                    throw Exception("Size can't be <=0")
            } catch (e: Exception) {
                println("ERROR:$e")
                continue
            }
            break
        }



        val matrix = Array(size, { IntArray(size) })
        println("Enter matrix (separate elements by ' ' or ','")

        var i = 0
        while (i < size) {
            print("[$i]\t")
            try {
                matrix[i] = readLine()!!.split(",", " ").map { it.toInt() }.toIntArray()
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
        return MatrixClass(matrix, "name")
    }
    private fun printMatrix() {
        if (Matrices.isEmpty()) {
            println("You entered not any matrix")
            return
        }
        for (matrixCounter in 0 until Matrices.size) {
            Matrices[matrixCounter].printMatrix()
            Matrices[matrixCounter].printMatrixHungarian()
            print("\n\n")
        }
    }
    private fun printMatrix(i:Int) {
        Matrices[i].printMatrix()
        Matrices[i].printMatrixHungarian()
    }
}