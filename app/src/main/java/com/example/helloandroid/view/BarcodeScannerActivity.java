package com.example.helloandroid.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.SparseArray;
import android.widget.Toast;

import com.example.helloandroid.R;
import com.example.helloandroid.view.interfaces.AddProductActivity;
import com.example.helloandroid.view.interfaces.BarcodeReaderListener;
import com.google.android.gms.vision.barcode.Barcode;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import info.androidhive.barcode.BarcodeReader;
/**
 *
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class BarcodeScannerActivity extends AppCompatActivity implements BarcodeReader.BarcodeReaderListener {
    private static final int PERMISSION_CALLBACK_CONSTANT = 101;
    private static final int REQUEST_PERMISSION_SETTING = 102;

    private boolean sentToSettings = false;
    private BarcodeReader mBarcodeReader;
    private SharedPreferences permissionStatus;
    private BarcodeReaderListener mListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_scanner);
        mBarcodeReader = (BarcodeReader) getSupportFragmentManager().findFragmentById(R.id.barcode_scanner);
        onScanned(null);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onScanned(Barcode barcode) {
        mBarcodeReader.playBeep();
        Toast.makeText(this, barcode != null && barcode.displayValue != null ? barcode.displayValue : "", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, AddProductActivity.class);
    }

    @Override
    public void onScannedMultiple(List<Barcode> barcodes) {

    }

    @Override
    public void onBitmapScanned(SparseArray<Barcode> sparseArray) {

    }

    @Override
    public void onScanError(String errorMessage) {

    }

    @Override
    public void onCameraPermissionDenied() {

    }
}
