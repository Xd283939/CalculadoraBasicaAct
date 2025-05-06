package com.example.calculadora
import android.content.Intent
import android.icu.text.DecimalFormat
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    val suma="+"
    val resta="+"
    val multiplicacion="*"
    val division="/"
    val porcentaje="%"
    var primerNumber:Double=Double.NaN
     var SegundoNumber:Double= Double.NaN
    var operacionActual =""
    lateinit var tvResultado:TextView//lateinit indica que la variable será inicializada más adelante
    lateinit var tvResultado2:TextView//

    lateinit var  formatDecimal:DecimalFormat




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        formatDecimal=DecimalFormat("#.##########")
        tvResultado =findViewById(R.id.tvResultado)
        tvResultado2=findViewById(R.id.tvResultado2)


        val btnChronometer: Button = findViewById(R.id.btnChronometer)
        btnChronometer.setOnClickListener {
            val intent = Intent(this, ChronometerActivity::class.java)
            startActivity(intent)
        }
        }

    fun  cambiarOperador(b: View){
        if(tvResultado.text.isNotEmpty() || primerNumber.toString()!="NaN" ){


            calcular()
        val boton:Button=b as Button
     if(boton.text.toString().trim()=="÷"){
         operacionActual="/"


    }else if ( boton.text.toString().trim()=="X"){
         operacionActual ="*"
     }else{
         operacionActual=boton.text.toString().trim()
     }
        tvResultado2.text = formatDecimal.format(primerNumber)+operacionActual
        tvResultado.text = ""

    }
    }
    fun calcular(){
       try{
           if(primerNumber.toString()!="NaN") {
               if(tvResultado.text.toString().isEmpty()){
                   tvResultado.text=tvResultado2.text.toString()
               }
               SegundoNumber=tvResultado.text.toString().toDouble()
               tvResultado.text=""

               when(operacionActual){
                   "+"-> primerNumber= (primerNumber+SegundoNumber)
                   "-"-> primerNumber= (primerNumber-SegundoNumber)
                   "*"-> primerNumber= (primerNumber*SegundoNumber)
                   "/"-> primerNumber= (primerNumber/SegundoNumber)
                   "%"-> primerNumber= (primerNumber%SegundoNumber)

               }
           }else{
               primerNumber=tvResultado.text.toString().toDouble()
           }
       }catch(e: Exception){

       }
       }
    fun seleccionarNumero(b:View){
        val boton:Button=b as Button

        tvResultado.text = tvResultado.text.toString()+  boton.text.toString()

    }
    fun igual(b:View){
        calcular()
        tvResultado2.text = formatDecimal.format(primerNumber)
        //primerNumber = Double.NaN
        operacionActual =""
    }
    fun borrar(b:View){
        val boton:Button = b as Button
        if(boton.text.toString().trim()=="C"){
            if(tvResultado.text.toString().isNotEmpty()){
              var datosActuales: CharSequence =tvResultado.text.toString() as CharSequence
                tvResultado.text=datosActuales.subSequence(0,datosActuales.length-1)
            }else {
                primerNumber = Double.NaN
                SegundoNumber = Double.NaN
                tvResultado.text = ""
                tvResultado2.text = ""
            }

        }else if (boton.text.toString().trim()=="CA"){
            primerNumber =Double.NaN
            SegundoNumber=Double.NaN
            tvResultado.text = ""
            tvResultado2.text = ""
        }
    }
       }











