package com.olimposystems.android.timpre;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class TimPREActivity extends Activity implements OnClickListener {

	private static final String CATEGORIA = "timpre";

	private static final String NUMERO_TIM = "*222";

	private TextView view;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		ImageButton button = (ImageButton) findViewById(R.id.btnSaldo);
		button.setOnClickListener(this);

		this.view = (TextView) findViewById(R.id.texto);

		ImageButton botaoSair = (ImageButton) findViewById(R.id.btnSair);
		botaoSair.setImageResource(R.drawable.exit);
		botaoSair.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				TimPREActivity.this.finish();

			}
		});

	}

	@Override
	public void onClick(View arg0) {
		String encodedHash = Uri.encode("#");
		call(NUMERO_TIM + encodedHash);
		this.view.setText("");
	}

	protected void call(String phoneNumber) {
		try {
			
			startActivityForResult(	new Intent("android.intent.action.CALL", Uri.parse("tel:"+ phoneNumber)), 1);
			this.view.setText("");
			
		} catch (Exception eExcept) {
			
			this.view.append("" + R.string.error_ussd_msg);
			
		}
		Log.i(CATEGORIA, "called " + phoneNumber);
		
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		new USSDTask().execute();
 
		this.view.append("");

	}

	   
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuItem item = menu.add(0, 1, 0,R.string.sobre);
		item.setIcon(R.drawable.olimposobre);
		item = menu.add(0, 2, 0,R.string.sair);
		item.setIcon(R.drawable.exit);
		return true;
	}
		
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		
		switch (item.getItemId()) {
		case 1:
			startActivity(new Intent(TimPREActivity.this,SobreActivity.class));
			
			return true;
		case 2:
			this.finish();
			return true;			
		}
		return false;
	}
		
}