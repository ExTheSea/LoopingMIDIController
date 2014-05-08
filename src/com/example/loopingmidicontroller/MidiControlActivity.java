package com.example.loopingmidicontroller;

import java.io.IOException;
import java.math.BigInteger;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MidiControlActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_midi_control);
		((Button)findViewById(R.id.btntest)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						try {
							InetAddress ia;
							ia = InetAddress.getLocalHost();
							int port = 8080;
							int i = 81;
							BigInteger bi = BigInteger.valueOf(i);
							byte[] data = bi.toByteArray();
							System.out.println(data[0]);
							DatagramPacket packet = new DatagramPacket( data, data.length, ia, port );
							DatagramSocket toSocket = new DatagramSocket();
							toSocket.send( packet );
						} catch (SocketException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}).start();
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.midi_control, menu);
		return true;
	}

}
