package com.blabla.cadastrodeprodutos.cadastrodeprodutos;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blabla.cadastrodeprodutos.cadastrodeprodutos.models.Produto;

import java.util.List;

public class CustomAdapterRecycler extends RecyclerView.Adapter<CustomAdapterRecycler.ProdutoViewHolder> {


    private OnProdutoListener mListener;

    private Context mContext;
    private List<Produto> mProdutos;


    public CustomAdapterRecycler(List<Produto> data, Context context, MainActivity listener)
    {
        mProdutos = data;
        mContext = context;
        mListener = listener;

    }

    @NonNull
    @Override
    public ProdutoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.row_list, viewGroup, false);

        return new ProdutoViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ProdutoViewHolder produtoViewHolder, int position) {

        Produto produto = mProdutos.get(position);

        produtoViewHolder.textProdutoView.setText(produto.nome);
        produtoViewHolder.textQDTView.setText(produto.qtde);
        produtoViewHolder.produto = produto;

    }

    @Override
    public int getItemCount() {
        return mProdutos != null ? mProdutos.size() : 0;

    }

    public void update(List<Produto> produtos) {
        mProdutos = produtos;
        notifyDataSetChanged();
    }

    public class ProdutoViewHolder extends RecyclerView.ViewHolder{


        TextView textProdutoView, textQDTView;

        Produto produto;




        public ProdutoViewHolder(@NonNull View itemView) {
            super(itemView);

            textProdutoView = itemView.findViewById(R.id.textProdutoView);

            textQDTView = itemView.findViewById(R.id.textQDTView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mListener.onProdutoItemClick(produto);

                }
            });


        }
    }

    public interface OnProdutoListener
    {
        void onProdutoItemClick(Produto produto);
    }

}
