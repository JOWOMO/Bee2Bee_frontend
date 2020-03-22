package de.wirvsvirus.abee.fragments;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;

import java.util.Arrays;
import java.util.HashMap;
import java.util.ArrayList;

import de.wirvsvirus.abee.R;
import de.wirvsvirus.abee.data.Posting;

public class DetailFragment extends Fragment {

    private ArrayList<HashMap<Integer, String>> mockPersons;
    private Posting mockPosting;

    public DetailFragment() {
        mockPersons = new ArrayList<HashMap<Integer, String>>(
                Arrays.asList(new HashMap<Integer, String>(){{put(16,  "Kann lange stehen, Kann viel laufen, Sanitätshelfer, ");}},
                              new HashMap<Integer, String>(){{put(5, "Gabelstapler Führerschein, Kann lange sitzen");}},
                              new HashMap<Integer, String>(){{put(1,  "Desinfektion & Reinigung, Kann viel laufen");}},
                              new HashMap<Integer, String>(){{put(1,  "Krisenmanagement");}}
                )
        );

        mockPosting = new Posting(
                123,
                456,
                "Wörstel Bau GmbH",
                "10119",
                "Berlin",
                mockPersons,
                2,
                "",
                ""
        );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        AppCompatImageView close = view.findViewById(R.id.close_button);
        close.setOnClickListener(view1 -> getActivity().finish());

        TextView companyName = view.findViewById(R.id.companyName);
        companyName.setText(mockPosting.getDescription());

        TextView zip = view.findViewById(R.id.plz);
        zip.setText(mockPosting.getZipCode());

        TextView city = view.findViewById(R.id.city);
        city.setText(mockPosting.getLocation());

        TextView postingType = view.findViewById(R.id.postType);
        postingType.setText( mockPosting.getType() > 1 ? "Angebot" : "Bedarf" );

        LinearLayout personsList = view.findViewById(R.id.personsList);

        for (HashMap<Integer, String> persons : mockPosting.getPersons()) {
            for (HashMap.Entry entry : persons.entrySet()) {
                LinearLayout personsItem = new LinearLayout(getContext());
                personsItem.setOrientation(LinearLayout.HORIZONTAL);

                TextView personsCount = new TextView(getContext());
                personsCount.setLayoutParams(new ViewGroup.LayoutParams(
                        (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, getResources().getDisplayMetrics()),
                        ViewGroup.LayoutParams.WRAP_CONTENT)
                );
                personsCount.setGravity(Gravity.END);
                personsCount.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
                personsCount.setText(entry.getKey().toString());

                Space space = new Space(getContext());
                space.setLayoutParams(new ViewGroup.LayoutParams(
                        (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16, getResources().getDisplayMetrics()),
                        ViewGroup.LayoutParams.WRAP_CONTENT)
                );

                TextView personsRole = new TextView(getContext());
                personsRole.setLayoutParams(new TableLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));
                personsRole.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
                personsRole.setText(entry.getValue().toString());


                personsItem.addView(personsCount);
                personsItem.addView(space);
                personsItem.addView(personsRole);

                personsList.addView(personsItem);
            }
        }

        return view;
    }
}