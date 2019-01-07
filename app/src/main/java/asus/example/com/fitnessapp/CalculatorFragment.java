package asus.example.com.fitnessapp;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Objects;

import static android.content.Context.NOTIFICATION_SERVICE;


/**
 * A simple {@link Fragment} subclass.
 */
public class CalculatorFragment extends Fragment {

    TextView result;
    EditText eWeight, eHeight;
    int nWeight, nHeight;
    Button button;

    public CalculatorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_calculator, container, false);
        button = v.findViewById(R.id.count);
        eWeight = v.findViewById(R.id.weight);
        eHeight = v.findViewById(R.id.height);
        result = v.findViewById(R.id.result);
        button.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                try {
                    nWeight = Integer.parseInt(eWeight.getText().toString());
                    nHeight = Integer.parseInt(eHeight.getText().toString());
                    if (nHeight-nWeight>120) {
                        result.setText("Your weight is too low");
                    }
                    else if (nHeight-nWeight<100){
                        result.setText("Your weight is too big");
                    }
                    else {
                        result.setText("Your weight is normal");
                    }
                }catch (NumberFormatException e){
                    result.setText("Enter the normal numbers");
                }

                NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity()).setSmallIcon(R.mipmap.ic_launcher).
                        setContentTitle("Title").setContentText("Content");

                Notification notification = builder.build();
                notification.ledARGB = Color.BLUE;
                notification.ledOffMS = 500;
                notification.ledOnMS = 500;
                notification.flags = notification.flags|Notification.FLAG_SHOW_LIGHTS;
                NotificationManager notificationManager = (NotificationManager) Objects.requireNonNull(getActivity()).getSystemService(NOTIFICATION_SERVICE);
                notificationManager.notify(101, notification);

            }
        });

        return v;
    }

}
