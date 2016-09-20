package com.yash.rapidbizapps.chatapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

public class MainActivity extends AppCompatActivity {
    EditText mInputMessage;
    Button mSendButton;
    TextView mTextView;

    private Socket mSocket;
    {
        try{

            mSocket = IO.socket(AppConfig.IP);

        }
        catch(URISyntaxException e){
            e.printStackTrace();

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSocket.on("newMessage", onNewMessage);

        mSocket.connect();




        mInputMessage = (EditText)findViewById(R.id.text);
        mSendButton = (Button)findViewById(R.id.send);
        mTextView = (TextView)findViewById(R.id.message);
        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = mInputMessage.getText().toString();
                mInputMessage.setText("");
                mSocket.emit("newMessage",message);
            }
        });


    }

    private Emitter.Listener onNewMessage = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject)args[0];
                    String message = null;
                    try{
                        message = data.getString("message");

                    }
                    catch (JSONException e){
                        e.printStackTrace();

                    }
                    mTextView.setText(message);
                }
            });
        }
    };

            @Override
            public void onDestroy(){
                super.onDestroy();
                mSocket.disconnect();
                mSocket.off("newMessage",onNewMessage);

            }
}
