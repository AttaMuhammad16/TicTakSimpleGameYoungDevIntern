package com.ticktakto12.mytictaksimplegame1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.fgamesalonslots.MyGameManager
import com.fgamesalonslots.MyGamePosition

class MyGameActivity : AppCompatActivity() {

    private lateinit var MygameManager: MyGameManager
    private lateinit var one: TextView
    private lateinit var two: TextView
    private lateinit var three: TextView
    private lateinit var four: TextView
    private lateinit var five: TextView
    private lateinit var six: TextView
    private lateinit var seven: TextView
    private lateinit var eight: TextView
    private lateinit var nine: TextView
    private lateinit var startNewGameBtn: Button
    private lateinit var p1Points: TextView
    private lateinit var p2Points: TextView
    private lateinit var aboutBtn:Button
    private lateinit var shareBtn:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_game)
        MygameManager = MyGameManager()
        window.statusBarColor= ContextCompat.getColor(this,R.color.st)

        one = findViewById(R.id._one1)
        two = findViewById(R.id._two2)
        three = findViewById(R.id._three3)
        four = findViewById(R.id._four4)
        five = findViewById(R.id.five)
        six = findViewById(R.id._six6)
        seven = findViewById(R.id._seven7)
        eight = findViewById(R.id.eight)
        nine = findViewById(R.id._nine9)
        startNewGameBtn = findViewById(R.id.sng_btn)
        p1Points = findViewById(R.id.p1_Score)
        p2Points = findViewById(R.id.p2_Score)
        aboutBtn = findViewById(R.id.aboutBtn)
        shareBtn = findViewById(R.id.shareBtn)

        one.setOnClickListener { myOnBBBoxClicked(one, MyGamePosition(0, 0)) }
        two.setOnClickListener { myOnBBBoxClicked(two, MyGamePosition(0, 1)) }
        three.setOnClickListener { myOnBBBoxClicked(three, MyGamePosition(0, 2)) }
        four.setOnClickListener { myOnBBBoxClicked(four, MyGamePosition(1, 0)) }
        five.setOnClickListener { myOnBBBoxClicked(five, MyGamePosition(1, 1)) }
        six.setOnClickListener { myOnBBBoxClicked(six, MyGamePosition(1, 2)) }
        seven.setOnClickListener { myOnBBBoxClicked(seven, MyGamePosition(2, 0)) }
        eight.setOnClickListener { myOnBBBoxClicked(eight, MyGamePosition(2, 1)) }
        nine.setOnClickListener { myOnBBBoxClicked(nine, MyGamePosition(2, 2)) }

        startNewGameBtn.setOnClickListener {
            startNewGameBtn.visibility = View.GONE
            MygameManager.reset()
            myRRResetboxes()
        }
        MyUUupdatePoints()
        aboutBtn.setOnClickListener {
            startActivity(Intent(this@MyGameActivity,AboutActivity::class.java))
        }
        shareBtn.setOnClickListener {
            val appPackageName = "com.ticktakto12.mytictaksimplegame1"
            val appPlayStoreLink = "https://play.google.com/store/apps/details?id=$appPackageName"
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Check out my app!")
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Download my app on the Play Store: $appPlayStoreLink")
            startActivity(Intent.createChooser(shareIntent, "Share via"))
        }
    }

    private fun MyUUupdatePoints() {
        p1Points.text = "Player X Points: ${MygameManager.p1Points}"
        p2Points.text = "Player O Points: ${MygameManager.p2Points}"
    }


    private fun myRRResetboxes() {
        one.text = ""
        two.text = ""
        three.text = ""
        four.text = ""
        five.text = ""
        six.text = ""
        seven.text = ""
        eight.text = ""
        nine.text = ""
        one.background = null
        two.background = null
        three.background = null
        four.background = null
        five.background = null
        six.background = null
        seven.background = null
        eight.background = null
        nine.background = null
        one.isEnabled = true
        two.isEnabled = true
        three.isEnabled = true
        four.isEnabled = true
        five.isEnabled = true
        six.isEnabled = true
        seven.isEnabled = true
        eight.isEnabled = true
        nine.isEnabled = true
    }

    private fun myOnBBBoxClicked(_Mbox: TextView, _Mposition: MyGamePosition) {
        if (_Mbox.text.isEmpty()) {
            _Mbox.text = MygameManager.cPMark
            val _MtdwinningLine = MygameManager.makeMove(_Mposition)
            if (_MtdwinningLine != null) {
                MyUUupdatePoints()
                myDBodDDisableBoxes()
                startNewGameBtn.visibility = View.VISIBLE
                showWinnerWinnerCheckDinner(_MtdwinningLine)
            }
        }
    }
    private fun myDBodDDisableBoxes() {
        one.isEnabled = false
        two.isEnabled = false
        three.isEnabled = false
        four.isEnabled = false
        five.isEnabled = false
        six.isEnabled = false
        seven.isEnabled = false
        eight.isEnabled = false
        nine.isEnabled = false
    }

    private fun showWinnerWinnerCheckDinner(_mywinningLine: AllMyPlayerWinningLines) {
        val (_wwhwinningBoxes, _Nfdbackground) = when (_mywinningLine) {
            AllMyPlayerWinningLines.ForROW_0 -> Pair(listOf(one, two, three), R.drawable.horizontal_line)
            AllMyPlayerWinningLines.ForROW_1 -> Pair(listOf(four, five, six), R.drawable.horizontal_line)
            AllMyPlayerWinningLines.ForROW_2 -> Pair(listOf(seven, eight, nine), R.drawable.horizontal_line)
            AllMyPlayerWinningLines.ForCOLUMN_0 -> Pair(listOf(one, four, seven), R.drawable.vertical_line)
            AllMyPlayerWinningLines.ForCOLUMN_1 -> Pair(listOf(two, five, eight), R.drawable.vertical_line)
            AllMyPlayerWinningLines.ForCOLUMN_2 -> Pair(listOf(three, six, nine), R.drawable.vertical_line)
            AllMyPlayerWinningLines.ForDIAGONAL_LEFT -> Pair(listOf(one, five, nine), R.drawable.left_diagonal_line)
            AllMyPlayerWinningLines.ForDIAGONAL_RIGHT -> Pair(listOf(three, five, seven), R.drawable.right_diagonal_line)
        }
        _wwhwinningBoxes.forEach { box ->
            box.background = ContextCompat.getDrawable(GameActivity@ this, _Nfdbackground)
        }
    }
}
