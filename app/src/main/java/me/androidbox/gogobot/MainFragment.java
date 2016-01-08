package me.androidbox.gogobot;


import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {
    private ActionBarDrawerToggle mDrawerToggle;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_main, container, false);

        final DrawerLayout drawerLayout = (DrawerLayout)view.findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, R.string.open, R.string.close);
        drawerLayout.setDrawerListener(mDrawerToggle);

        final AppCompatActivity appConpactActivity = (AppCompatActivity)getActivity();
        final Toolbar toolbar = (Toolbar)view.findViewById(R.id.tool_bar);
        appConpactActivity.setSupportActionBar(toolbar);
        appConpactActivity.getSupportActionBar().setDisplayShowTitleEnabled(false);
        appConpactActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /* Get the bitmap from the background */
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inMutable = true;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.background_image, options);
        final Bitmap blurredBitmap = BitmapUtils.blur(getActivity(), bitmap);

        final ImageView ivBackground = (ImageView)view.findViewById(R.id.ivBackground);
        ivBackground.setImageBitmap(blurredBitmap);
        ivBackground.setScaleType(ImageView.ScaleType.FIT_XY);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        /* This little thing will display our actual hamburger icon*/
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

}
