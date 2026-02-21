import scala.io._
import scala.concurrent._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

object PageLoader {
  def getPageSize(url: String) = {
    try {
      Source.fromURL(url).mkString.length
    } catch {
      case e: Exception => -1
    }
  }
}

object Main {
  val urls = List(
    "https://www.scala-lang.org",
    "https://www.mozilla.org",
    "https://www.github.com",
    "https://www.rust-lang.org"
  )

  def timeMethod(method: => Unit) = {
    val Start = System.nanoTime()
    method
    val end = System.nanoTime()
    println("Method took " + (end - Start) / 1e9 + " seconds")
  }

  def getPageSizeSequentially() = {
    urls.foreach { url =>
      println(s"Size for $url: ${PageLoader.getPageSize(url)}")
    }
  }

  def getPageSizeConcurrently() = {
    val futures = urls.map { url =>
      Future {
        (url, PageLoader.getPageSize(url))
      }
    }

    val allResults = Future.sequence(futures)
    Await.result(allResults, 30.seconds).foreach { case (url, size) =>
      println(s"Size for $url: $size")
    }
  }

  def main(args: Array[String]): Unit = {
    println("Sequential run:")
    timeMethod(getPageSizeSequentially())
    println("\nConcurrent run:")
    timeMethod(getPageSizeConcurrently())
  }
}
