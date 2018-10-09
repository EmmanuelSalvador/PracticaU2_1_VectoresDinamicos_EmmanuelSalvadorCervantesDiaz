package mx.edu.ittepic.practicau2_1_vectoresdinamicos_emmanuelsalvadorcervantesdiaz;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ListView lista;
    String[] listaItems, vector;
    TextView texto;
    LinearLayout layo;
    int contador=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lista=findViewById(R.id.listaTareas);
        layo=findViewById(R.id.layo);
        texto=findViewById(R.id.texto);
        listaItems = new String[20];
        vector = new String[20];
        for (int i=0; i<listaItems.length;i++) {
            listaItems[i] = "";
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ventanaCrear = new Intent (MainActivity.this, Main2Activity.class);
                startActivityForResult(ventanaCrear,1);
            }
        });


    }
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        int posicion = data.getIntExtra("pos", 10);
        String cadena = data.getStringExtra("cadena");
        if (resultCode != 10){
            guardarEnVector(posicion, cadena);
        }
        if(listaItems[posicion]==""){
            for(int i=0; i<20;i++){
                if(listaItems[i]==""){
                    contador++;
                }
            }
        }
        if(contador==20){
            Intent regresar = new Intent(MainActivity.this, MainActivity.class);
            startActivity(regresar);
        }
        contador=0;
    }

    private void guardarEnVector(int posicion, String cadena) {
        layo.removeView(texto);
        String datos[] = cadena.split("&");
        listaItems[posicion] = datos[0];
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,listaItems);
        lista.setAdapter(adaptador);
        vector[posicion] = cadena;
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String totalCadena = vector[position];
                if (listaItems[position]!=""){
                    Intent ventana3 = new Intent(MainActivity.this, Main3Activity.class);
                    ventana3.putExtra("cadena", totalCadena);
                    ventana3.putExtra("pos", position);
                    startActivityForResult(ventana3, 2);
                }
                else {
                    Toast.makeText(MainActivity.this,"Está vacío",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
