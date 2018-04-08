import java.io.File
import java.io.InputStream
import java.util.*

data class MatrixClass(private val Matrix:Array<IntArray>, private val Name:String) {

    var hungarianAssigment: IntArray? =null
       get() {
           if (field==null)
              field=HungarianAlgorithm(Matrix).StepByStep()
            return field
       }

    fun getSize(): Int {
        return Matrix.size
    }
    fun printMatrix(){
        println("Name:$Name")
        for(i in 0 until Matrix.size){
            for (j in 0 until Matrix.size)
                print("${Matrix[i][j]}\t")
            print("\n")
            }
    }
    fun printMatrixHungarian(){
        println("Name:$Name")
        for(i in 0 until Matrix.size){
            for (j in 0 until Matrix.size)
                if (j== hungarianAssigment!![i])
                    print("[${Matrix[i][j]}]\t")
                else
                    print("${Matrix[i][j]}\t")
            print("\n")
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