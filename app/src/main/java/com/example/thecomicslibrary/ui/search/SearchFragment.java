package com.example.thecomicslibrary.ui.search;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.thecomicslibrary.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mancj.materialsearchbar.MaterialSearchBar;

public class SearchFragment extends Fragment implements  MaterialSearchBar.OnSearchActionListener{
    private MaterialSearchBar searchBar;
    private int filterSelection;
    private RadioGroup radioGroup;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View root = inflater.inflate(R.layout.fragment_search, container, false);

        searchBar = (MaterialSearchBar) root.findViewById(R.id.searchBar);

        searchBar.setOnSearchActionListener(this);

        //Implement realtime text reader
        searchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        final FloatingActionButton filterButton = root.findViewById(R.id.filterButton);

        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
                        getActivity(), R.style.BottomSheetDialogTheme
                );
                View bottomSheetView = LayoutInflater.from(getActivity().getApplicationContext())
                        .inflate(
                                R.layout.bottom_sheet, (LinearLayout)root.findViewById(R.id.bottomSheetContainer)
                        );

                radioGroup = (RadioGroup) bottomSheetView.findViewById(R.id.radioGroup);
                if (radioGroup.getCheckedRadioButtonId() != 0)
                    radioGroup.check(filterSelection);

                bottomSheetView.findViewById(R.id.buttonFilter).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        filterSelection = radioGroup.getCheckedRadioButtonId();
                        bottomSheetDialog.dismiss();
                    }
                });
                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();
            }
        });

        return root;
    }

    @Override
    public void onSearchStateChanged(boolean enabled) {
    }

    @Override
    public void onSearchConfirmed(CharSequence text) {
        System.out.println(searchBar.getText());
        System.out.println(filterSelection);
        //// find the radiobutton by returned id
        //		        radioSexButton = (RadioButton) findViewById(selectedId);
        InputMethodManager imm =(InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(searchBar.getWindowToken(), 0);
    }

    @Override
    public void onButtonClicked(int buttonCode) {
    }


}
