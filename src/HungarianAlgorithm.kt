import java.io.File

class HungarianAlgorithm(private val MatrixOriginal:Array<IntArray>) {
    private val matrixSize = MatrixOriginal.size
    private var Matrix=Array(matrixSize, { i-> MatrixOriginal[i].clone()})
    private var counter=1
    private val assignmentRows= IntArray(matrixSize)// Index of the column selected by every row (The final result)
    private val occupiedCols=BooleanArray(matrixSize)//Verify that all column are occupied, used in the tryToAssign step
    fun StepByStep(): IntArray {
        if ( tryToAssign(0))
            return assignmentRows
        Step1()
        if ( tryToAssign(0))
            return assignmentRows
        Step2()
        tryToAssign(0)
        Step3()
        return assignmentRows
    }
    fun StepByStep(file:File): IntArray {
        if ( tryToAssign(0,file)) {
            writeDataToFileAssignment(file)
            return assignmentRows
        }
        file.appendText("\nStep1")
        Step1()
        writeDataToFile(file)


        if ( tryToAssign(0,file)) {
            writeDataToFileAssignment(file)
            return assignmentRows
        }
        file.appendText("\nStep2")
        Step2()
        writeDataToFile(file)

       tryToAssign(0,file)
            Step3(file)

        writeDataToFileAssignment(file)
        return assignmentRows
    }
    private fun writeDataToFile(file:File){
        file.appendText("\n")
        for(i in 0 until Matrix.size) {
            for (j in 0 until Matrix.size)
                file.appendText("${Matrix[i][j]}\t")
            file.appendText("\n")
        }
    }
    private fun writeDataToFileAssignment(file:File){
        file.appendText("\n")
        file.appendText("Trying to assign:\n")
        for(i in 0 until Matrix.size) {
            for (j in 0 until Matrix.size)
                if(j== assignmentRows[i])
                    file.appendText("[${Matrix[i][j]}]\t")
                else
                    file.appendText("${Matrix[i][j]}\t")
            file.appendText("\n")
        }
    }
    private fun writeDataToFileCovered(file:File, coveredColumns:MutableList<Int>, coveredRows:MutableList<Int>){
        file.appendText("\nCovering lines with zeroes:\n")
        file.appendText("Covered rows:$coveredRows\nCovered columns:$coveredColumns")
        writeDataToFile(file)
//        for(i in 0 until Matrix.size) {
//            for (j in 0 until Matrix.size)
//                if(coveredColumns.contains(j)||coveredRows.contains(i))
//                    file.appendText("[${Matrix[i][j]}]\t\t\t")
//                else
//                    file.appendText("${Matrix[i][j]}\t\t\t")
//            file.appendText("\n")
//        }
    }

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
    private fun Step3(file:File) {
        val coveredColumns = mutableListOf<Int>()
        val coveredRows = mutableListOf<Int>()
        var min: Int
        var VerHor: Int

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

        writeDataToFileCovered(file,coveredColumns,coveredRows)

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
        counter++
        writeDataToFile(file)
        if (tryToAssign(0,file))
            return

        return Step3(file)
    }
    private fun Step3() {
        val coveredColumns = mutableListOf<Int>()
        val coveredRows = mutableListOf<Int>()
        var min: Int
        var VerHor: Int

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
        counter++

        if (tryToAssign(0))
            return

        return Step3()
    }
    private fun verticalOrHorizontal(row: Int, column: Int)= (compareValues(countZeroesInColumn(column),countZeroesInRow(row))) //check where is more zeroes per line
//        1->1 //Column>Row
//        -1->-1//Column<Row
//        0->0//Column==Row

    private fun tryToAssign(row:Int):Boolean { //"row" - number of row to assign
        if (row == assignmentRows.size) // If all rows were assigned a cell; assignmentRows.size==Matrix.size
            return true
        for (column in 0 until assignmentRows.size) {
            if (Matrix[row][column] == 0 && occupiedCols[column] == false) {
                assignmentRows[row] = column// Assign the current row the current column cell
                occupiedCols[column] = true // Mark the column as reserved
                if (tryToAssign(row+1)) // If the next rows were assigned successfully a cell from a unique column, return true
                    return true
                occupiedCols[column]=false // If the next rows were not able to get a cell, go back and try for the previous rows another cell from another column
            }
        }
        return false
    }
    private fun tryToAssign(row:Int,file:File):Boolean { //"row" - number of row to assign
        if (row == assignmentRows.size) // If all rows were assigned a cell; assignmentRows.size==Matrix.size
            return true
        for (column in 0 until assignmentRows.size) {
            if (Matrix[row][column] == 0 && occupiedCols[column] == false) {
                assignmentRows[row] = column// Assign the current row the current column cell
                occupiedCols[column] = true // Mark the column as reserved
                if (tryToAssign(row+1)) // If the next rows were assigned successfully a cell from a unique column, return true
                    return true
                occupiedCols[column]=false // If the next rows were not able to get a cell, go back and try for the previous rows another cell from another column
            }
        }
        writeDataToFileAssignment(file)
        return false
    }
}

