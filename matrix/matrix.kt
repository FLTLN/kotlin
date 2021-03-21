class ComplexNumber {
    val re: Double
    val im: Double
  
    constructor(re: Double, im: Double) {
      this.re = re
      this.im = im
    }
    constructor(re: Double) {
      this.re = re
      this.im = 0.0
    }
    constructor() {
      this.re = 0.0
      this.im = 0.0
    }

    override fun toString(): String {
        if(im != 0.0) {
            return re.toString() + " + " + im.toString() + " i"
        } else {
            return re.toString()
        }
    }

    public operator fun plus(a : ComplexNumber) : ComplexNumber {
        return ComplexNumber(this.re + a.re, this.im + a.im)
    }

    public operator fun minus(a : ComplexNumber) : ComplexNumber {
        return ComplexNumber(this.re - a.re, this.im - a.im)
    }

    public operator fun times(a : ComplexNumber) : ComplexNumber {
        return ComplexNumber(this.re * a.re - this.im * a.im, this.im * a.re + this.re * a.im)
    }

    public operator fun times(a : Double) : ComplexNumber {
        return ComplexNumber(this.re * a, this.im * a)
    }
}

class Matrix(val content : Array<ComplexNumber>, val ncol : Int, val nrow : Int) 
{
    override fun toString() : String {
        var res : String = ""
        for(i in 0..(this.nrow - 1)){
            for(j in 0..(this.ncol - 1)){
                res = res + this.content[j + i * this.ncol] + ", ";
            }
            res = res + "\n";
        }
        return res;
    }

    public operator fun plus(a : Matrix) : Matrix {

        if(a.ncol != this.ncol || a.nrow != this.nrow) {
            throw Exception("Uncompatible matrix size")
        }

        val array = Array(this.ncol * this.nrow, {ComplexNumber()})
        // не самым оптимальным образом сделано, но я так и не понял как правильно создать неинициализированный массив

        for(i in 0..(this.nrow - 1)){
            for(j in 0..(this.ncol - 1)){
                array[j + i * this.ncol] = this.content[j + i * this.ncol] + a.content[j + i * this.ncol]
            }
        }

        return Matrix(array, this.ncol, this.nrow)
    }
}

fun main() {
    val complexReIm = ComplexNumber(12.0, 45.0)
    val complexRe = ComplexNumber(80.0)

    println("Два обьекта класса ComplexNumber.\n")
    println("Обьект, инициализированный с действительной и мнимой частью:")
    println(complexReIm)
    println("Обьект, инициализированный с только действительной частью:")
    println(complexRe)

    val content1 = arrayOf(complexReIm, complexReIm, complexReIm, complexRe, complexRe, complexRe)
    val content2 = arrayOf(complexRe, complexRe, complexRe, complexReIm, complexReIm, complexReIm)

    val matrix1 = Matrix(content1, 2, 3) 
    val matrix2 = Matrix(content2, 2, 3)

    println("\nДва обьекта класса Matrix:\n")
    println(matrix1)
    println(matrix2)

    println("Результат сложения матриц:\n")
    println(matrix1 + matrix2)
}
