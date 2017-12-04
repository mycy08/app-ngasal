package tercyduk.appngasal.apihelper.Adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.Collections;
import java.util.List;

import tercyduk.appngasal.R;
import tercyduk.appngasal.coresmodel.Lapagans;
import tercyduk.appngasal.coresmodel.LapanganImage;

/**
 * Created by User on 12/1/2017.
 */

public class AdapterLapangan extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    private LayoutInflater inflater;
    List<Lapagans> data= Collections.emptyList();
    List<LapanganImage>dataimage = Collections.emptyList();
    Lapagans current;
    int currentPos=0;
    // create constructor to innitilize context and data sent from MainActivity
    public AdapterLapangan(Context context, List<Lapagans> data){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=data;
    }
    // Inflate the layout when viewholder created
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.item_futsal_grid, parent,false);
        MyHolder holder=new MyHolder(view);
        return holder;
    }
    // Bind data
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        // Get current position of item in recyclerview to bind data and assign values from list
        MyHolder myHolder= (MyHolder) holder;
        Lapagans current=data.get(position);
        LapanganImage currents = dataimage.get(position);
        myHolder.txtnamaLapang.setText(current.namalapang);
        myHolder.txtKecamatan.setText(current.kecamatan);
        myHolder.txtHarga.setText("Rp: " + current.price +"\\JAM");
        myHolder.txtHarga.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
        myHolder.imgVlapang.setImageResource(currents.getGambarLap());
//
//        // load image into imageview using glide
//        Glide.with(context).load("http://192.168.1.7/test/images/" + current.fishImage)
//                .placeholder(R.drawable.ic_img_error)
//                .error(R.drawable.ic_img_error)
//                .into(myHolder.ivFish);

    }

    // return total item from List
    @Override
    public int getItemCount() {
        return data.size();
    }


    class MyHolder extends RecyclerView.ViewHolder{

        private TextView txtnamaLapang,txtHarga,txtKecamatan;
        private ImageView imgVlapang;

        // create constructor to get widget reference
        public MyHolder(View itemView) {
            super(itemView);
            txtnamaLapang= (TextView) itemView.findViewById(R.id.item_lapang_grid_name);
            imgVlapang = (ImageView) itemView.findViewById(R.id.item_lapang_grid_image);
            txtHarga = (TextView) itemView.findViewById(R.id.item_lapang_grid_harga);
            txtKecamatan = (TextView) itemView.findViewById(R.id.item_lapang_grid_kecamatan);
        }

    }
}
