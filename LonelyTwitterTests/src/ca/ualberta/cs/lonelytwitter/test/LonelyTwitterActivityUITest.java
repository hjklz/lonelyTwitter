package ca.ualberta.cs.lonelytwitter.test;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ViewAsserts;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import ca.ualberta.cs.lonelytwitter.IntentReaderActivity;
import ca.ualberta.cs.lonelytwitter.LonelyTwitterActivity;
import ca.ualberta.cs.lonelytwitter.NormalTweetModel;
import ca.ualberta.cs.lonelytwitter.R;

/*
 * generate this class with new.. JUnit Test Case
 * set superclass to ActivityInstrumentationTestCase2
 */
public class LonelyTwitterActivityUITest extends
		ActivityInstrumentationTestCase2<LonelyTwitterActivity> {

	Instrumentation instrumentation;
	Activity activity;
	EditText textInput;
	
	public LonelyTwitterActivityUITest() {
		super(LonelyTwitterActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		instrumentation = getInstrumentation();
		activity = getActivity();

		textInput = ((EditText) activity.findViewById(ca.ualberta.cs.lonelytwitter.R.id.body));
	}
	
	/*
	 * fills in the input text field and clicks the 'save'
	 * button for the activity under test
	 */
	private void makeTweet(String text) {
		assertNotNull(activity.findViewById(ca.ualberta.cs.lonelytwitter.R.id.save));
		textInput.setText(text);
		((Button) activity.findViewById(ca.ualberta.cs.lonelytwitter.R.id.save)).performClick();
	}
	
	public void testAddTweet() throws Throwable{
		Intent intent = new Intent();
		setActivityIntent(intent);
		
		LonelyTwitterActivity activity = (LonelyTwitterActivity)getActivity();
		int count = getActivity().getListView().getAdapter().getCount();
		runTestOnUiThread(new Runnable() {
			
			public void run() {
				makeTweet("some tweet");
			}
		});
		
		ListAdapter la = activity.getListView().getAdapter();
		
		assertTrue(la.getCount() > count);
		assertTrue(la.getItem(count).getClass().equals(NormalTweetModel.class));
		assertTrue(((NormalTweetModel)la.getItem(count)).getText().equals("some tweet"));
	}
	
	public void testInputClears() throws Throwable{
		Intent intent = new Intent();
		setActivityIntent(intent);
		
		runTestOnUiThread(new Runnable() {
			
			public void run() {
				makeTweet("some tweet");
			}
		});
		
		assertTrue(textInput.getText().toString().isEmpty());
	}
}
