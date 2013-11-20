package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.thesis.emostatus.R;

import java.util.List;

import persistance.InfoComponent;


public class InfoArrayAdapter extends ArrayAdapter<InfoComponent> {
	private final Context context;
	private final List<InfoComponent> values;

	public InfoArrayAdapter(Context context, List<InfoComponent> values) {
		super(context, R.layout.two_line_component_list, values);
		this.context = context;
		this.values = values;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View option = inflater.inflate(R.layout.info, parent, false);
		TextView title = (TextView) option.findViewById(R.id.title);
        title.setEnabled(values.get(position).isEnabled());
        title.setText(values.get(position).getTitle());
        option.setId(values.get(position).getId());
		return option;
	}

}
