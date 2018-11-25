object Utils {

  def using[T <: {def close()}, R](resource: T)(block: T => R): R = {
    try {
      block(resource)
    } finally {
      if (resource != null) {
        try {
          resource.close()
        } catch {
          case e: Exception => println(s"Can not close resource. $e")
        }
      }
    }
  }

}
