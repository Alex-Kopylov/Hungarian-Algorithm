import java.io.File

class Matrix(private val Matrix:Array<IntArray>, private val Name:String, private val file: File?) {
   private var maxOrMinAssignment: Boolean? = null

    fun getName()=this.Name
    init {
        if (file!=null) {
            file.writeText("Name:$Name\n")
            file.appendText("\nOriginal matrix\n")
            for(i in 0 until Matrix.size) {
                for (j in 0 until Matrix.size)
                    file.appendText("${Matrix[i][j]}\t")
                file.appendText("\n")
            }
        }

    }
    private fun hungarianAlgorithmParameters(): IntArray {
        println("Would you like to trace it (type YES)")
        val trace=
                when(readLine()){
                    "YES"-> true
                    else -> false
                }
        println("Would you like to find MAXIMUM possible (type YES)")
        maxOrMinAssignment=
                when(readLine()){
                    "YES"-> true
                    else -> false
                }
        return when{
            (trace)->
                HungarianAlgorithm(Matrix, maxOrMinAssignment!!).StepByStep(file!!)
            else->
                HungarianAlgorithm(Matrix, maxOrMinAssignment!!).StepByStep()
        }
    }
    private val hungarianAssignment =hungarianAlgorithmParameters()

    val assigmentSum=getSum()
    private fun getSum(): Int {
        var sum = 0
        for (i in 0 until Matrix.size)
            for (j in 0 until Matrix.size)
                if (j == hungarianAssignment[i])
                    sum += Matrix[i][j]
            return sum
    }
        fun printMatrix() {
            println("Name:$Name")
            for (i in 0 until Matrix.size) {
                for (j in 0 until Matrix.size)
                    print("${Matrix[i][j]}\t")
                println()
            }
        }

        fun printHungarian() {

            println("Hungarian Algorithm Assignment:")
            for (i in 0 until Matrix.size) {
                for (j in 0 until Matrix.size)
                    if (j == hungarianAssignment[i])
                        print("[${Matrix[i][j]}]\t")
                    else
                        print("${Matrix[i][j]}\t")
                println()
            }
            if (maxOrMinAssignment!!)
                println("Maximum possible cost:$assigmentSum")
            else
                println("Minimum possible cost:$assigmentSum")
        }

        fun writeHungarianInFile() {
            if (file != null) {
                file.appendText("\nHungarian Algorithm Assignment:\n")
                for (i in 0 until Matrix.size) {
                    for (j in 0 until Matrix.size)
                        if (j == hungarianAssignment[i])
                            file.appendText("[${Matrix[i][j]}]\t")
                        else
                            file.appendText("${Matrix[i][j]}\t")
                    file.appendText("\n")
                }
                if (maxOrMinAssignment!!)
                    file.appendText("Maximum possible cost:$assigmentSum")
                else
                    file.appendText("Minimum possible cost:$assigmentSum")

            }

        }
    }

