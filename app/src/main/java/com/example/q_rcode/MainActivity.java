package com.example.q_rcode;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.WHITE;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

public class MainActivity extends AppCompatActivity {
    ImageView imgview;
    Button btnQR;
   EditText textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgview=findViewById(R.id.qr_img);
        textView=findViewById(R.id.inputText);
        btnQR=findViewById(R.id.gnrtbtn);

        btnQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(textView.getText())){
                    String text=textView.getText().toString();
                    Bitmap qrcode = createBitmap(text);
                    imgview.setImageBitmap(qrcode);
                }
            }
        });
    }

    private Bitmap createBitmap(String text) {
        BitMatrix result=null;
        try{
            result=new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE,300,300,null);

        }catch(WriterException e){
            e.printStackTrace();
        }
        int width=result.getWidth();
        int height=result.getHeight();
        int[] pixels=new int[width*height];
        for(int i=0;i<height;i++){
            int offset=i*width;
            for(int j=0;j<width;j++){
                pixels[offset+j]=result.get(j,i)?BLACK:WHITE;
            }
        }
        Bitmap myBitmap=Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888);
        myBitmap.setPixels(pixels,0,width,0,0,width,height);
        return myBitmap;
    }
}