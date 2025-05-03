package com.cuatrucdao.k22411c_firstdegree;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText edtCoefficientA;
    EditText edtCoefficientB;
    TextView txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        addViews();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void addViews() {
        edtCoefficientA = findViewById(R.id.edtCoefficientA);
        edtCoefficientB = findViewById(R.id.edtCoefficientB);
        txtResult = findViewById(R.id.txtResult);
    }

    public void do_next(View view) {
        edtCoefficientA.setText("");
        edtCoefficientB.setText("");
        txtResult.setText("");
        edtCoefficientA.requestFocus();
    }

    public void do_exit(View view) {
        finish();
    }

    @SuppressLint("DefaultLocale")
    public void do_solution(View view) {
        String hsa = edtCoefficientA.getText().toString().trim();
        String hsb = edtCoefficientB.getText().toString().trim();

        // Error handling: empty input
        if (hsa.isEmpty() || hsb.isEmpty()) {
            Toast.makeText(this, "Please enter both coefficients", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double a = Double.parseDouble(hsa);
            double b = Double.parseDouble(hsb);

            if (a == 0 && b == 0) {
                txtResult.setText(getResources().getText(R.string.title_infinity));
            } else if (a == 0) {
                txtResult.setText(getResources().getText(R.string.title_no_solution));
            } else {
                double x = -b / a;
                txtResult.setText(String.format("x = %.2f", x)); // formatted result
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid number format", Toast.LENGTH_SHORT).show();
        }
    }

    // Method to change the app's language
    @SuppressLint("NonConstantResourceId")
    public void changeLanguage(View view) {
        String languageCode = "en"; // Default to English

        int id = view.getId();
        if (id == R.id.btnEnglish) {
            languageCode = "en";
        } else if (id == R.id.btnFrench) {
            languageCode = "fr";
        } else if (id == R.id.btnSpanish) {
            languageCode = "es";
        } else if (id == R.id.btnVietnamese) {
            languageCode = "vi";
        }

        // Create the Locale object with the selected language
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);

        Configuration config = new Configuration();  // ✅ declare config properly
        config.setLocale(locale);

        // Apply the new configuration
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());

        // Recreate the activity to apply changes
        recreate();

        Toast.makeText(this, "Language changed to: " + languageCode, Toast.LENGTH_SHORT).show();
    }
}
