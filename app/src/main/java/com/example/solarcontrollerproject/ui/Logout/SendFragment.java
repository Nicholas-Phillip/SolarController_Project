package com.example.solarcontrollerproject.ui.Logout;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.solarcontrollerproject.Login;
import com.example.solarcontrollerproject.MainActivity;
import com.example.solarcontrollerproject.R;
import com.google.firebase.auth.FirebaseAuth;

public class SendFragment extends Fragment {

    private Button Logout;
    private FirebaseAuth mAuth;

    private SendViewModel sendViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        sendViewModel =
                ViewModelProviders.of(this).get(SendViewModel.class);
        View root = inflater.inflate(R.layout.fragment_send, container, false);
        final TextView textView = root.findViewById(R.id.text_send);
        sendViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        Logout = root.findViewById(R.id.Logout_button);
        signout();

        return root;
    }
    private void signout()
    {
        mAuth = FirebaseAuth.getInstance();
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Toast.makeText(getActivity().getApplicationContext(), "You have been Signed Out",
                      Toast.LENGTH_LONG).show();
                SignoutfromDatabase();
                Transfer();
            }
        });
    }

    private void SignoutfromDatabase(){
        //
        // TODO: Logout from Firebase.
        mAuth.signOut();
    }

    private void Transfer ()
    {
        Intent i = new Intent(getActivity().getApplicationContext(), Login.class);
                startActivity(i);

    }
}