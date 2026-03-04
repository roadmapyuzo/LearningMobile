package com.example.learningmobile;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FormularyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulary);

        String landlord = getIntent().getStringExtra("Landlord");

        Button generatebtn = findViewById(R.id.generatebtn);

        EditText etName = findViewById(R.id.name);
        EditText etCPF = findViewById(R.id.cpf);
        EditText etAddress = findViewById(R.id.address);
        EditText etStartDate = findViewById(R.id.startdate);
        EditText etEndDate = findViewById(R.id.enddate);
        EditText etRent = findViewById(R.id.rent);
        EditText etDate = findViewById(R.id.date);
        EditText etPaymentDay = findViewById(R.id.paymentday);

        generatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = etName.getText().toString();
                String cpf = etCPF.getText().toString();
                String address = etAddress.getText().toString();
                String startDate = etStartDate.getText().toString();
                String endDate = etEndDate.getText().toString();
                String rent = etRent.getText().toString();
                String date = etDate.getText().toString();
                String paymentDay = etPaymentDay.getText().toString();

                AssetManager manager = getAssets();
                InputStream stream = null;

                try {

                    String archive = "modelo_"+landlord;
                    stream = manager.open(archive+".docx");

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(FormularyActivity.this, "Erro ao abrir modelo", Toast.LENGTH_SHORT).show();
                    return;
                }

                File outfile = new File(getExternalFilesDir(null), "contrato_final.docx");

                try {

                    XWPFDocument doc = new XWPFDocument(stream);

                    for (XWPFParagraph p : doc.getParagraphs()) {
                        for (XWPFRun r : p.getRuns()) {
                            if  (r.isItalic()) {

                                String text = r.getText(0);
                                text = text.trim().replace("\u200B", "");
                                if (text != null) {
                                    text.replace("NOME", name)
                                            .replace("CPF", cpf)
                                            .replace("ENDERECO", address)
                                            .replace("INICIO", startDate)
                                            .replace("FIM", endDate)
                                            .replace("ALUGUEL", rent)
                                            .replace("PAGAMENTO", paymentDay)
                                            .replace("DATA", date)
                                    ;
                                    r.setText(text, 0);
                                }
                            }
                        }
                    }

                    FileOutputStream fos = new FileOutputStream(outfile);
                    doc.write(fos);
                    doc.close();
                    fos.close();
                    stream.close();

                    Toast.makeText(FormularyActivity.this,
                            "Contrato gerado em: " + outfile.getAbsolutePath(),
                            Toast.LENGTH_LONG).show();


                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(FormularyActivity.this,
                            "Erro ao gerar contrato: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }


            }
        });

    }
}