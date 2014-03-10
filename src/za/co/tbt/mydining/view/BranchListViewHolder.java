package za.co.tbt.mydining.view;

import za.co.tbt.mydining.R;
import za.co.tbt.mydining.db.Branch;
import za.co.tbt.mydining.location.LocationProvider;
import za.co.tbt.mydining.location.LocationUpdateListener;
import android.content.ComponentName;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class BranchListViewHolder implements OnClickListener, LocationUpdateListener{
	Branch branch;
	private Location location = null;
	
	public TextView branchSuburbText = null;
	public TextView branchNameText = null;
	public TextView branchAddressText = null;
	public TextView branchTelephoneText = null;
	public TextView branchHalaalText = null;
	public TextView branchKosherText = null;
	
	public BranchListViewHolder(View row) {
		// TODO Auto-generated constructor stub
		branchSuburbText = (TextView) row.findViewById(R.id.text_branch_suburb);
		branchNameText = (TextView) row.findViewById(R.id.text_branch_name);
		branchAddressText = (TextView) row.findViewById(R.id.text_branch_address);
		branchTelephoneText = (TextView) row.findViewById(R.id.text_branch_tel);
		branchHalaalText = (TextView) row.findViewById(R.id.text_branch_halaal);
		branchKosherText = (TextView) row.findViewById(R.id.text_branch_kosher);
	}

	public void populateFrom(Branch branch){
		this.branch = branch;
		
		branchSuburbText.setText(branch.getSuburb());
		branchNameText.setText(branch.getName());
		branchAddressText.setText(branch.getAddress());
		branchTelephoneText.setText(branch.getTelephone_no());
		if (branch.isHalaal()){
			branchHalaalText.setText("H");
		}else{
			branchHalaalText.setText("");
		}
		
		if (branch.isKosher()){
			branchKosherText.setText("K");
		}else{
			branchKosherText.setText("");
		}				
	}
	
	public Branch getBranch(){
		return branch;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		String saddr = "";
		
		if (location != null){
			saddr = "&saddr=" + location.getLatitude() + "," + location.getLongitude();
		}
		
		Intent intent = new Intent(Intent.ACTION_VIEW, 
			    Uri.parse("http://maps.google.com/maps?" + saddr + "daddr=" + branch.getLatitude() + "," + branch.getLongitude() + "&mode=driving"));
			intent.setComponent(new ComponentName("com.google.android.apps.maps", 
			    "com.google.android.maps.MapsActivity"));
			v.getContext().startActivity(intent);
	}

	@Override
	public void locationUpdated(Location location) {
		// TODO Auto-generated method stub
		this.location = location;
	}
}
