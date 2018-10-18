package com.kotlin.cursos.progresbar

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.SeekBar
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var estado = 0
    var incremento = 0
    lateinit var slider:SeekBar
    lateinit var valor:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {
            Thread(Runnable {
              while(estado < 100){
                  try{
                      incremento +=10
                      Thread.sleep(500)
                  } catch (e: InterruptedException){
                      e.printStackTrace()
                  }
                  estado = incremento
                  progressBar.progress = estado
              }
            }).start()
        }

        slider = seekBar
        valor = textView

        slider.setOnSeekBarChangeListener(object :SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                valor.text = "Cambiando" + progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                valor.text = "Inicio" + slider.progress
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                if (slider.progress == 0) valor.text = "Inicio" + slider.progress
            }
        })
    }
}
