import java.io.File
import kotlin.collections.HashSet
import kotlin.collections.ArrayList
import kotlin.math.abs
import java.util.Random
import java.io.FileWriter
//import kotlinx.coroutines.*

val inputFile = "words.txt"
val outputFile = "user-words.txt"
val minLength = 7

fun main() 
{
    val allWords: HashSet<String> = HashSet()
    val longWords:  ArrayList<String> = ArrayList()

    File(inputFile).forEachLine{
        allWords.add(it)
        if(it.length >= minLength) {
            longWords.add(it)
        }
    }

    val random = Random()
    val randomWordIndex = abs(random.nextInt()) % longWords.size

    val longWord = longWords[randomWordIndex]
    println(longWord)

    val userWords = ArrayList<String>()

    do {
        val word = readLine()?.trim()
        if (word != null && word.isNotEmpty()) {
            userWords.add(word)
        }
    } while (word != null && word.isNotEmpty())
    
    //val saveAndCheckTask = CoroutineScope(Dispatchers.IO).launch {
        
        var score = 0
        val longWordLetters: HashSet<Char> = longWord.toHashSet()
        val newLineEscapeSequence = System.getProperty("line.separator")
        File(outputFile).writeText("Длинное слово - " + longWord + newLineEscapeSequence + newLineEscapeSequence)

        val fw = FileWriter(outputFile, true)
        fw.write("Ваши слова:" + newLineEscapeSequence + newLineEscapeSequence)

        for (word in userWords) {
            
            fw.write(word + " - ")
            var anyExtraChars = false
            word.forEach { char ->
                if (!longWordLetters.contains(char)) {
                    anyExtraChars = true
                }
            }

            if (anyExtraChars) {
                fw.write("не засчитано, использованы лишние буквы" + newLineEscapeSequence)
            } else if (allWords.contains(word)) {
                score += word.length
                fw.write("засчтитано, + " + word.length.toString() + " баллов" + newLineEscapeSequence)
            } else {
                fw.write("не засчитано, нет такого слова" + newLineEscapeSequence)
            }
        }

        fw.write(newLineEscapeSequence + "итоговый счёт - " + score.toString() + " баллов" + newLineEscapeSequence)
        fw.close()
        println(newLineEscapeSequence + "итоговый счёт - " + score.toString() + " баллов" + newLineEscapeSequence)
    //}

    //saveAndCheckTask.join()
}
