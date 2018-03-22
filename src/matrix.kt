import java.util.*

data class MatrixClass(private var Matrix:Array<IntArray>, private val Name:String) {

    fun getSize(): Int {
        return Matrix.size
    }
    fun printMatrix(){
        println("Name:$Name")
        for(i in 0 until Matrix.size)
            for (j in 0 until Matrix.size){
                println("[$i][$j]=${Matrix[i][j]}")

            }
    }
    fun getName(): String{
        return Name
    }
    fun getMatrix(): Array<IntArray> {
        return Matrix
    }
    fun outputToFile(){

    }
    fun hungarianAlgoritthm(){
        val hungarian = HungarianAlgorithm(Matrix)
       Matrix=hungarian.StepByStep()
    }
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MatrixClass

        if (!Arrays.equals(Matrix, other.Matrix)) return false

        return true
    }
    override fun hashCode(): Int {
        return Arrays.hashCode(Matrix)
    }
}