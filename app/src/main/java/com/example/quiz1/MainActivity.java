package com.example.quiz1;

import androidx.appcompat.app.AppCompatActivity;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etKayu, etEbony, etJati;
    private Button btPesan;
    private RadioGroup radioGroup;
    private RadioButton rbPerunggu, rbPerak, rbEmas, radioButton;
    private TextView tvTitle, tvPesan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTitle = findViewById(R.id.tvTitle);
        tvPesan = findViewById(R.id.tvPesan);
        etKayu = findViewById(R.id.etKayu);
        etEbony = findViewById(R.id.etEbony);
        etJati = findViewById(R.id.etJati);
        btPesan = findViewById(R.id.btPesan);
        radioGroup = findViewById(R.id.radioGroup);
        rbPerunggu = findViewById(R.id.rbPerunggu);
        rbPerak = findViewById(R.id.rbPerak);
        rbEmas = findViewById(R.id.rbEmas);


        btPesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int radioId = radioGroup.getCheckedRadioButtonId();

                radioButton = findViewById(radioId);

                int oas = 1989123;
                int aae = 8676981;
                int mp3 = 28999999;

                String inputKayu = etKayu.getText() != null ? etKayu.getText().toString() : "0";
                String inputEbony = etEbony.getText() != null ? etEbony.getText().toString() : "0";
                String inputJati = etJati.getText() != null ? etJati.getText().toString() : "0";

                int OAS = !inputKayu.isEmpty() ? Integer.parseInt(inputKayu) : 0;
                int AAE = !inputEbony.isEmpty() ? Integer.parseInt(inputEbony) : 0;
                int MP3 = !inputJati.isEmpty() ? Integer.parseInt(inputJati) : 0;

                if (OAS < 0 || AAE < 0 || MP3 < 0) {
                    tvPesan.setText("Info: Anda Tidak Memesan Apapun.");
                    return;
                }

                int totalHarga = (oas * OAS) + (aae * AAE) + (mp3 * MP3);
                BigDecimal diskon = BigDecimal.ZERO;
                double potonganPersen = 0;

                if (totalHarga >= 10000000) {
                    diskon = new BigDecimal("100000");
                    if (rbPerunggu.isChecked()) {
                        potonganPersen = 0.02;
                    } else if (rbPerak.isChecked()) {
                        potonganPersen = 0.05;
                    } else if (rbEmas.isChecked()) {
                        potonganPersen = 0.10;
                    }
                } else {
                    if (rbPerunggu.isChecked()) {
                        potonganPersen = 0.02;
                    } else if (rbPerak.isChecked()) {
                        potonganPersen = 0.05;
                    } else if (rbEmas.isChecked()) {
                        potonganPersen = 0.10;
                    }
                }

                BigDecimal potongan = BigDecimal.valueOf(totalHarga).multiply(BigDecimal.valueOf(potonganPersen));

                BigDecimal totalAkhir = BigDecimal.valueOf(totalHarga)
                        .subtract(diskon)
                        .subtract(potongan);
                NumberFormat formatter = NumberFormat.getInstance(Locale.getDefault());
                DecimalFormat decimalFormatter = (DecimalFormat)formatter;
                decimalFormatter.applyPattern("#,###");


                StringBuilder notaBuilder = new StringBuilder();
                notaBuilder.append("===== STRUK BELANJA =====\n");
                notaBuilder.append("Total Harga: Rp " + decimalFormatter.format(totalHarga) + "\n");
                notaBuilder.append("Diskon: Rp " + decimalFormatter.format(diskon) + "\n");
                notaBuilder.append("Potongan: Rp " + decimalFormatter.format(potongan) + "\n\n");
                notaBuilder.append("Total Akhir: Rp " + decimalFormatter.format(totalAkhir) + "\n");

                tvPesan.setText(notaBuilder.toString());

            }
        });
    }

    public void checkButton(View v) {
        int radioId = radioGroup.getCheckedRadioButtonId();

        radioButton = findViewById(radioId);

        Toast.makeText(this, "Silahkan memilih Ponsel: " + radioButton.getText(), Toast.LENGTH_SHORT).show();

    }

}
