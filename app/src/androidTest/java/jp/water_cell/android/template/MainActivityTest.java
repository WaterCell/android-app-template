package jp.water_cell.android.template;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    MainActivity activity;

    public MainActivityTest() {
        super(MainActivity.class);
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();

        injectInstrumentation(InstrumentationRegistry.getInstrumentation());

        activity = getActivity();
    }

    @Test
    public void testTvDisplay() {
        onView(withId(R.id.tv_display)).check(matches(withText("")));
    }

    @Test
    public void testBtnButterKnife() {
        onView(withId(R.id.btn_butter_knife)).perform(click());
        onView(withId(R.id.tv_display)).check(matches(withText("Hello Butter Knife!\n")));
    }

    @Test
    public void 各Viewがインジェクトされている() {
        assertThat(activity.toolbar, is(not(nullValue())));
        assertThat(activity.tvDisplay, is(not(nullValue())));
        assertThat(activity.ivPicasso, is(not(nullValue())));
    }

}
