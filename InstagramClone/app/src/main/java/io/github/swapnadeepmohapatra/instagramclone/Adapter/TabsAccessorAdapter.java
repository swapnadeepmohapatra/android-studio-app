package io.github.swapnadeepmohapatra.instagramclone.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import io.github.swapnadeepmohapatra.instagramclone.Fragments.NewPostFragment;
import io.github.swapnadeepmohapatra.instagramclone.Fragments.PostsFragment;
import io.github.swapnadeepmohapatra.instagramclone.Fragments.ProfileFragment;

public class TabsAccessorAdapter extends FragmentPagerAdapter {
    public TabsAccessorAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new PostsFragment();
            case 1:
                return new NewPostFragment();
            case 2:
                return new ProfileFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Posts";
            case 1:
                return "New Post";
            case 2:
                return "Profile";
            default:
                return null;
        }
    }
}
