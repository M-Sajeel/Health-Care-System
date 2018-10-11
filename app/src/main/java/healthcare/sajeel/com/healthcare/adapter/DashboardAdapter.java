package healthcare.sajeel.com.healthcare.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.squareup.picasso.Picasso;

import healthcare.sajeel.com.healthcare.R;
import healthcare.sajeel.com.healthcare.model.CustomTextView;
import healthcare.sajeel.com.healthcare.model.Dashboard;
import healthcare.sajeel.com.healthcare.model.RoundedImageView;

public class DashboardAdapter extends BaseAdapter {
    private final Context mContext;
    private final Dashboard[] data;

    /**
     * @param context Context
     * @param data
     */
    public DashboardAdapter(Context context, Dashboard[] data) {
        this.mContext = context;
        this.data = data;
    }

    /**
     * Get count of the list
     * @return int
     */
    @Override
    public int getCount() {
      return data.length;
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
        final Dashboard dashboardData = data[position];

        /* View holder pattern */
        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.fragment_dashboard, null);

            final CustomTextView physicianName = (CustomTextView)convertView.findViewById(R.id.physician_name);
            final CustomTextView physicianAddress = (CustomTextView)convertView.findViewById(R.id.physician_address);
            final RoundedImageView physicianImage = (RoundedImageView)convertView.findViewById(R.id.physician_image);

            final ViewHolder viewHolder = new ViewHolder(physicianName, physicianAddress, physicianImage);
            convertView.setTag(viewHolder);
        }

        final ViewHolder viewHolder = (ViewHolder)convertView.getTag();
        viewHolder.physicianName.setText(dashboardData.getName());
        viewHolder.physicianAddress.setText(dashboardData.getAddress());

        Picasso.with(mContext).load(dashboardData.getPicture()).into(viewHolder.physicianImage);

        return convertView;
    }

    private class ViewHolder {
        private final CustomTextView physicianName;
        private final CustomTextView physicianAddress;
        private final RoundedImageView physicianImage;

        /**
         * @param physicianName String
         * @param physicianAddress String
         * @param physicianImage
         */
        public ViewHolder(CustomTextView physicianName, CustomTextView physicianAddress, RoundedImageView physicianImage) {
            this.physicianName = physicianName;
            this.physicianAddress = physicianAddress;
            this.physicianImage = physicianImage;
        }
    }
}