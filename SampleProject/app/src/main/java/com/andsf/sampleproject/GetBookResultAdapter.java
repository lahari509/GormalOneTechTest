package com.andsf.sampleproject;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;


import java.util.ArrayList;

public class GetBookResultAdapter extends RecyclerView.Adapter<GetBookResultAdapter.BookViewHolder> {

    private Context context;
    private ArrayList<Results> bookList;
    public GetBookResultAdapter(Context context, ArrayList<Results> bookList) {
        super();
        this.context = context;
        this.bookList = bookList;

    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.bookslist_item, viewGroup, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        String eachenigmaid = bookList.get(position).getBook_name();
        System.out.println("eachenigmaid"+eachenigmaid);
         holder.bookName.setText(bookList.get(position).getBook_name());
        holder.bookDesc.setText(bookList.get(position).getBook_desc());
        holder.authorName.setText(bookList.get(position).getBook_author());
        holder.price.setText(bookList.get(position).getBook_price());
        Glide.with(context)
                .load(bookList.get(position).getBook_img_url())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.bookImage);
    }


    @Override
    public int getItemCount() {
        return bookList.size();
    }


    static class BookViewHolder extends RecyclerView.ViewHolder {
         TextView bookName,bookDesc,authorName,price;
         ImageView bookImage;

        BookViewHolder(@NonNull View itemView) {
            super(itemView);
            bookName = itemView.findViewById(R.id.book_name);
            bookImage = itemView.findViewById(R.id.book_image);
            bookDesc = itemView.findViewById(R.id.book_desc);
            authorName = itemView.findViewById(R.id.author_name);
            price = itemView.findViewById(R.id.cost);
        }
    }
}