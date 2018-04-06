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
        var min:Int
        for (i in 0 until MatrixSize) {
            min = Matrix[i][0]
            for (j in 1 until MatrixSize)
                if (Matrix[i][j] < min)
                    min = Matrix[i][j]
            for (j in 0 until MatrixSize)
                Matrix[i][j]-=min
        }
    }


//If there are columns without a zero,
    // reduce the columns by subtracting the minimum value of each
    // column from that column.
    fun Step2() {
    var min:Int
    for (j in 0 until MatrixSize) {
        min = Matrix[0][j]
        for (i in 1 until MatrixSize)
            if (Matrix[i][j] < min)
                min = Matrix[i][j]
        for (i in 0 until MatrixSize)
            Matrix[i][j]-=min
    }
    }


    fun Step3() {
        val coveredCollumns = mutableListOf<Int>()
        val coveredRows = mutableListOf<Int>()


        var min= Int.MAX_VALUE
        var zeroMaxI=0
        var zeroMaxColumn=0
        var zeroMaxJ=0
        var toCoverColumn: Int? = null
        var toCoverRow: Int? =null
        var zeroesUncoveredRow= mutableListOf<Int>()
        var zeroesUncoveredColumn= mutableListOf<Int>()
        var n=0

        for(row in 0 until MatrixSize)
            for (column in 0 until MatrixSize)
                if (Matrix[row][column]==0) {
                    zeroesUncoveredColumn.add(column)
                    zeroesUncoveredRow.add(row)
                }

        while (!zeroesUncoveredColumn.isEmpty()) // or Row. It doesn't matter
        {
            //searching max number of zeros in rows
            zeroMaxI = 0
            for (i in 0 until MatrixSize)
                if (zeroMaxI < zeroesUncoveredRow.count { it == i }) {
                    zeroMaxI = zeroesUncoveredRow.count { it == i }
                    toCoverRow=i
                }

            //searching max number of zeros in columns
            zeroMaxJ = 0
            for (j in 0 until MatrixSize) {
                if (coveredCollumns.contains(j))
                    continue
                zeroMaxColumn = 0

                for (i in 0 until MatrixSize)
                    if (Matrix[i][j] == 0 && zeroesUncoveredColumn.contains(j)) {
                        zeroMaxColumn++
                    }
                if (zeroMaxColumn > zeroMaxJ) {
                    zeroMaxJ = zeroMaxColumn
                    toCoverColumn = j
                }
            }


            if (zeroMaxI > zeroMaxJ) {
                n = 0
                while (true) {
                    if (n >= zeroesUncoveredRow.size)
                        break
                    if (zeroesUncoveredRow[n] == toCoverRow) {
                        zeroesUncoveredColumn.removeAt(n)
                        zeroesUncoveredRow.removeAt(n)
                    }
                    else
                         n++
                }
                coveredRows.add(toCoverRow!!)
            }
            else {
                n = 0
                while (true) {
                    if (n >= zeroesUncoveredColumn.size)
                        break
                    if (zeroesUncoveredColumn[n] == toCoverColumn) {
                        zeroesUncoveredColumn.removeAt(n)
                        zeroesUncoveredRow.removeAt(n)

                    }
                    else
                        n++
                }
                coveredCollumns.add(toCoverColumn!!)
            }
        }
//        for (k in 0 until MatrixSize)
//            for (l in 0 until MatrixSize) {
//                 zeroCounterIMAX = 0
//                 zeroCounterJMAX = 0
//
//
//
//
//
//                for (i in 0 until MatrixSize)
//                    for (j in 0 until MatrixSize) {
//                        if (Matrix[i][j] == 0 && !(coveredCollumns.contains(i) || coveredRows.contains(j))) {
//                            zeroCounterI = 0
//                            zeroCounterJ = 0
//                            for (k in 0 until MatrixSize)
//                                if (Matrix[k][j] == 0)
//                                    zeroCounterJ++
//                            for (l in 0 until MatrixSize)
//                                if (Matrix[i][l] == 0)
//                                    zeroCounterI++
//                            when {
//                                zeroCounterI > zeroCounterJ ->
//                                    coveredCollumns.add(i)
//                                zeroCounterI < zeroCounterJ ->
//                                    coveredRows.add(j)
//
//                            }
//                        }
//                    }
//            }

        if(coveredCollumns.size==coveredRows.size)
            return



            //Add the minimum uncovered element
            // to every covered element.
            // If an element is covered twice,
            // add the minimum element to it twice.
            min= Int.MAX_VALUE
        for (i in 0 until MatrixSize)
            for (j in 0 until MatrixSize) {
                if (!((coveredCollumns.contains(i) || coveredRows.contains(j))))
                    if (Matrix[i][j] < min)
                        min = Matrix[i][j]
            }


        for (i in 0 until MatrixSize)
                for (j in 0 until MatrixSize) {
                    if (coveredCollumns.contains(i) && coveredRows.contains(j)) //if zero covered twice
                        Matrix[i][j] += min + min
                    else
                        if (coveredCollumns.contains(i) || coveredRows.contains(j))
                            Matrix[i][j] += min
                }

            //Subtract the minimum element
            // from every element in the matrix.
        min= Int.MAX_VALUE
            for (i in 0 until MatrixSize)
                for (j in 0 until MatrixSize)
                    if (Matrix[i][j]<min)
                        min=Matrix[i][j]
            for (i in 0 until MatrixSize)
                for (j in 0 until MatrixSize)
                    Matrix[i][j]-=min

        return Step3()
    }




    fun Step4(){


    }
}

