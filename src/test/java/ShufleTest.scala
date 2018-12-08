import java.io._

import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach, DiagrammedAssertions, FunSuite}
import Utils._
import org.apache.commons.io.FileUtils

class ShufleTest extends FunSuite with BeforeAndAfterEach with BeforeAndAfterAll with DiagrammedAssertions {

  def basicCheckAndDeleteResult(path: String, resultFileName: String) = {
    var prevVal: String = null
    using(new BufferedReader(new FileReader(resultFileName))) { fr =>
      val current = fr.readLine()
      if (prevVal == null)
        prevVal = current
      assert(prevVal <= current)
    }


    assert(FileUtils.readLines(new File(path)).size() ==
             FileUtils.readLines(new File(resultFileName)).size())

    //FileUtils.deleteQuietly(new File(resultFileName))
  }

  test("basic check with 1 file in each partition") {
    //Utils.generateFile("littleWith1partition.txt",100,20)
    val path = getPathToResource("littleWith1partition.txt")
    val resultFileName = "resultLittleWith1partition.txt"
    val sorter = new BigFilesSorter(path, 10, 1, resultFileName)
    sorter.sort()

    basicCheckAndDeleteResult(path, resultFileName)
  }

  test("basic check with big file") {
    //Utils.generateFile("file10000.txt",10000,20)
    val path = getPathToResource("file10000.txt")
    val resultFileName = "resultfile10000.txt"
    val sorter = new BigFilesSorter(path, 100, 1, resultFileName)
    sorter.sort()

    basicCheckAndDeleteResult(path, resultFileName)
  }


  private def getPathToResource(fileName:String) = {
    this.getClass.getClassLoader.getResource(fileName).getPath
  }
}
