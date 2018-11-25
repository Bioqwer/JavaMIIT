import java.io._

import Utils._

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer
import scala.reflect.io.Path
import scala.util.Try

class BigFilesSorter(fileNameInput: String, buffer: Int, numSimbolsForPartition: Int, resultFileName: String) {

  val partitionsTable = new mutable.HashMap[String, ArrayBuffer[String]]()
  val partitionInfo = new mutable.HashMap[String, ArrayBuffer[FilesInfos]]()

  def sort(): Unit = {

    val path = Path(resultFileName)
    Try(path.deleteRecursively())

    map()
    mergeSort()
    writeSortedValues()

    //Try(path.deleteRecursively())
  }

  def map(): Unit = {
    println("Start Map")
    using(new FileReader(fileNameInput)) { fileReader =>
      using(new BufferedReader(fileReader)) { bufferedReader =>
        var current: String = null
        while ( {
          current = bufferedReader.readLine
          current != null
        }) {
          val partitionKey = current.take(numSimbolsForPartition).toLowerCase
          val arrayBuffer = partitionsTable
            .getOrElseUpdate(partitionKey, new ArrayBuffer[String](buffer + 1))
          arrayBuffer += current
          if (arrayBuffer.size >= buffer) {
            val sorted = arrayBuffer.sorted
            saveSortedFile(partitionKey, sorted)
            partitionsTable.put(partitionKey, new ArrayBuffer[String](buffer + 1))
          }
        }

      }
    }
    partitionsTable.par.filter(x => x._2.nonEmpty)
      .foreach { case (k, v) =>
        saveSortedFile(k, v.sorted)
      }
    partitionsTable.clear()
    println("End Map")

  }

  def saveSortedFile(partitionKey: String, sorted: ArrayBuffer[String]): Unit = {
    val resFolder = new File(s"$resultFileName")
    if (!resFolder.exists()) {
      resFolder.mkdir()
    }
    val partFolder = new File(s"$resultFileName\\$partitionKey")
    println(partFolder.getAbsolutePath)
    var size: Int = 0
    if (partFolder.exists()) {
      size = partFolder.listFiles().length
    } else {
      partFolder.mkdir()
    }

    val min = sorted.head.take(numSimbolsForPartition + 3)
    val max = sorted.last.take(numSimbolsForPartition + 3)
    val fileNum = size + 1

    partitionInfo.getOrElseUpdate(partitionKey,
                                  new ArrayBuffer[FilesInfos]()) += FilesInfos(fileNum, min, max)
    val fileName = s"$resultFileName\\$partitionKey\\${fileNum}_${min}_$max"

    println(s"should write $fileName ${sorted.size}")

    new File(fileName).createNewFile()
    using(new FileWriter(fileName)) { fileWriter =>
      using(new BufferedWriter(fileWriter)) { bufferedWriter =>
        sorted.foreach { x =>
          bufferedWriter.write(x)
          bufferedWriter.write(System.lineSeparator())
        }
      }
    }
  }

  def mergeSort(): Unit = {
    println("Start mergeSort")
    partitionInfo.par.foreach { case (k, info) =>
      val partResultFileName = s"$resultFileName\\$k.res"
      println(s"Start for $partResultFileName")
      new File(partResultFileName).createNewFile()
      using(new FileWriter(partResultFileName)) { fileWriter =>
        using(new BufferedWriter(fileWriter)) { bufferedWriter =>
          if (info.size > 1) {
            info.foreach { f =>
              val value = s"$resultFileName\\$k\\${f.num}_${f.min}_${f.max}"
              f.bufferedReader = new BufferedReader(new FileReader(value))
              f.isEmpty = false
              f.currentValue = f.bufferedReader.readLine()
            }
            while (info.exists(x => !x.isEmpty)) {
              val sorted = info.filterNot(_.isEmpty)
                .sortBy(_.currentValue)
              if (sorted.size == 1) {
                val last = sorted.head
                while ( {
                  last.currentValue != null
                }) {
                  bufferedWriter.write(last.currentValue)
                  bufferedWriter.write(System.lineSeparator())
                  last.currentValue = last.bufferedReader.readLine()
                  if (last.currentValue == null) {
                    last.isEmpty = true
                  }
                }
              } else {
                val first = sorted(0)
                val second = sorted(1)
                while ( {
                  first.currentValue != null && first.currentValue < second.currentValue
                }) {
                  bufferedWriter.write(first.currentValue)
                  bufferedWriter.write(System.lineSeparator())
                  first.currentValue = first.bufferedReader.readLine()
                  if (first.currentValue == null) {
                    first.isEmpty = true
                  }
                }
              }
            }
            info.foreach(_.bufferedReader.close())
          } else {
            val sorted = info.filterNot(_.isEmpty)
            if (sorted.size == 1) {
              var curLine: String = null
              while ( {
                curLine = sorted.head.bufferedReader.readLine
                curLine != null
              }) {
                bufferedWriter.write(curLine)
                bufferedWriter.write(System.lineSeparator())
              }
              sorted.head.bufferedReader.close()
            }
          }
        }
      }
      println(s"End for $partResultFileName")
    }
    println("End mergeSort")
  }

  def writeSortedValues(): Unit = {

    using(new FileWriter(resultFileName + ".txt")) { fileWriter =>
      using(new BufferedWriter(fileWriter)) { bufferedWriter =>
        partitionInfo.keys.toList.sorted.foreach { x =>
          val partResultFileName = s"$resultFileName\\$x.res"
          using(new FileReader(partResultFileName)) { fileReader: FileReader =>
            using(new BufferedReader(fileReader)) { bufferedReader =>
              var curLine: String = null
              while ( {
                curLine = bufferedReader.readLine()
                curLine != null
              }) {
                bufferedWriter.write(curLine)
                bufferedWriter.write(System.lineSeparator())
              }
            }
          }
        }
      }
    }
  }

  case class FilesInfos(num: Int, min: String, max: String) {
    var currentValue: String = _
    var isEmpty: Boolean = _
    var bufferedReader: BufferedReader = _
  }

}

