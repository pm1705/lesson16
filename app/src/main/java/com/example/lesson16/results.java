/** @author paz malul
 *  results screen in the arithmetic / geometric sequence
 */
package com.example.lesson16;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class results extends AppCompatActivity implements View.OnCreateContextMenuListener, AdapterView.OnItemLongClickListener{
    String[] items = new String[20];
    ListView lv1;
    TextView tv1, tv2, tv3;
    int mode, pos;
    double first, diff;


    /** onCreate
     * connects all view elements to their java variabels
     * sets deafult textview values and sets list.
     * @param savedInstanceState - ...
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        lv1 = (ListView) findViewById(R.id.lv1);
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);

        Intent gi = getIntent();
        mode = gi.getIntExtra("mode",2);
        first = Double.parseDouble(gi.getStringExtra("first"));
        diff = Double.parseDouble(gi.getStringExtra("diff"));

        if (mode == 0){
            tv1.setText("type: arithmetic");
        }
        else{
            tv1.setText("type: geometric");
        }

        tv2.setText("diffrence: "+diff);

        tv3.setText("Position / sum");

        if (mode == 0){
            for (int i=0;i<20;i++){
                items[i] = String.valueOf(first + i*diff);
            }
        }
        else{
            for (int i=0;i<20;i++){
                items[i] = String.valueOf(first*Math.pow(diff,i));
            }
        }

        ArrayAdapter<String> adp = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, items);
        lv1.setAdapter(adp);
        lv1.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lv1.setOnItemLongClickListener(this);
        lv1.setOnCreateContextMenuListener(this);

    }

    /** onItemLongClick
     * used to update the help paramater pos when a long click accures
     * @param parent
     * @param view
     * @param position - the position of the clicked item in the list
     * @param id
     * @return returns false -?-
     */

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        pos = position;
        return false;
    }

    /** return to main screen on return button click
     * @param view
     */

    public void ret(View view) {
        finish();
    }

    /* creates the context menu
     */

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("ummm operations yes:");
        menu.add("position");
        menu.add("sum");
    }

    /** when an item is clicked will update the textbox to the relevant info.
     * @param item - which item was clicked.
     * @return
     */

    @Override
    public boolean onContextItemSelected(MenuItem item){
        String oper=item.getTitle().toString();
        if (oper.equals("sum")) {
            if (mode == 0){
                tv3.setText("sum: "+((first+Double.parseDouble(items[pos]))* (pos+1)/2));
            }
            else{
                tv3.setText("sum: "+((first*(1-Math.pow(diff,pos+1)))/(1-diff)));
            }
        }
        else if (oper.equals("position")){
            pos = pos+1;
            tv3.setText("position: "+pos);
            pos = pos-1;
        }

        return true;
    }
}