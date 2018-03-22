class HungarianAlgorithm(private val Matrix:Array<IntArray>) {
    val MatrixSize = Matrix.size

    fun StepByStep(): Array<IntArray> {
        Step1()
        Step2()
        Step3()
        return Matrix
    }

    fun Checker(): Boolean {
        var flag = 0
        for (j in 0 until MatrixSize) {
            for (i in 0 until MatrixSize) {
                if (Matrix[i][j] == 0) {
                    flag++
                    break
                }
            }
        }
        return true
    }


    //Reduce the rows by subtracting
    // the minimum value of each row from that row.
    fun Step1() {
        var min = 0
        for (j in 0 until MatrixSize) {
            min = 0
            for (i in 0 until MatrixSize)
                if (Matrix[i][j] < min)
                    min = Matrix[i][j]
            for (i in 0 until MatrixSize)
                Matrix[i][j] - min
        }
    }


//If there are columns without a zero,
    // reduce the columns by subtracting the minimum value of each
    // column from that column.
    fun Step2() {
        var flag = false
        var min = 0
        for (j in 0 until MatrixSize) {
            flag = false
            var min = 0

            for (i in 0 until MatrixSize)
                if (Matrix[i][j] == 0) {
                    flag = true
                    break
                } else
                    if (Matrix[i][j] < min)
                        min = Matrix[i][j]

            if (flag == false)
                for (i in 0 until MatrixSize)
                    Matrix[i][j] - min
        }
    }


    fun Step3() {
        val coveredI = mutableListOf<Int>()
        val coveredJ = mutableListOf<Int>()
        var zeroCounterI = 0
        var zeroCounterJ = 0
        var minUncovered = 0
        var minElementInMatrix =0
        for (i in 0 until MatrixSize)
            for (j in 0 until MatrixSize) {
                if (Matrix[i][j] == 0 && !(coveredI.contains(i) || coveredI.contains(j))) {
                    zeroCounterI = 0
                    zeroCounterJ = 0
                    for (k in i until MatrixSize)
                        if (Matrix[k][j] == 0)
                            zeroCounterJ++
                    for (l in j until MatrixSize)
                        if (Matrix[i][l] == 0)
                            zeroCounterI++
                    if (zeroCounterI > zeroCounterJ)
                        coveredI.add(i)
                    else
                        coveredJ.add(j)
                }
            }
        for (i in 0 until MatrixSize)
            for (j in 0 until MatrixSize) {
                if (!((coveredI.contains(i) || coveredI.contains(j))))
                    if (Matrix[i][j] < minUncovered)
                        minUncovered = Matrix[i][j]
            }

        if(coveredI.size==coveredJ.size)
            return
            //Add the minimum uncovered element
            // to every covered element.
            // If an element is covered twice,
            // add the minimum element to it twice.
            for (i in 0 until MatrixSize)
                for (j in 0 until MatrixSize) {
                    if (coveredI.contains(i) && coveredI.contains(j))
                        Matrix[i][j] += minUncovered + minUncovered
                    else
                        if (coveredI.contains(i) || coveredI.contains(j))
                            Matrix[i][j] += minUncovered
                }

            //Subtract the minimum element
            // from every element in the matrix.
            for (i in 0 until MatrixSize)
                for (j in 0 until MatrixSize)
                    if (Matrix[i][j]<minElementInMatrix)
                        minElementInMatrix=Matrix[i][j]
            for (i in 0 until MatrixSize)
                for (j in 0 until MatrixSize)
                    Matrix[i][j]-=minElementInMatrix

        return Step3()
    }
    fun Step4(){


    }
}

