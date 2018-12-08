import java.io._
import java.nio.file.Files

import Utils._

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer
import scala.reflect.io.Path
import scala.util.Try

class BigFilesSorter(fileNameInput: String, buffer: Int, numSymbolsForPartition: Int, resultFileName: String) {

  val mapBuffer = new mutable.HashMap[String, ArrayBuffer[String]]()
  val partitionInfo = new mutable.HashMap[String, ArrayBuffer[FilesInfos]]()

  var tempDir: String = _

  def sort(): Unit = {
    tempDir = Files.createTempDirectory("bigFilesSorter").toFile.getAbsolutePath
    map()
    mergeSort()
    writeSortedValues()
    deleteStuff
  }

  private def deleteStuff = {
    val path = Path(tempDir)
    Try(path.deleteRecursively())
  }

  /**
    * Map existing values to partitions, eventually write sorted values in partition part
    */
  def map(): Unit = {
    println("Start Map")
    using(new BufferedReader(new FileReader(fileNameInput))) { bufferedReader =>
      var current: String = null
      while ( {
        current = bufferedReader.readLine
        current != null
      }) {
        val partitionKey = current.take(numSymbolsForPartition).toLowerCase
        val arrayBuffer = mapBuffer
          .getOrElseUpdate(partitionKey, new ArrayBuffer[String](buffer + 1))
        arrayBuffer += current

        if (arrayBuffer.size >= buffer) {
          val sorted = arrayBuffer.sorted
          saveSortedFile(partitionKey, sorted)
          mapBuffer.put(partitionKey, new ArrayBuffer[String](buffer + 1))
        }
      }
    }
    mapBuffer.par.filter(x => x._2.nonEmpty)
      .foreach { case (k, v) =>
        saveSortedFile(k, v.sorted)
      }
    mapBuffer.clear()
    println("End Map")
  }

  /**
    * Check that directory exists or create new for temp results
    *
    */
  def saveSortedFile(partitionKey: String, sorted: ArrayBuffer[String]): Unit = {
    def checkDirectoriesAndGetCountFiles = {
      val partFolder = new File(s"$tempDir/$partitionKey")
      var size: Int = 0
      if (partFolder.exists()) {
        size = partFolder.listFiles().length
      } else {
        partFolder.mkdir()
      }
      size
    }

    val size: Int = checkDirectoriesAndGetCountFiles
    val fileNum = size + 1

    partitionInfo.getOrElseUpdate(partitionKey,
                                  new ArrayBuffer[FilesInfos]()) += FilesInfos(fileNum, sorted.head, sorted.last)
    val fileName = s"$tempDir/$partitionKey/$fileNum"
    using(new BufferedWriter(new FileWriter(fileName))) { bufferedWriter =>
      sorted.foreach { x =>
        bufferedWriter.write(x)
        bufferedWriter.newLine()
      }
    }
  }

  /**
    * Write 1 sorted file for each partition
    */
  def mergeSort(): Unit = {
    println("Start mergeSort")
    partitionInfo.foreach { case (k, info) =>
      val partResultFileName = s"$tempDir/$k.res"
      info.foreach { f =>
        val value = s"$tempDir/$k/${f.num}"
        f.bufferedReader = new BufferedReader(new FileReader(value))
        f.isEmpty = false
        f.currentValue = f.bufferedReader.readLine()
      }

      using(new PrintWriter(partResultFileName)) { bufferedWriter =>
        var nonEmptySorted = info.filterNot(_.isEmpty)
          .sortBy(_.currentValue)
        while (nonEmptySorted.headOption.nonEmpty) {
          val nextFileValue = nonEmptySorted.drop(1).headOption.map(_.currentValue)
          val currentInfo = nonEmptySorted.head

          bufferedWriter.println(currentInfo.currentValue)

          var cur: String = currentInfo.readAndGetValue
          while (cur != null && nextFileValue.forall(x => cur <= x)) {
            bufferedWriter.println(cur)
            cur = currentInfo.readAndGetValue
          }
          nonEmptySorted = nonEmptySorted.filterNot(_.isEmpty)
            .sortBy(_.currentValue)
        }
      }
    }
    println("End mergeSort")
  }

  /**
    * Write result file from all partitions
    */
  def writeSortedValues(): Unit = {
    using(new PrintWriter(resultFileName)) { bufferedWriter =>
      partitionInfo.keys.toList.sorted.foreach { x =>
        val partResultFileName = s"$tempDir/$x.res"
        using(new BufferedReader(new FileReader(partResultFileName))) { bufferedReader =>
          var curLine: String = bufferedReader.readLine()
          while (curLine != null) {
            bufferedWriter.println(curLine)
            curLine = bufferedReader.readLine()
          }
        }
      }
    }
  }

  case class FilesInfos(num: Int, min: String, max: String) {
    var currentValue: String = _
    var isEmpty: Boolean = _
    var bufferedReader: BufferedReader = _

    def readAndGetValue: String = {
      currentValue = bufferedReader.readLine()
      if (currentValue == null) {
        isEmpty = true
        try {
          bufferedReader.close()
        } catch {
          case fe: Throwable => fe.printStackTrace()
        }
      }
      currentValue
    }
  }

}