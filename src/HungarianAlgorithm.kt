class HungarianAlgorithm(private val MatrixLegacy:Array<IntArray>) {
    val MatrixSize = MatrixLegacy.size
    val Matrix=Array(MatrixSize, {IntArray(MatrixSize)})
    var counter=0
    val assigmentRows= IntArray(MatrixSize)// Index of the column selected by every row (The final result)
    val occupiedCols=BooleanArray(MatrixSize)//Verify that all column are occupied, used in the optimization step
    fun StepByStep(): IntArray {
        for(row in 0 until MatrixSize)
            Matrix[row] = MatrixLegacy[row].clone()
        Step1()
        Step2()
        Step3()
        Optimization()

        return assigmentRows
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

    fun countZeroesInRow(i:Int): Int {
        return Matrix[i].count { it==0 }
    }
    fun countZeroesInColumn(j: Int):Int{
        var result=0
        for(i in 0 until MatrixSize)
            if(Matrix[i][j]==0)
                result++
        return result
    }
    fun Step3() {
//        println("------------")
//        for(i in 0 until Matrix.size){
//            for (j in 0 until Matrix.size) {
//                print("${Matrix[i][j]}\t")
//            }
//            print("\n")
//        }
        val coveredColumns = mutableListOf<Int>()
        val coveredRows = mutableListOf<Int>()

        var min= Int.MAX_VALUE
        var zeroMaxI=0
        var zeroMaxInColumn=0
        var zeroMaxJ=0
        var toCoverColumn: Int? = null
        var toCoverRow: Int? =null
        var zeroesUncoveredRow= mutableListOf<Int>()
        var zeroesUncoveredColumn= mutableListOf<Int>()
        var n=0
        var VerHor: Int? =null
        //counting zeroes
        for(row in 0 until MatrixSize)
            loop@ for (column in 0 until MatrixSize)
                if (Matrix[row][column]==0) {
                    VerHor=VerticalOrHorizontal(row,column)
                    when{
                        coveredColumns.contains(column) && coveredRows.contains(row)->
                            continue@loop
                        coveredColumns.contains(column)->
                            continue@loop
                        coveredRows.contains(row)->
                            continue@loop
                        else->
                                if (VerHor>0)
                                    coveredColumns.add(column)
                                else
                                    coveredRows.add(row)

                    }

                }

        if(counter==MatrixSize)
            return
        counter++


        //Add the minimum uncovered element
        // to every covered element.
        // If an element is covered twice,
        // add the minimum element to it twice.
        min= Int.MAX_VALUE
        for (i in 0 until MatrixSize)
            for (j in 0 until MatrixSize) {
                if (!((coveredColumns.contains(j) || coveredRows.contains(i))))
                    if (Matrix[i][j] < min)
                        min = Matrix[i][j]
            }


        for (i in 0 until MatrixSize)
                for (j in 0 until MatrixSize)
                    when {
                        (coveredColumns.contains(j) && coveredRows.contains(i))-> //if zero covered twice
                            Matrix[i][j] += min*2
                         (coveredColumns.contains(j) || coveredRows.contains(i))->
                            Matrix[i][j] += min
                }

            //Subtract the minimum element
            // from every element in the matrix.
        min= Int.MAX_VALUE
            for (i in 0 until MatrixSize)
                for (j in 0 until MatrixSize)
                    if (Matrix[i][j]<min)
                        min=Matrix[i][j]
            for (i in 0 until MatrixSize)  //Subtract the minimum element
                for (j in 0 until MatrixSize)
                Matrix[i][j]-=min

        return Step3()
    }

    fun VerticalOrHorizontal(row: Int, column: Int)= (compareValues(countZeroesInColumn(column),countZeroesInRow(row)))
//        1->1 //Column>Row
//        -1->-1//Column<Row
//        0->0//Column==Row

    fun Optimization(row:Int):Boolean {
        if (row == assigmentRows.size) // If all rows were assigned a cell
            return true
        for (column in 0 until assigmentRows.size) {
            if (Matrix[row][column] == 0 && occupiedCols[column] == false) {
                assigmentRows[row] = column
                occupiedCols[column] = true // Mark the column as reserved
                if (Optimization(row+1))
                    return true
                occupiedCols[column]=false
            }
        }
        return false
    }
    fun Optimization():Boolean=Optimization(0)

}

