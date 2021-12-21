//Test Scanner Android Kotlin Antonio Villanueva Segura
//https://stackoverflow.com/questions/34983201/change-qr-scanner-orientation-with-zxing-in-android-studio
//https://github.com/journeyapps/zxing-android-embedded

//https://cursokotlin.com/zxing-leer-qr-codigos-de-barras-en-kotlin/

package com.example.testscanner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.testscanner.databinding.ActivityMainBinding
import com.google.zxing.integration.android.IntentIntegrator
import com.journeyapps.barcodescanner.ScanOptions

import android.widget.Toast
import androidx.activity.result.ActivityResultCallback

import com.journeyapps.barcodescanner.ScanContract

import androidx.activity.result.ActivityResultLauncher
import com.journeyapps.barcodescanner.ScanIntentResult


class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)



        //Si pulsa boton arranca scanner
        binding.button.setOnClickListener{
            //lanzaScanner.launch(ScanOptions())


            val options = ScanOptions()
            //options.setDesiredBarcodeFormats(ScanOptions.ONE_D_CODE_TYPES)
            options.setPrompt("Scan a barcode")
            //options.setCameraId(0) // Use a specific camera of the device

            options.setBeepEnabled(true)
            options.setBarcodeImageEnabled(true)
            options.setOrientationLocked(false)
            options.setTimeout(25000)
            options.setTorchEnabled(true)

            lanzaScanner.launch(options)



        }
    }

    // Registre el lanzador y el controlador de resultados
    private val lanzaScanner = registerForActivityResult(ScanContract())
    {    result: ScanIntentResult ->
        if (result.contents == null) { }
        else {
            //Muestra scan en EditView @+id/textViewReadText
            binding.textViewReadText.setText(result.contents)//Muestra el valor scaneado en viewText
        }
    }


}

