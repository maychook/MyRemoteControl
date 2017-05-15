package com.remotecontrol.remotecontrol;

import android.content.Context;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.*;
import java.net.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;



public class MainActivity extends AppCompatActivity {

    Button btnConnect;
    Button btnSend;
    EditText txtMsg;

    private Socket socket = null;
//    private DataOutputStream os = null;
//    private DataInputStream is = null;

    private static final int SERVERPORT = 4000;
    private static final String SERVER_IP = "192.168.78.68";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnConnect = (Button)findViewById(R.id.btnConnect);
        btnSend = (Button)findViewById(R.id.btnSend);
        txtMsg = (EditText)findViewById(R.id.txtMessage);

        //---------------------------------------------------------------------------------------------------------------

        btnConnect.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                new Thread(new ClientThread()).start(); //--------------------------------------  CONNECT TO SERVER
            }
        });

        //---------------------------------------------------------------------------------------------------------------

        btnSend.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
//                try
//                {
//                    EditText et = (EditText) findViewById(R.id.txtMessage);
//                    String str = et.getText().toString();

                    String str = "hello Server!";

                    if (socket != null)
                    {
                        try
                        {
                            DataOutputStream dOut = new DataOutputStream(socket.getOutputStream());
//                            InputStreamReader inupt_stream_reader = new InputStreamReader(socket.getInputStream());
//                            BufferedReader buffered_reader= new BufferedReader(inupt_stream_reader);
//                            PrintWriter print_writer = new PrintWriter(socket.getOutputStream(),true);

//                            print_writer.println("hello server");


                            dOut.writeByte(1);
                            dOut.writeUTF("trying to send message");
                            dOut.flush();
//                            os.("hello Server!");
//                            os.close();
//                            is.close();
                            Context context = getApplicationContext();
                            Toast toast5 = Toast.makeText(context, "inside try", Toast.LENGTH_LONG);
                            toast5.show();
//                            socket.close();
                        }
                        catch (UnknownHostException e)
                        {
//                            System.err.println("Trying to connect to unknown host: "+e);
                            Context context = getApplicationContext();
                            Toast toast6 = Toast.makeText(context, "catch  1", Toast.LENGTH_LONG);
                            toast6.show();
                        }
                        catch (IOException e)
                        {
//                            System.err.println("IOException: "+e);
                            Context context = getApplicationContext();
                            Toast toast7 = Toast.makeText(context, "chatch  2", Toast.LENGTH_LONG);
                            toast7.show();
                        }
                    }
//                    PrintWriter out = new PrintWriter(new BufferedWriter(
//                            new OutputStreamWriter(socket.getOutputStream())),
//                            true);
//                    out.println(str);
//                    Toast toast1 = Toast.makeText(context, "try", Toast.LENGTH_LONG);
//                    toast1.show();
//                }
//                catch (UnknownHostException e)
//                {
//                    Toast toast2 = Toast.makeText(context, "catch 1", Toast.LENGTH_LONG);
//                    toast2.show();
//                    e.printStackTrace();
//                }
//                catch (IOException e)
//                {
//                    Toast toast3 = Toast.makeText(context, "catch 2", Toast.LENGTH_LONG);
//                    toast3.show();
//                    e.printStackTrace();
//                }
//                catch (Exception e)
//                {
//                    Toast toast4 = Toast.makeText(context, "catch 3", Toast.LENGTH_LONG);
//                    toast4.show();
//                    e.printStackTrace();
//                }

            }
        });
    }

    //---------------------------------------------------------------------------------------------------------------

    public class ClientThread implements Runnable
    {
        @Override
        public void run()
        {
            try
            {
                InetAddress serverAddr = InetAddress.getByName(SERVER_IP);
                socket = new Socket(serverAddr, SERVERPORT);
//                os = new DataOutputStream(socket.getOutputStream());
//                is=new DataInputStream(socket.getInputStream());

            }
            catch (UnknownHostException e1)
            {
                e1.printStackTrace();
                Context context2 = getApplicationContext();
                Toast toast5 = Toast.makeText(context2, "problem is os or is", Toast.LENGTH_LONG);
                toast5.show();
            }
            catch (IOException e1)
            {
                e1.printStackTrace();
                Context context2 = getApplicationContext();
                Toast toast5 = Toast.makeText(context2, "problem is os or is   2", Toast.LENGTH_LONG);
                toast5.show();
            }
        }
    }
}
