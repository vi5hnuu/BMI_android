package com.vi5hnu.bmi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val calculate_btn=findViewById<AppCompatButton>(R.id.calculate_btn)
        val name_inp=findViewById<EditText>(R.id.name_inp)
        val height_inp=findViewById<EditText>(R.id.height_inp)
        val weight_inp=findViewById<EditText>(R.id.weight_inp)
        calculate_btn.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                if(!validate(name_inp,height_inp,weight_inp)){
                    Toast.makeText(this@MainActivity,"Please fill in valid data.",Toast.LENGTH_LONG).show()
                    return
                }
                val name= name_inp.text.toString()
                val height= (height_inp.text.toString()).toDouble()*0.3048
                val weight=(weight_inp.text.toString()).toDouble()

                /////
                Toast.makeText(this@MainActivity,getResultString(name,height,weight),Toast.LENGTH_LONG).show()
            }
        })
    }
    fun validate(name:EditText,height:EditText,weight:EditText):Boolean{
        if(name.text.isBlank() || height.text.isEmpty() || weight.text.isEmpty()){
            return false
        }else{
            return true
        }
    }
    fun getResultString(name:String,height: Double,weight:Double):String{
        val result=String.format("%.3f",weight/(height*height)).toDouble()
        val res=when{
            result<18.5 -> "Under weight"
            18.5<=result && result<=24.9 -> "Normal"
            25<=result && result<=29.9 -> "Over Weight"
            30<=result && result<=34.9 -> "Obseity (Class 1)"
            35<=result && result<=39.9 -> "Obseity (Class 2)"
            else->"Extreme Obesity"
        }
        return "Hello, ${name} your BMI is ${result} : ${res}"
    }
}