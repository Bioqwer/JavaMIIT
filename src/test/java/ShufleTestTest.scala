import java.io._

import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach, DiagrammedAssertions, FunSuite}

import Utils._

class ShufleTest extends FunSuite with BeforeAndAfterEach with BeforeAndAfterAll with DiagrammedAssertions {

  //generateFile("bigFile.txt",100000,100)
  def generateFile(fileName: String, numLines: Long, stringLength: Long) = {
    def randomAlphaNumericString(length: Long): String = {
      val chars = ('a' to 'z') ++ ('A' to 'Z') ++ ('0' to '9')
      randomStringFromCharList(length, chars)
    }

    def randomStringFromCharList(length: Long, chars: Seq[Char]): String = {
      val sb = new StringBuilder
      var i = 0L
      while (i < length) {
        val randomNum = util.Random.nextInt(chars.length)
        sb.append(chars(randomNum))
        i = i + 1
      }
      sb.toString
    }

    new File(fileName)

    using(new FileWriter(fileName)) { fileWriter =>
      var i = 0L
      while (i < numLines) {
        fileWriter.write(randomAlphaNumericString(stringLength))
        fileWriter.write(System.lineSeparator())
        i = i + 1
      }
    }
  }


  test("basic check") {
    //val sorter = new BigFilesSorter("bigFile.txt", 1000, 1, "result")
    //sorter.sort()
  }


}
