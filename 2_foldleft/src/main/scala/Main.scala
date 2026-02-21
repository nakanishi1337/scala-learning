@main def hello(): Unit =
  val strings = List("Hello", "world", "foldLeft")
  val totalLength = strings.foldLeft(0)((total, str) => total + str.length)
  println(s"文字列リスト: $strings")
  println(s"合計長: $totalLength")
