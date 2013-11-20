package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.thesis.emostatus.R;

import java.util.List;

import persistance.OptionInfoComponent;


public class OptionInfoArrayAdapter extends ArrayAdapter<OptionInfoComponent> {
	private final Context context;
	private final List<OptionInfoComponent> values;

	public OptionInfoArrayAdapter(Context context, List<OptionInfoComponent> values) {
		super(context, R.layout.two_line_component_list, values);
		this.context = context;
		this.values = values;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View option = inflater.inflate(R.layout.text_info, parent, false);
		TextView title = (TextView) option.findViewById(R.id.title);
		TextView info = (TextView) option.findViewById(R.id.info);
        title.setEnabled(values.get(position).isEnabled());
        title.setText(values.get(position).getTitle());
        info.setText(values.get(position).getInfo());
        info.setEnabled(values.get(position).isEnabled());
        option.setId(values.get(position).getId());
        option.setEnabled(values.get(position).isEnabled());
		return option;
	}

}
