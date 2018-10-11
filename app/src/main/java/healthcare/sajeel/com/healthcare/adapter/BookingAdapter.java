package healthcare.sajeel.com.healthcare.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.squareup.picasso.Picasso;

import healthcare.sajeel.com.healthcare.R;
import healthcare.sajeel.com.healthcare.model.BookingListing;
import healthcare.sajeel.com.healthcare.model.CustomTextView;
import healthcare.sajeel.com.healthcare.model.Dashboard;
import healthcare.sajeel.com.healthcare.model.RoundedImageView;

public class BookingAdapter extends BaseAdapter {
    private final Context mContext;
    private final BookingListing[] booking;

    /**
     * @param context Context
     * @param booking
     */
    public BookingAdapter(Context context, BookingListing[] booking) {
        this.mContext = context;
        this.booking = booking;
    }

    /**
     * Get count of the list
     * @return int
     */
    @Override
    public int getCount() {
      return booking.length;
    }

    /**
     * Retrieve item/club id
     * @param position int
     * @return long
     */
    @Override
    public long getItemId(int position) {
      return 0;
    }

    /**
     * Get item
     * @param position int
     * @return null
     */
    @Override
    public Object getItem(int position) {
      return null;
    }

    /**
     * Append all the items with in the listview to render it on the screen
     * @param position int
     * @param convertView View
     * @param parent ViewGroup
     * @return View
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final BookingListing bookingListing = booking[position];

        /* View holder pattern */
        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.booking_list_item, null);

            final CustomTextView physicianBookingName = (CustomTextView)convertView.findViewById(R.id.physician_appointment_name);
            final CustomTextView physicianBookingDesignation = (CustomTextView)convertView.findViewById(R.id.physician_appointment_designation);
            final CustomTextView physicianBookingAddress = (CustomTextView)convertView.findViewById(R.id.physician_appointment_address);
            final RoundedImageView physicianBookingImage = (RoundedImageView)convertView.findViewById(R.id.physician_appointment_image);

            final ViewHolder viewHolder = new ViewHolder(physicianBookingName, physicianBookingDesignation, physicianBookingAddress, physicianBookingImage);
            convertView.setTag(viewHolder);
        }

        final ViewHolder viewHolder = (ViewHolder)convertView.getTag();
        viewHolder.physicianBookingName.setText(bookingListing.getName());
        viewHolder.physicianBookingDesignation.setText(bookingListing.getDesignation());
        viewHolder.physicianBookingAddress.setText(bookingListing.getAddress());

        Picasso.with(mContext).load(bookingListing.getPicture()).into(viewHolder.physicianBookingImage);

        return convertView;
    }

    private class ViewHolder {
        private final CustomTextView physicianBookingName;
        private final CustomTextView physicianBookingDesignation;
        private final CustomTextView physicianBookingAddress;
        private final RoundedImageView physicianBookingImage;

        /**
         * @param physicianBookingName String
         * @param physicianBookingDesignation String
         * @param physicianBookingAddress String
         * @param physicianBookingImage
         */
        public ViewHolder(CustomTextView physicianBookingName, CustomTextView physicianBookingDesignation, CustomTextView physicianBookingAddress, RoundedImageView physicianBookingImage) {
            this.physicianBookingName = physicianBookingName;
            this.physicianBookingDesignation = physicianBookingDesignation;
            this.physicianBookingAddress = physicianBookingAddress;
            this.physicianBookingImage = physicianBookingImage;
        }
    }
}