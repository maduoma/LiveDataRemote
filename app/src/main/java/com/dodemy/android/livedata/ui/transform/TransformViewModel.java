package com.dodemy.android.livedata.ui.transform;


import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.dodemy.android.livedata.model.User;


/**
 * The examples provided here not best practises.
 * They are more of syntactical representation
 */
public class TransformViewModel extends ViewModel {
    private MutableLiveData<User> user;

    // full address is not member of user
    // it will be prepared dynamically using Transformation
    private LiveData<String> fullAddress;

    public LiveData<User> getUser() {
        if (user == null) {
            prepareUser();
        }
        return user;
    }

    public void setUser(User user) {
        this.user.setValue(user);
    }


    /**
     * Preparing full address using map operator transformation
     */
    public LiveData<String> getFullAddress() {
        if (user == null)
            return null;

        fullAddress = Transformations.map(user, new Function<User, String>() {
            @Override
            public String apply(User user) {
                StringBuilder address = new StringBuilder(user.getStreetAddress());
                address.append("\n");
                address.append(user.getCity() + ", " + user.getCountry() + " - " + user.getPinCode());
                address.append("\n");
                address.append("Lag: +234" + user.getPrimaryMobile() + ", ");
                address.append("+234" + user.getSecondaryMobile());

                return address.toString();
            }
        });

        return fullAddress;
    }

    /**
     * Preparing dummy user. This should be done using a
     * repository
     */
    private void prepareUser() {
        User u = new User();
        u.setName("Maduabughichi Achilefu");
        u.setEmail("info@dodemy.com");

        u.setCountry("Nigeria");
        u.setStreetAddress("Flat 5, Oluga, Pait");
        u.setCity("Lato");
        u.setPinCode("23000");

        u.setPrimaryMobile("01234567890");
        u.setSecondaryMobile("123456789");

        user = new MutableLiveData<>();
        user.setValue(u);
    }
}
