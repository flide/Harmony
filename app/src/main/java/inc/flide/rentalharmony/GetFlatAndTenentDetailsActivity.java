package inc.flide.rentalharmony;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GetFlatAndTenentDetailsActivity extends AppCompatActivity {

    private List<String> listHeaders;
    private HashMap<String, List<String>> mapDefaultNames;
    private ExpandableInputListViewAdapter expandableInputListViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_flat_and_tenent_details);

        Intent callerIntent = getIntent();
        int totalTenents = callerIntent.getIntExtra(RentDivisionInputActivity.TOTAL_TENENTS_KEY, 2);

        setupDefaultText(totalTenents);

        ExpandableListView expandableListView = (ExpandableListView) findViewById(R.id.tenentNameExpandableListView);
        expandableInputListViewAdapter = new ExpandableInputListViewAdapter(this, listHeaders, mapDefaultNames);
        expandableListView.setAdapter(expandableInputListViewAdapter);

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, final int groupPosition, final int childPosition, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(GetFlatAndTenentDetailsActivity.this);
                builder.setTitle("Title");

                // Set up the input
                final EditText input = new EditText(GetFlatAndTenentDetailsActivity.this);
                // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
                builder.setView(input);

                // Set up the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String m_Text = input.getText().toString();
                        expandableInputListViewAdapter.setChildData(groupPosition,childPosition, m_Text);
                        expandableInputListViewAdapter.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();

                return true;
            }
        });

    }

    private void setupDefaultText(int totalTenents) {

        listHeaders = new ArrayList<String>(totalTenents);
        listHeaders.add(0,"Roommates");
        listHeaders.add(1,"Rooms");

        List<String> defaultTeneantNames = new ArrayList<String>(totalTenents);
        List<String> defaultRoomNames = new ArrayList<String>(totalTenents);
        for(int i = 0 ; i < totalTenents; i++){
            defaultTeneantNames.add(i, "Roommate-"+(i+1));
            defaultRoomNames.add(i, "Room-"+(i+1));
        }
        mapDefaultNames = new HashMap<String, List<String>>(totalTenents);
        mapDefaultNames.put(listHeaders.get(0), defaultTeneantNames);
        mapDefaultNames.put(listHeaders.get(1), defaultRoomNames);
    }

    public void onBackPressed(View view){
        finish();
    }
}
