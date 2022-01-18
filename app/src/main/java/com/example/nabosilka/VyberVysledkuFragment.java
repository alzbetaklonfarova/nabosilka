package com.example.nabosilka;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VyberVysledkuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VyberVysledkuFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public VyberVysledkuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VyberVysledkuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static VyberVysledkuFragment newInstance(String param1, String param2) {
        VyberVysledkuFragment fragment = new VyberVysledkuFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        final View routeListingsView = inflater.inflate(R.layout.fragment_vyber_vysledku, null);
        Button tlacitko = (Button) routeListingsView.findViewById(R.id.tlacitko1);
        Bundle bundle = getArguments();
        ArrayList<String> vysledky = bundle.getStringArrayList("Zpráva");
        TextView textView = (TextView) routeListingsView.findViewById(R.id.Priklad);
        tlacitko.setText(vysledky.get(0));
        tlacitko = (Button) routeListingsView.findViewById(R.id.tlacitko2);
        tlacitko.setText(vysledky.get(1));
        tlacitko = (Button) routeListingsView.findViewById(R.id.tlacitko3);
        tlacitko.setText(vysledky.get(2));
        tlacitko = (Button) routeListingsView.findViewById(R.id.tlacitko4);
        tlacitko.setText(vysledky.get(3));
        textView.setText(vysledky.get(4));
        View view = inflater.inflate(R.layout.fragment_vyber_vysledku, container, false);
        return routeListingsView;

    }
}