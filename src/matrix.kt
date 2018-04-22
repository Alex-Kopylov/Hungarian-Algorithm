import java.io.File

class MatrixClass(private val Matrix:Array<IntArray>, private val Name:String, private val file: File?) {
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
        println("Would you like to trace it (type YES)")
    }
    private var hungarianAssignment =
        when (readLine()) {
            "YES" ->
                HungarianAlgorithm(Matrix).StepByStep(file!!)
            else ->
                HungarianAlgorithm(Matrix).StepByStep()
        }
        fun printMatrix(){
            println("Name:$Name")
            for(i in 0 until Matrix.size){
                for (j in 0 until Matrix.size)
                    print("${Matrix[i][j]}\t")
                print("\n")
            }
        }
        fun printHungarian(){
            println("Hungarian Algorithm Assignment:")
            for(i in 0 until Matrix.size){
                for (j in 0 until Matrix.size)
                    if (j== hungarianAssignment[i])
                        print("[${Matrix[i][j]}]\t")
                    else
                        print("${Matrix[i][j]}\t")
                print("\n")
            }
        }
        fun getName()=Name
    fun writeHungarianInFile()
    {
        if (file!=null)
        {
           file.appendText("\nHungarian Algorithm Assignment:\n")
            for(i in 0 until Matrix.size) {
                for (j in 0 until Matrix.size)
                    if(j== hungarianAssignment[i])
                        file.appendText("[${Matrix[i][j]}]\t")
                    else
                        file.appendText("${Matrix[i][j]}\t")
                file.appendText("\n")
            }
        }
    }

    }
