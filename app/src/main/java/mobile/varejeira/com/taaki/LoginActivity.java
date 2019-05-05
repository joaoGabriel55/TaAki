package mobile.varejeira.com.taaki;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private Button entrar;
    private Button criarConta;

    private EditText username;
    private EditText senha;

    private TextView continuar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);

        this.entrar = findViewById(R.id.entrar);
        this.criarConta = findViewById(R.id.criarConta);

        this.username = findViewById(R.id.nomeusuario);
        this.senha = findViewById(R.id.senhausuario);

        this.continuar = findViewById(R.id.continuar);

        // Caso de uso: Autenticar no Cliente
        this.entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        // Caso de uso: Criar conta Cliente
        this.criarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        // caso de uso: Acessar Aplicativo
        this.continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        
    }
}
