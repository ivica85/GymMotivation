import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Ivica on 18.4.2016..
 */
public class adapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 2;
    private String tabTitles[] = new String[]{"MPlayer","Koraci"};


    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);

    }


