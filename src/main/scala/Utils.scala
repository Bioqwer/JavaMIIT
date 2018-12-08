import java.io.{File, FileWriter, PrintWriter}

object Utils {

  def using[T <: {def close()}, R](resource: T)(block: T => R): R = {
    var exception: Throwable = null
    try {
      block(resource)
    } catch {
      case e: Exception =>
        exception = e
        throw e
    } finally {
      try {
        resource.close()
      } catch {
        case fe: Throwable =>
          if (exception != null) {
            exception.addSuppressed(fe)
          } else {
            throw fe
          }
      }
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
