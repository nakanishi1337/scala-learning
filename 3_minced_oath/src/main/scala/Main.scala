trait Censor:
  val censorMap: Map[String, String] =
    scala.io.Source.fromResource("censorWords.txt")
      .getLines()
      .map { line =>
        val parts = line.split(",")
        (parts(0).trim, parts(1).trim)
      }
      .toMap
  
  def censor(text: String): String =
    censorMap.foldLeft(text) { (acc, pair) =>
      acc.replaceAll("(?i)" + pair._1, pair._2)
    }

class ContentFilter extends Censor

@main def hello(): Unit =
  val filter = ContentFilter()
  val originalText = "Oh shoot! What the darn is this?"
  val censoredText = filter.censor(originalText)
  println(s"Original: $originalText")
  println(s"Censored: $censoredText")
