package com.fgamesalonslots

import com.ticktakto12.mytictaksimplegame1.AllMyPlayerWinningLines

class MyGameManager {

    private var cP = 1
    var p1Points = 0
    var p2Points = 0

    val cPMark: String
        get() {
            return if (cP == 1) "X" else "O"
        }

    private var _state = arrayOf( // 2D Array
        intArrayOf(0, 0, 0),
        intArrayOf(0, 0, 0),
        intArrayOf(0, 0, 0)
    )

    fun makeMove(position: MyGamePosition): AllMyPlayerWinningLines? {
        _state[position.row][position.column] = cP
        val winningLine = hasGameEnded()

        if (winningLine == null) {
            cP = 3 - cP
        } else {
            when (cP) {
                1 -> p1Points++
                2 -> p2Points++
            }
        }

        return winningLine
    }

    fun reset() {
        _state = arrayOf(
            intArrayOf(0, 0, 0),
            intArrayOf(0, 0, 0),
            intArrayOf(0, 0, 0)
        )
        cP = 1
    }

    private fun hasGameEnded(): AllMyPlayerWinningLines? {
        if (_state[0][0] == cP && _state[0][1] == cP && _state[0][2] == cP) {
            return AllMyPlayerWinningLines.ForROW_0
        } else if (_state[1][0] == cP && _state[1][1] == cP && _state[1][2] == cP) {
            return AllMyPlayerWinningLines.ForROW_1
        } else if (_state[2][0] == cP && _state[2][1] == cP && _state[2][2] == cP) {
            return AllMyPlayerWinningLines.ForROW_2
        } else if (_state[0][0] == cP && _state[1][0] == cP && _state[2][0] == cP) {
            return AllMyPlayerWinningLines.ForCOLUMN_0
        } else if (_state[0][1] == cP && _state[1][1] == cP && _state[2][1] == cP) {
            return AllMyPlayerWinningLines.ForCOLUMN_1
        } else if (_state[0][2] == cP && _state[1][2] == cP && _state[2][2] == cP) {
            return AllMyPlayerWinningLines.ForCOLUMN_2
        } else if (_state[0][0] == cP && _state[1][1] == cP && _state[2][2] == cP) {
            return AllMyPlayerWinningLines.ForDIAGONAL_LEFT
        } else if (_state[0][2] == cP && _state[1][1] == cP && _state[2][0] == cP) {
            return AllMyPlayerWinningLines.ForDIAGONAL_RIGHT
        }
        return null
    }

    private fun hasGameEndedV2(): Boolean {
        _state.forEach { row ->
            val hasWon = row.all { player -> player == cP }
            if (hasWon) return true
        }

        for (i: Int in _state.indices) {
            val hasWon = _state[i].all { player -> player == cP }
            if (hasWon) return true
        }

        for (i in _state.indices) {
            if (_state[i][i] != cP) return false
        }

        for (i in _state.size until 0) {
            if (_state[i][i] != cP) return false
        }

        return true
    }
}
