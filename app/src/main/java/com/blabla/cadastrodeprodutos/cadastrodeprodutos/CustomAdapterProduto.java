package com.blabla.cadastrodeprodutos.cadastrodeprodutos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.blabla.cadastrodeprodutos.cadastrodeprodutos.models.Produto;

import java.util.ArrayList;
import java.util.List;


public class CustomAdapterProduto extends ArrayAdapter<Produto> implements View.OnClickListener {

    private List<Produto>mProdutos;

    private Context mContext;

    private OnProdutoListener mListener;

    DatabaseHelper DB_methods;


    public CustomAdapterProduto(List<Produto> data, Context context, OnProdutoListener listener)
    {

        super(context, R.layout.row_list, data);
        mProdutos = data;
        mContext = context;
        mListener = listener;

    }

    @Override
    public void onClick(View v) {

        Produto produto = getItem((Integer) v.getTag());
        //Toast.makeText(mContext, produto.nome, Toast.LENGTH_SHORT).show();
        mListener.onProdutoItemClick(produto);
    }

    // View lookup cache
    private static class ViewHolder
    {
        TextView textNome;
        TextView textQuantidade;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Produto produto = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

//        if (convertView == null) {
//
//            viewHolder = new ViewHolder();
//            LayoutInflater inflater = LayoutInflater.from(getContext());
//            convertView = inflater.inflate(R.layout.row_list, parent, false);
//            viewHolder.textNome = (TextView) convertView.findViewById(R.id.textProdutoView);
//            viewHolder.textQuantidade = (TextView) convertView.findViewById(R.id.textQDTView);
//
//
//            result=convertView;
//
//            convertView.setTag(viewHolder);
//
//        } else {
//
//            viewHolder = (ViewHolder) convertView.getTag();
//            result=convertView;
//        }


        viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_list, parent, false);
            viewHolder.textNome = (TextView) convertView.findViewById(R.id.textProdutoView);
            viewHolder.textQuantidade = (TextView) convertView.findViewById(R.id.textQDTView);

        viewHolder.textNome.setText(produto.nome);
        viewHolder.textQuantidade.setText(produto.qtde);
        //result.setOnClickListener(this);
        //result.setTag(position);
        // Return the completed view to render on screen
        return convertView;
    }



    public void update(List<Produto> produtos) {
        mProdutos = produtos;
        notifyDataSetChanged();
    }



    public void AtualizaDados()
    {
        notifyDataSetChanged();
    }


    public interface OnProdutoListener
    {
        void onProdutoItemClick(Produto produto);
    }


}
