package com.dodemy.android.livedata.ui.profile;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dodemy.android.livedata.R;
import com.dodemy.android.livedata.model.User;
import com.dodemy.android.livedata.utils.MyDividerItemDecoration;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileFragment extends Fragment {

    private static final String TAG = ProfileFragment.class.getSimpleName();
    private ProfileViewModel mViewModel;
    CallActionsAdapter mAdapter;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);

        mViewModel.getUser().observe(getViewLifecycleOwner(), new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                Log.d(TAG, "Name: " + user.getName());
                Log.d(TAG, "Email: " + user.getEmail());
                mAdapter.setData(user.getCallActions());
            }
        });

        mAdapter = new CallActionsAdapter();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new MyDividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL, 46));
        recyclerView.setAdapter(mAdapter);
    }

    class CallActionsAdapter extends RecyclerView.Adapter<CallActionsAdapter.MyViewHolder> {

        private List<String> actions;

        public CallActionsAdapter() {
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            @BindView(R.id.call_action)
            TextView tvAction;

            public MyViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }

        @NonNull
        @Override
        public CallActionsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item_call_action, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull CallActionsAdapter.MyViewHolder holder, int position) {
            holder.tvAction.setText(actions.get(position));
        }

        @Override
        public int getItemCount() {
            Log.e(TAG, "getItemCount: " + actions.size());
            return actions.size();
        }

        public void setData(List<String> actions) {
            this.actions = actions;
            notifyDataSetChanged();
        }
    }
}
