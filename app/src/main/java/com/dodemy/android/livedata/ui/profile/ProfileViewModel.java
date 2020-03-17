package com.dodemy.android.livedata.ui.profile;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dodemy.android.livedata.model.User;

import java.util.ArrayList;
import java.util.List;

public class ProfileViewModel extends ViewModel {

    private MutableLiveData<User> user;

    public LiveData<User> getUser() {
        if (user == null) {
            prepareUser();
        }
        return user;
    }

    private void prepareUser() {
        User u = new User();
        u.setName("Maduabughichi Achilefu");
        u.setEmail("info@dodemy.com");

        List<String> phoneNumbers = new ArrayList<>();
        phoneNumbers.add("0123456789");
        phoneNumbers.add("1234567890");
        phoneNumbers.add("WhatsApp");
        phoneNumbers.add("Send SMS");
        phoneNumbers.add("Tweet");
        u.setCallActions(phoneNumbers);

        u.setCountry("Nigeria");
        u.setProfileImageUrl("https://avatars2.githubusercontent.com/u/18477889?s=460&u=4105c089cfeab31925f0bea459ff1fd872deda07&v=4");
        u.setProfileCoverImage("https://images.hdqwalls.com/wallpapers/bthumb/abstract-dark-purple-4k-uz.jpg");

        /*
        https://images.hdqwalls.com/wallpapers/bthumb/material-design-dark-red-black-ap.jpg
        https://images.hdqwalls.com/wallpapers/bthumb/dark-green-material-design-gc.jpg
        https://images.hdqwalls.com/wallpapers/magnetic-north-yn.jpg
        https://images.hdqwalls.com/wallpapers/interstellar-abstract-art-i4.jpg
        https://images.hdqwalls.com/wallpapers/bthumb/abstract-dark-purple-4k-uz.jpg
        https://images.hdqwalls.com/wallpapers/bthumb/material-design-color-pallets-new.jpg
        https://images.hdqwalls.com/wallpapers/bthumb/geometric-material-yellow-blue-4k-18.jpg
        https://images.hdqwalls.com/wallpapers/bthumb/black-green-material-design-hd.jpg
        https://images.hdqwalls.com/wallpapers/bthumb/material-design-dark-red-black-ap.jpg
         */

        user = new MutableLiveData<>();
        user.setValue(u);
    }
}
