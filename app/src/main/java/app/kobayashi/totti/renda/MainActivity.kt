package app.kobayashi.totti.renda

import android.os.Bundle
import android.os.CountDownTimer
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import app.kobayashi.totti.renda.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater).apply {setContentView(this.root)}
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var tapCount: Int = 0

        var second = 10

        val timer: CountDownTimer = object : CountDownTimer(10000,1000){
            override fun onFinish() {
                binding.startButton.isVisible = true
                binding.tapButton.backgroundTintList =
                    ContextCompat.getColorStateList(this@MainActivity,R.color.gray)
                second = 10
                tapCount = 0
            }

            override fun onTick(millisUntilFinished: Long) {
                binding.tapButton.backgroundTintList =
                    ContextCompat.getColorStateList(this@MainActivity,R.color.pink)
                second = second -1

                binding.secondText.text =second.toString()
            }
        }

        binding.startButton.setOnClickListener{
            binding.countText.text = tapCount.toString()

            binding.startButton.isVisible = false

            timer.start()
        }

        binding.tapButton.setOnClickListener{
            if(second <10) {
                tapCount = tapCount + 1

                binding.countText.text = tapCount.toString()
            }

        }

    }
}