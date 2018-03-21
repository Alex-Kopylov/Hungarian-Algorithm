class HungarianAlgorithm(private val Matrix:Array<IntArray>) {
    val MatrixSize=Matrix.size

    fun StepByStep() {
        Step1()
        Step2()
    }
    fun Checker():Boolean{
        var flag=0
        for(j in 0 until MatrixSize) {
            for(i in 0 until MatrixSize) {
                if (Matrix[i][j]==0) {
                    flag++
                    break
                }
            }
        }
        return true
    }


    fun Step1(){
        var min=0
        for(j in 0 until MatrixSize) {
            min=0
            for(i in 0 until MatrixSize)
               if (Matrix[i][j]<min)
                   min = Matrix[i][j]
            for(i in 0 until MatrixSize)
                Matrix[i][j]-min
        }
    }//Reduce the rows by subtracting the minimum value of each row from that row.
    fun Step2(){
        var flag=false
        var min=0
        for(j in 0 until MatrixSize) {
            flag=false
            var min=0

            for(i in 0 until MatrixSize)
                if (Matrix[i][j]==0) {
                    flag = true
                    break
                }
                else
                    if (Matrix[i][j]<min)
                        min=Matrix[i][j]

            if (flag==false)
                    for(i in 0 until MatrixSize)
                         Matrix[i][j]-min
        }
    }//If there are columns without a zero, reduce the columns by subtracting the minimum value of each column from that column.
}
