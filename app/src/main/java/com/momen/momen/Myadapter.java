package com.momen.momen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import de.hdodenhof.circleimageview.CircleImageView;

public class Myadapter extends RecyclerView.Adapter<Myadapter.MyviewHolder> {


    private static Clicklistener clicklistener ;
    Context context;
    String[] title,dese;
    int[] Images;


    public Myadapter(Context context, String[] title, String[] dese, int[] images) {


        this.context = context;
        this.title = title;
        this.dese = dese;
       this.Images = images;
    }


    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        LayoutInflater layoutInflater=LayoutInflater.from(context);
      View view =  layoutInflater.inflate(R.layout.simpleLayout,viewGroup,false);


        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {


        holder.titletextview.setText(title[position]);
        holder.deseTextview.setText(dese[position]);
         holder.flagImageview.setImageResource(Images[position]);




    }

    @Override
    public int getItemCount() {
        return title.length;
    }

    class MyviewHolder extends RecyclerView.ViewHolder  implements  View.OnClickListener,View.OnLongClickListener{

        TextView titletextview, deseTextview;
        CircleImageView flagImageview;

       public MyviewHolder(@NonNull View itemView)
       {
           super(itemView);
           titletextview = itemView.findViewById(R.id.tvtitleId);
           deseTextview= itemView.findViewById(R.id.tvdescripId);
           flagImageview= itemView.findViewById(R.id.imgVid);



           itemView.setOnClickListener(this);
           itemView.setOnLongClickListener(this);


       }


        @Override
        public void onClick(View v) {

        clicklistener.OnItmClick(getAdapterPosition(),v);

        }
        @Override
        public boolean onLongClick(View v) {

            clicklistener.OnItmlongClick(getAdapterPosition(),v);
            return false;
        }
    }

public interface Clicklistener{

        void OnItmClick(int position, View v);
        void OnItmlongClick(int position, View v);

}


public void setOnItmClickListener(Clicklistener clickListener){

        Myadapter.clicklistener=clickListener;
}

}
