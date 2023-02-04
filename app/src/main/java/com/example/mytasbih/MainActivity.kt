package com.example.mytasbih

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.mytasbih.databinding.ActivityMainBinding

const val APP_PREFERENCES = "APP_PREFERENCES"
const val PREF_SOME_TEXT_VALUE = "0"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var preferences: SharedPreferences

    private fun hideSystemBars() {
        val windowInsetsControllerCompat =
            ViewCompat.getWindowInsetsController(window.decorView) ?: return
        windowInsetsControllerCompat.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        // Hide both the status bar and the navigation bar
        windowInsetsControllerCompat.hide(WindowInsetsCompat.Type.systemBars())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        hideSystemBars()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preferences = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)

        binding.tiykargiBtn.setText(preferences.getString((PREF_SOME_TEXT_VALUE), "0"))

        var currentNumber = preferences.getString(PREF_SOME_TEXT_VALUE, "0")!!.toInt()
        binding.all.text = "Barlig'i: ${currentNumber}"

        var vibrBtn = false
        var tritriBtn = false
        var devdevBtn = false
        var infinityBtn = true

        binding.apply {


            tiykargiBtn.setOnClickListener {

                if (vibrBtn == true){
                    val v = (getSystemService(Context.VIBRATOR_SERVICE) as Vibrator)

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        v.vibrate(VibrationEffect.createOneShot(20, VibrationEffect.DEFAULT_AMPLITUDE))
                    } else {
                        v.vibrate(20)
                    }
                }
                if (infinityBtn == true) {

                    currentNumber++
                    binding.all.text = "Barlig'i: ${currentNumber}"
                    tiykargiBtn.text = "$currentNumber"
                    preferences.edit()
                        .putString(PREF_SOME_TEXT_VALUE, "$currentNumber")
                        .apply()
                }

                if (tritriBtn == true) {

                    currentNumber++
                    binding.all.text = "Barlig'i: ${currentNumber}"
                    if (currentNumber % 33 == 0 && vibrBtn == true){
                        val v = (getSystemService(Context.VIBRATOR_SERVICE) as Vibrator)

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            v.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE))
                        } else {
                            v.vibrate(200)
                        }
                    }
                    tiykargiBtn.text = "${currentNumber % 33}"

                    preferences.edit()
                        .putString(PREF_SOME_TEXT_VALUE, "$currentNumber")
                        .apply()
                }

                if (devdevBtn == true) {

                    currentNumber++
                    binding.all.text = "Barlig'i: ${currentNumber}"
                    if (currentNumber % 99 == 0 && vibrBtn == true){
                        val v = (getSystemService(Context.VIBRATOR_SERVICE) as Vibrator)

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            v.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE))
                        } else {
                            v.vibrate(200)
                        }
                    }
                    tiykargiBtn.text = "${currentNumber % 99}"
                    preferences.edit()
                        .putString(PREF_SOME_TEXT_VALUE, "$currentNumber")
                        .apply()
                }



            }

            restart.setOnClickListener {

                currentNumber = 0
                tiykargiBtn.text = "$currentNumber"
                binding.all.text = "Barlig'i: ${currentNumber}"

                preferences.edit()
                    .putString(PREF_SOME_TEXT_VALUE, "$currentNumber")
                    .apply()
            }

            vibr.setOnClickListener {
                vibrBtn = !vibrBtn
            }

            tritri.setOnClickListener {
                currentNumber = 0
                tiykargiBtn.text = "$currentNumber"
                tritriBtn = true
                devdevBtn = false
                infinityBtn = false
            }

            devdev.setOnClickListener {
                currentNumber = 0
                tiykargiBtn.text = "$currentNumber"
                tritriBtn = false
                devdevBtn = true
                infinityBtn = false

            }

            infinity.setOnClickListener {
                currentNumber = 0
                tiykargiBtn.text = "$currentNumber"
                tritriBtn = false
                devdevBtn = false
                infinityBtn = true

            }
        }
    }
}
