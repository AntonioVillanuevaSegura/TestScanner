#Test de un scanner implementado en android kotlin

El scanner esta basado en zwing https://github.com/journeyapps/zxing-android-embedded

necesita estas dependencias  build.gradle app

// Config for SDK 24+

repositories {
    mavenCentral()
}

dependencies {
    implementation 'com.journeyapps:zxing-android-embedded:4.3.0'
}

---------------------------------------------------------------------------------------------------------------
Utiliza ScanContract ( IntentIntegrator no esta recomendado )  ejemplo de zxing
startActivityForResult can still be used via IntentIntegrator, but that is not recommended anymore.

// Register the launcher and result handler
private final ActivityResultLauncher<ScanOptions> barcodeLauncher = registerForActivityResult(new ScanContract(),
        result -> {
            if(result.getContents() == null) {
                Toast.makeText(MyActivity.this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(MyActivity.this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
            }
        });

// Launch
public void onButtonClick(View view) {
    barcodeLauncher.launch(new ScanOptions());
}

---------------------------------------------------------------------------------------------------------------
En mi caso ha quedado asi 
       
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



---------------------------------------------------------------------------------------------------------------
Se puede personalizar  ejemplo
Customize options:

ScanOptions options = new ScanOptions();
options.setDesiredBarcodeFormats(ScanOptions.ONE_D_CODE_TYPES);
options.setPrompt("Scan a barcode");
options.setCameraId(0);  // Use a specific camera of the device
options.setBeepEnabled(false);
options.setBarcodeImageEnabled(true);
barcodeLauncher.launch(options)
---------------------------------------------------------------------------------------------------------------

Cambio de la orientacion en mi ejemplo el AndroidManifest.xml
        <activity
            android:name="com.journeyapps.barcodescanner.CaptureActivity"
            android:screenOrientation="portrait"
            tools:replace="android:screenOrientation"
            android:stateNotNeeded="true"/>

---------------------------------------------------------------------------------------------------------------

