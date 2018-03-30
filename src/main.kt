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
//
//        println("Enter size of matrix")
//        val sizeOfMatrix: Int = readLine()!!.toInt()

        var matrix= Array(5, {IntArray(5)})
        matrix[0]= intArrayOf(10,19,8,15,19)
        matrix[1]= intArrayOf(10,18,7,17,19)
        matrix[2]= intArrayOf(13,16,9,14,19)
        matrix[3]= intArrayOf(12,19,8,18,19)
        matrix[4]= intArrayOf(14,17,10,19,19)
//
//
//        for(i in 0 until sizeOfMatrix)
//            for (j in 0 until sizeOfMatrix){
//                println("[$i][$j]=")
//                matrix[i][j]= readLine()!!.toInt()
//            }
//        println("Set name of matrix")
//
////        matrix.file.bufferedReader().use { it.readText().toInt() }
//        return (MatrixClass(matrix,readLine().toString()))
        return MatrixClass(matrix,"name")

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