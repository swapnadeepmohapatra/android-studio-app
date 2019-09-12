package io.github.swapnadeepmohapatra.Parking.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import io.github.swapnadeepmohapatra.Parking.Model.Book;
import io.github.swapnadeepmohapatra.Parking.R;

public class BookAdapter extends ArrayAdapter<Book> {
    public BookAdapter(Context context, int resource, List<Book> objects) {
        super(context, resource, objects);
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.book_item, parent, false);
        }
        final Book book = getItem(position);

        TextView textViewbOOKId = convertView.findViewById(R.id.textViewBookId);
        TextView textViewSeat = convertView.findViewById(R.id.textViewBookSeat);
        TextView textViewRef = convertView.findViewById(R.id.textViewBookRef);

        textViewbOOKId.setText("Booking ID: "+book.getTicket());
        textViewSeat.setText("B"+book.getSeat());
        textViewRef.setText("Booking Ref: "+book.getRef());



        return convertView;
    }
}
