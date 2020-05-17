package ca.yorku.eecs.kryptonote;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class kryptoNoteActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kryptonote_layout);
    }

    public void onEncrypt(View v){
        EditText noteView = (EditText) findViewById(R.id.data);
        String note=noteView.getText().toString();
        EditText keyView = (EditText) findViewById(R.id.key);
        String key=keyView.getText().toString();
        CipherModel cipher= new CipherModel(key);
        ((EditText) findViewById(R.id.data)).setText(cipher.encrypt(note));
    }

    public void onDecrypt(View v){
        EditText noteView = (EditText) findViewById(R.id.data);
        String note=noteView.getText().toString();
        EditText keyView = (EditText) findViewById(R.id.key);
        String key=keyView.getText().toString();
        CipherModel cipher= new CipherModel(key);
        ((EditText) findViewById(R.id.data)).setText(cipher.decrypt(note));
    }

    public void onSave(View v)
    {
        try {
            String name = ((EditText) findViewById(R.id.file)).getText().toString();
            File dir = this.getFilesDir();
            File file = new File(dir, name);
            FileWriter fw = new FileWriter(file);
            fw.write(((EditText) findViewById(R.id.data)).getText().toString());
            fw.close();
            Toast.makeText(this, "Note Saved", Toast.LENGTH_LONG);
        }
        catch (Exception e)
        {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG);
        }
    }

    public void onLoad(View v)
    {
        try
        {
            String name = ((EditText) findViewById(R.id.file)).getText().toString();
            File dir = this.getFilesDir();
            File file = new File(dir, name);
            FileReader fr = new FileReader(file);

            String show = "";
            for (int c = fr.read(); c != -1; c = fr.read())
            {
                show += (char) c;
            }
            fr.close();
            ((EditText) findViewById(R.id.data)).setText(show);
        } catch (Exception e)
        {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG);
        }

    }
}
