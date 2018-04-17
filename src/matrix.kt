class MatrixClass(private val Matrix:Array<IntArray>, private val Name:String) {

    private var hungarianAssignment: IntArray? =null
       get() {
           if (field==null) {
               println("Would you like to trace it (type YES)")
               field = if (readLine()=="YES")
                   HungarianAlgorithm(Matrix).StepByStep(Name)
               else
                   HungarianAlgorithm(Matrix).StepByStep()
           }
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
        println("Hungarian:")
        for(i in 0 until Matrix.size){
            for (j in 0 until Matrix.size)
                if (j== hungarianAssignment!![i])
                    print("[${Matrix[i][j]}]\t")
                else
                    print("${Matrix[i][j]}\t")
            print("\n")
        }
    }
    fun getName()=Name
    fun getMatrix()=Matrix
    fun outputToFile(){

    }
}