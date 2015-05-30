package infinity.complain_app;


import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

public class local extends Activity {
    LinearLayout mylayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.local);
        mylayout = (LinearLayout) findViewById(R.id.mylayout);
        SQLiteDatabase db = openOrCreateDatabase("news",MODE_PRIVATE,null);
        db.execSQL("DROP TABLE mylocal;");
        db.execSQL("CREATE TABLE mylocal(name VARCHAR, email VARCHAR,password VARCHAR);");
        db.execSQL("INSERT INTO mylocal VALUES('Shivang Nagaria','shivang.nagaria@gmail.com','shiv_pass');");
        db.execSQL("INSERT INTO mylocal VALUES('Raj Manvar','raj.manvar@gmail.com','raj_pass');");
        db.execSQL("INSERT INTO mylocal VALUES('Deepanshu Jain','deep@gmail.com','deep_pass');");
        Cursor c =db.rawQuery("SELECT * FROM mylocal ;",null);
        c.moveToFirst();
        final TextView[] myText = new TextView[3];
        for(int i=0; i<3 ;i++){
            final TextView rowText = new TextView(this);
            String name = new String();
            String email = new String();
            String pass = new String();
            name = c.getString(c.getColumnIndex("name"));
            email = c.getString(c.getColumnIndex("email"));
            pass = c.getString(c.getColumnIndex("password"));
            rowText.setText("Name: "+ name+ "\n"+"Email:"+email+"\n"+"password:"+pass);
            mylayout.addView(rowText);
            myText[i]=rowText;
            if(c!=null){
                c.moveToNext();
            }
        }
        c.close();
        db.close();
    }

}
