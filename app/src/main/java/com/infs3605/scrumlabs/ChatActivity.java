package com.infs3605.scrumlabs;

import android.content.Context;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.ibm.cloud.sdk.core.http.Response;
import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.assistant.v2.Assistant;
import com.ibm.watson.assistant.v2.model.SessionResponse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;


public class ChatActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ChatAdapter mAdapter;
    private ArrayList messageArrayList;
    private EditText inputMessage;
    private ImageButton btnSend;
    private ImageButton btnRecord;
  //StreamPlayer streamPlayer = new StreamPlayer();
    private boolean initialRequest;
    private boolean permissionToRecordAccepted = false;
    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;
    private static String TAG = "ChatActivity";
    private static final int RECORD_REQUEST_CODE = 101;
    private boolean listening = false;
    //private MicrophoneInputStream capture;
    private Context mContext;
    //private MicrophoneHelper microphoneHelper;

    private Assistant watsonAssistant;
    private Response<SessionResponse> watsonAssistantSession;
    //private SpeechToText speechService;
    //private TextToSpeech textToSpeech;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

    }

    private void createServices() {
        watsonAssistant = new Assistant("2019-02-28", new IamAuthenticator(getString(R.string.ibm_watson_apikey)));
        watsonAssistant.setServiceUrl(getString(R.string.ibm_watson_assistant_id));

       /* textToSpeech = new TextToSpeech(new IamAuthenticator((mContext.getString(R.string.TTS_apikey))));
        textToSpeech.setServiceUrl(mContext.getString(R.string.TTS_url));

        speechService = new SpeechToText(new IamAuthenticator(mContext.getString(R.string.STT_apikey)));
        speechService.setServiceUrl(mContext.getString(R.string.STT_url));*/
    }
}