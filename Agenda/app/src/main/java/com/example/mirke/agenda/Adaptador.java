package com.example.mirke.agenda;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Adaptador extends BaseAdapter{
    ArrayList<Contacto> contactos;
    Context contexto;

    public Adaptador(ArrayList<Contacto> contactos,Context contexto) {
        this.contactos = contactos;
        this.contexto = contexto;
    }

    @Override
    public int getCount() {

        return contactos.size();
    }

    @Override
    public Object getItem(int position) {

        return contactos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {


        Contacto itemContacto = (Contacto)getItem(position);
        convertView = LayoutInflater.from(contexto).inflate(R.layout.layout_formato_contacto,null);

        TextView tvNombre = (TextView)convertView.findViewById(R.id.tv1Nombre);
        TextView tvNumero = (TextView)convertView.findViewById(R.id.tv1Numero);
        TextView tvDireccion = (TextView)convertView.findViewById(R.id.tv1Direccion);



        tvNombre.setText(itemContacto.nombre);
        tvNumero.setText(itemContacto.numero);
        tvDireccion.setText(itemContacto.direccion);

        return convertView;
    }
}
