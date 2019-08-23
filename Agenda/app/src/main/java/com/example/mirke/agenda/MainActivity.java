package com.example.mirke.agenda;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    EditText etNombre, etNumero, etDireccion;
    Button  btAgregar, btQuitar, btModificar;
    ListView lv;
    ArrayList<Contacto> contactos;
    Adaptador adaptador;
    Contacto contacto;
    DataBase dataBase;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNombre = (EditText)findViewById(R.id.etNombre);
        etNumero = (EditText)findViewById(R.id.etNumero);
        etDireccion = (EditText)findViewById(R.id.etDireccion);

        btAgregar = (Button)findViewById(R.id.btAgregar);
        btQuitar = (Button)findViewById(R.id.btQuitar);
        btModificar = (Button)findViewById(R.id.btModificar);

        dataBase = new DataBase(this,"Agenda",null,1);

        contactos = new ArrayList<Contacto>();

        adaptador = new Adaptador(contactos,this);

        lv = (ListView)findViewById(R.id.idListView);
        lv.setAdapter(adaptador);
        listado();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                contacto = contactos.get(position);

                etNombre.setText(contacto.getNombre());
                etNumero.setText(contacto.getNumero());
                etDireccion.setText(contacto.getDireccion());
            }
        });
        btAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = etNombre.getText().toString();
                String numero = etNumero.getText().toString();
                String direccion = etDireccion.getText().toString();

                if(TextUtils.isEmpty(etNombre.getText().toString()) || TextUtils.isEmpty(etNumero.getText().toString()))
                {
                    Toast.makeText(getApplicationContext(),"Nombre y Numero requeridos",Toast.LENGTH_LONG).show();
                }
                else {
                    agregar(new Contacto(nombre,numero,direccion));
                }
            }
        });

        btQuitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(etNombre.getText().toString()) || TextUtils.isEmpty(etNumero.getText().toString()))
                {
                    Toast.makeText(getApplicationContext(),"Seleccione un Contacto",Toast.LENGTH_LONG).show();
                }
                else {
                    quitar(contacto);
                }

            }
        }); 

        btModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newname = etNombre.getText().toString();
                String newnumero = etNumero.getText().toString();
                String newdir = etDireccion.getText().toString();

                int position = contactos.indexOf(contacto);
                if(TextUtils.isEmpty(etNombre.getText().toString()) || TextUtils.isEmpty(etNumero.getText().toString()))
                {
                    Toast.makeText(getApplicationContext(),"Seleccione un Contacto",Toast.LENGTH_LONG).show();
                }
                else {
                    modificar(new Contacto(newname,newnumero,newdir),position);
                }

            }
        });
    }


    public void agregar(Contacto contacto) {
        Operaciones operaciones = new Operaciones(dataBase,contacto);
        operaciones.addContacto();
        listado();

        etNombre.setText("");
        etDireccion.setText("");
        etNumero.setText("");



        Toast.makeText(getApplicationContext(),"Contacto agregado",Toast.LENGTH_LONG).show();
    }

    public void quitar(Contacto contacto) {
        Operaciones operaciones = new Operaciones(dataBase,contacto);
        operaciones.delContacto();
        listado();

        etNombre.setText("");
        etNumero.setText("");
        etDireccion.setText("");

        adaptador.notifyDataSetChanged();
    }


    public void modificar(Contacto contacto1, int position) {

        Operaciones operaciones = new Operaciones(dataBase,contacto1);
        int id = operaciones.getID(contacto);
        if(id == 0){
            Toast.makeText(this,"ERROR",Toast.LENGTH_SHORT).show();
        }
        else {
            operaciones.modificarContacto(id);
            listado();
        }

    }

    public void listado ()
    {
        Operaciones operaciones = new Operaciones(dataBase,null);
        List contactos1 = operaciones.listContacto();
        contactos.clear();
        for(int i = 0; i<contactos1.size();i++)
        {
            Contacto c = (Contacto)contactos1.get(i);
            contactos.add(c);
        }
        adaptador.notifyDataSetChanged();
    }
}
