object MainKt{
    val Matrixes:MutableList<MatrixClass> = mutableListOf()
//    val file: InputStream = File("Name.txt").inputStream()

    @JvmStatic
    fun main(args: Array<String>) {


        addNewMatrix()
        Matrixes[0].hungarianAlgorithm()
        printMatrixeS()

    }

    fun addNewMatrix(){
        Matrixes.add(newMatrix())
    }
    fun newMatrix(): MatrixClass {

        println("Enter size of matrix")
        val sizeOfMatrix: Int = readLine()!!.toInt()
        var matrix= Array(sizeOfMatrix, {IntArray(sizeOfMatrix)})


        for(i in 0 until sizeOfMatrix)
            for (j in 0 until sizeOfMatrix){
                println("[$i][$j]=")
                matrix[i][j]= readLine()!!.toInt()
            }
        println("Set name of matrix")

//        matrix.file.bufferedReader().use { it.readText().toInt() }
        return (MatrixClass(matrix,readLine().toString()))

    }
    fun printMatrixeS(){
        for(matrixCounter in 0 until Matrixes.size) {
            Matrixes[matrixCounter].printMatrix()
        }
    }
    fun printSingleMatrix(Index: Int)
    {
        Matrixes[Index].printMatrix()
    }
}