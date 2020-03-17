package com.dodemy.android.livedata.ui.transform;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.dodemy.android.livedata.R;
import com.dodemy.android.livedata.model.User;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TransformFragment extends Fragment {

    @BindView(R.id.full_address)
    TextView tvFullAddress;

    @BindView(R.id.name)
    TextView tvName;

    private TransformViewModel mViewModel;

    public static TransformFragment newInstance() {
        return new TransformFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.transform_fragment, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(TransformViewModel.class);

        mViewModel.getUser().observe(getViewLifecycleOwner(), new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                tvName.setText(user.getName());
            }
        });

        mViewModel.getFullAddress().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                tvFullAddress.setText(s);
            }
        });
    }

    /**
     * Updating user address. You can see full address reflected on
     * the UI automatically as full address is observed
     */
    @OnClick(R.id.btn_update_address)
    void updateAddress() {
        User user = prepareUser();
        mViewModel.setUser(user);
    }

    private User prepareUser() {
        User u = new User();
        u.setName("Maduabughichi Achilefu");

        u.setCountry("Nigeria");
        u.setStreetAddress("Flat 3, Mowagen, Lag");
        u.setCity("Lagos");
        u.setPinCode("245700");

        u.setPrimaryMobile("0123456789");
        u.setSecondaryMobile("1234567890");
        return u;
    }

}
