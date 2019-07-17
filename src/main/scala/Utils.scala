import java.io.{File, FileWriter, PrintWriter}

import scala.util.control.NonFatal

object Utils {

  /**
    * https://medium.com/@dkomanov/scala-try-with-resources-735baad0fd7d
    */
  def using[T <: AutoCloseable, V](r: => T)(f: T => V): V = {
    val resource: T = r
    require(resource != null, "resource is null")
    var exception: Throwable = null
    try {
      f(resource)
    } catch {
      case NonFatal(e) =>
        exception = e
        throw e
    } finally {
      closeAndAddSuppressed(exception, resource)
    }
  }

  private def closeAndAddSuppressed(e: Throwable,
                                    resource: AutoCloseable): Unit = {
    if (e != null) {
      try {
        resource.close()
      } catch {
        case NonFatal(suppressed) =>
          e.addSuppressed(suppressed)
      }
    } else {
      resource.close()
    }
  }

  //generateFile("bigFile.txt",100000,100)
  def generateFile(fileName: String, numLines: Long, stringLength: Long): Unit = {
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

    using(new PrintWriter(fileName)) { fileWriter =>
      var i = 0L
      while (i < numLines) {
        fileWriter.write(randomAlphaNumericString(stringLength))
        fileWriter.write(System.lineSeparator())
        i = i + 1
      }
    }
  }

}
