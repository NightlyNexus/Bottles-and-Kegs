package com.brianco.bottlesandkegs;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new MyFragment()).commit();
		}
	}

	public static class MyFragment extends Fragment {
		private Switch mSwitch;
		private EditText mEditText;
		private ListView mLv;
		private ArrayAdapter mAdapter;
		private List<String> mNumbersArray;
		private Button mButton;
		private BottlesAndKegs mBottlesAndKegs;

		public MyFragment() {
			mBottlesAndKegs = new BottlesAndKegs();
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			mSwitch = (Switch) rootView.findViewById(R.id.expert);
			mEditText = (EditText) rootView.findViewById(R.id.number);
			mEditText.setText(String.valueOf(1));
			mLv = (ListView) rootView.findViewById(R.id.list_view);
			mNumbersArray = new ArrayList<String>();
			mAdapter = new ArrayAdapter<String>(getActivity(),
	      	        android.R.layout.simple_list_item_1, mNumbersArray);
			mLv.setAdapter(mAdapter);
			mButton = (Button) rootView.findViewById(R.id.add_number);
			mButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					int num;
					try {
						num = Integer.parseInt(mEditText.getText().toString());
					} catch (NumberFormatException e) {
						Toast.makeText(getActivity(), R.string.invalid_number,
								Toast.LENGTH_SHORT).show();
						return;
					}
					boolean beginner = !mSwitch.isChecked();
					mNumbersArray.add(mBottlesAndKegs.bottlesAndKegs(beginner, num));
					mAdapter.notifyDataSetChanged();
					mEditText.setText(String.valueOf(++num));
					mLv.setSelection(mLv.getCount() - 1);
				}
			});
			return rootView;
		}

		
	}

}
