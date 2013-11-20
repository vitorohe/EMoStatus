package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import android.widget.TextView;

import com.thesis.emostatus.R;

import java.util.ArrayList;
import java.util.List;

import persistance.ThreeDataComponent;


public class ThreeCompArrayAdapter extends ArrayAdapter<ThreeDataComponent> {
	private final Context context;
	private final List<ThreeDataComponent> values;

	public ThreeCompArrayAdapter(Context context, List<ThreeDataComponent> values) {
		super(context, R.layout.two_line_component_list, values);
		this.context = context;
		this.values = values;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View user_info = inflater.inflate(R.layout.two_line_component_list, parent, false);
		TextView name = (TextView) user_info.findViewById(R.id.title);
		TextView status = (TextView) user_info.findViewById(R.id.description);
		ImageView imageView = (ImageView) user_info.findViewById(R.id.picture);
        name.setText(values.get(position).getTitle());
        status.setText(values.get(position).getDescription());
        imageView.setImageResource(values.get(position).getImageId());
		return user_info;
	}

}
