// 三目並べの結果を表す型
enum GameResult:
  case Winner(player: Char)
  case Draw
  case Ongoing

// 三目並べの判定関数
// board: 3x3の配列（'X', 'O', または ' 'を含む）
def judgeOX(board: Array[Array[Char]]): GameResult =
  // 勝者チェック
  if checkWinner(board, 'X') then GameResult.Winner('X')
  else if checkWinner(board, 'O') then GameResult.Winner('O')
  // 引き分けチェック（全てのマスが埋まっているか）
  else if isBoardFull(board) then GameResult.Draw
  // どちらでもない場合は勝者未定
  else GameResult.Ongoing

// プレイヤーが勝っているかチェック
def checkWinner(board: Array[Array[Char]], player: Char): Boolean =
  // 行のチェック
  board.exists(row => row.forall(_ == player)) ||
  // 列のチェック
  (0 until 3).exists(col => (0 until 3).forall(row => board(row)(col) == player)) ||
  // 斜めのチェック（左上→右下）
  (0 until 3).forall(i => board(i)(i) == player) ||
  // 斜めのチェック（右上→左下）
  (0 until 3).forall(i => board(i)(2 - i) == player)

// ボード全体が埋まっているかチェック
def isBoardFull(board: Array[Array[Char]]): Boolean =
  board.forall(row => row.forall(_ != ' '))

@main def hello(): Unit =
  // 使用例：引き分け
  val boardDraw: Array[Array[Char]] = Array(
    Array('X', 'O', 'X'),
    Array('X', 'O', 'O'),
    Array('O', 'X', 'X')
  )
  
  // 使用例：X が勝利
  val boardWinnerX: Array[Array[Char]] = Array(
    Array('X', 'X', 'X'),
    Array('O', 'O', ' '),
    Array(' ', ' ', ' ')
  )
  
  // 使用例：O が勝利（列）
  val boardWinnerO: Array[Array[Char]] = Array(
    Array('X', 'O', 'X'),
    Array('X', 'O', ' '),
    Array(' ', 'O', ' ')
  )
  
  // 使用例：ゲーム進行中
  val boardOngoing: Array[Array[Char]] = Array(
    Array('X', 'O', ' '),
    Array('O', 'X', ' '),
    Array(' ', ' ', ' ')
  )
  
  println(s"引き分け: ${judgeOX(boardDraw)}")      // Draw
  println(s"X勝利: ${judgeOX(boardWinnerX)}")      // Winner(X)
  println(s"O勝利: ${judgeOX(boardWinnerO)}")      // Winner(O)
  println(s"進行中: ${judgeOX(boardOngoing)}")     // Ongoing
