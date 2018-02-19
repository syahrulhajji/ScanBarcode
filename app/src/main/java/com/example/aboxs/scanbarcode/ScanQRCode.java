package com.example.aboxs.scanbarcode;


import android.os.Bundle;
import android.util.Log;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScanQRCode extends Fragment implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;
    private FrameLayout frameScan;
    private TextView txtResult;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_scan_qrcode, container, false);
        frameScan = (FrameLayout)view.findViewById(R.id.frame_scan);
        txtResult = (TextView) view.findViewById(R.id.tv_result);
        try{
            mScannerView = new ZXingScannerView(getContext());   // Programmatically initialize the scanner view

            mScannerView.setLayoutParams(new FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.MATCH_PARENT
            ));
            mScannerView.addView(mScannerView);
        }catch (Exception e){
            Toast.makeText(getContext(), "Please Allow Permission in Apps Info" , Toast.LENGTH_SHORT).show();
        }

        return view;
    }
    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }
    @Override
    public void onResume(){
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
        txtResult.setText("");
    }

    @Override
    public void handleResult(Result rawResult) {
        try{
            Log.e("handler", rawResult.getText()); // Prints scan results
            Log.e("handler", rawResult.getBarcodeFormat().toString()); // Prints the scan format (qrcode)
            String hasil_deskrip = rawResult.getText();

            txtResult.setText(hasil_deskrip);
        }catch (Exception e){
            txtResult.setText("Data Not Found");
        }
    }

}
