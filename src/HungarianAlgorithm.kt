import java.io.File

class HungarianAlgorithm(private val Matrix:Array<IntArray>) {
    private val matrixSize = Matrix.size
    private var counter=0
    private val assignmentRows= IntArray(matrixSize)// Index of the column selected by every row (The final result)
    private val occupiedCols=BooleanArray(matrixSize)//Verify that all column are occupied, used in the optimization step
    fun StepByStep(): IntArray {
        Step1()
        if ( optimization(0))
            return assignmentRows
        Step2()
        if ( optimization(0))
            return assignmentRows
        Step3()
        return assignmentRows
    }
    fun StepByStep(Name: String): IntArray {
        print("Enter name of input file:")
        val file = File("${readLine()}.txt")
        file.writeText(Name)

        file.appendText("\nStep1")
        if ( optimization(0))
            return assignmentRows
        Step1()

        file.appendText("\nStep1")
        writeDataToFile(file)
        if ( optimization(0))
            return assignmentRows
        Step2()
        file.appendText("\nStep2")
        writeDataToFile(file)
        if ( optimization(0))
            return assignmentRows
        while (!optimization(0))
                    Step3(file)

        return assignmentRows
    }
    fun writeDataToFile(file:File){
        file.appendText("\n")
        for(i in 0 until Matrix.size) {
            for (j in 0 until Matrix.size)
                file.appendText("${Matrix[i][j]}\t")
            file.appendText("\n")
        }
    }
//    fun Checker(): Boolean {
//        var flag = 0
//        for (j in 0 until matrixSize) {
//            for (i in 0 until matrixSize) {
//                if (Matrix[i][j] == 0) {
//                    flag++
//                    break
//                }
//            }
//        }
//        return true
//    }


    //Reduce the rows by subtracting
    // the minimum value of each row from that row.
    private fun Step1() {
        var min:Int
        for (i in 0 until matrixSize) {
            min = Matrix[i][0]
            for (j in 1 until matrixSize)
                if (Matrix[i][j] < min)
                    min = Matrix[i][j]
            for (j in 0 until matrixSize)
                Matrix[i][j]-=min
        }
    }


//If there are columns without a zero,
    // reduce the columns by subtracting the minimum value of each
    // column from that column.
private fun Step2() {
    var min:Int
    for (j in 0 until matrixSize) {
        min = Matrix[0][j]
        for (i in 1 until matrixSize)
            if (Matrix[i][j] < min)
                min = Matrix[i][j]
        for (i in 0 until matrixSize)
            Matrix[i][j]-=min
    }
    }

    private fun countZeroesInRow(i:Int)= Matrix[i].count { it==0 }

    private fun countZeroesInColumn(j: Int):Int{
        var result=0
        for(i in 0 until matrixSize)
            if(Matrix[i][j]==0)
                result++
        return result
    }
    private fun Step3() {
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
        for(row in 0 until matrixSize)
            loop@ for (column in 0 until matrixSize)
                if (Matrix[row][column]==0) {
                    VerHor=verticalOrHorizontal(row,column)
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

        if(counter==matrixSize)
            return
        counter++


        //Add the minimum uncovered element
        // to every covered element.
        // If an element is covered twice,
        // add the minimum element to it twice.
        min= Int.MAX_VALUE
        for (i in 0 until matrixSize)
            for (j in 0 until matrixSize) {
                if (!((coveredColumns.contains(j) || coveredRows.contains(i))))
                    if (Matrix[i][j] < min)
                        min = Matrix[i][j]
            }


        for (i in 0 until matrixSize)
                for (j in 0 until matrixSize)
                    when {
                        (coveredColumns.contains(j) && coveredRows.contains(i))-> //if zero covered twice
                            Matrix[i][j] += min*2
                         (coveredColumns.contains(j) || coveredRows.contains(i))->
                            Matrix[i][j] += min
                }

            //Subtract the minimum element
            // from every element in the matrix.
        min= Int.MAX_VALUE
            for (i in 0 until matrixSize)
                for (j in 0 until matrixSize)
                    if (Matrix[i][j]<min)
                        min=Matrix[i][j]
            for (i in 0 until matrixSize)  //Subtract the minimum element
                for (j in 0 until matrixSize)
                Matrix[i][j]-=min

        return Step3()
    }
    private fun Step3(file:File) {
        val coveredColumns = mutableListOf<Int>()
        val coveredRows = mutableListOf<Int>()
        var min: Int
        var VerHor: Int? =null

        //counting zeroes
        for(row in 0 until matrixSize)
            loop@ for (column in 0 until matrixSize)
            if (Matrix[row][column]==0 &&!(coveredColumns.contains(column) || coveredRows.contains(row))) {
                VerHor=verticalOrHorizontal(row,column)
                if(VerHor>0)
                    coveredColumns.add(column)
                else
                    coveredRows.add(row)
                }

        if ( optimization(0))
            return



        //Add the minimum uncovered element
        // to every covered element.
        // If an element is covered twice,
        // add the minimum element to it twice.
        min= Int.MAX_VALUE
        for (i in 0 until matrixSize)
            for (j in 0 until matrixSize) {
                if (!((coveredColumns.contains(j) || coveredRows.contains(i))))
                    if (Matrix[i][j] < min)
                        min = Matrix[i][j]
            }


        for (i in 0 until matrixSize)
            for (j in 0 until matrixSize)
                when {
                    (coveredColumns.contains(j) && coveredRows.contains(i))-> //if zero covered twice
                        Matrix[i][j] += min*2
                    (coveredColumns.contains(j) || coveredRows.contains(i))->
                        Matrix[i][j] += min
                }

        //Subtract the minimum element
        // from every element in the matrix.
        min= Int.MAX_VALUE
        for (i in 0 until matrixSize)
            for (j in 0 until matrixSize)
                if (Matrix[i][j]<min)
                    min=Matrix[i][j]
        for (i in 0 until matrixSize)  //Subtract the minimum element
            for (j in 0 until matrixSize)
                Matrix[i][j]-=min


        file.appendText("\nStep3.$counter")
        writeDataToFile(file)


        return Step3(file)
    }
    private fun verticalOrHorizontal(row: Int, column: Int)= (compareValues(countZeroesInColumn(column),countZeroesInRow(row))) //check where is more zeroes per line
//        1->1 //Column>Row
//        -1->-1//Column<Row
//        0->0//Column==Row

    private fun optimization(row:Int):Boolean { //"row" - number of row to assign
        if (row == assignmentRows.size) // If all rows were assigned a cell
            return true
        for (column in 0 until assignmentRows.size) {
            if (Matrix[row][column] == 0 && occupiedCols[column] == false) {
                assignmentRows[row] = column// Assign the current row the current column cell
                occupiedCols[column] = true // Mark the column as reserved
                if (optimization(row+1)) // If the next rows were assigned successfully a cell from a unique column, return true
                    return true
                occupiedCols[column]=false // If the next rows were not able to get a cell, go back and try for the previous rows another cell from another column
            }
        }
        return false
    }
}

