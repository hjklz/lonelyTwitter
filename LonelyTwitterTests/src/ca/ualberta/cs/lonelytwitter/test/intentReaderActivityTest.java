package ca.ualberta.cs.lonelytwitter.test;
import ca.ualberta.cs.lonelytwitter.IntentReaderActivity;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ViewAsserts;

public class intentReaderActivityTest extends	ActivityInstrumentationTestCase2<IntentReaderActivity> {

	public intentReaderActivityTest(){
		super(IntentReaderActivity.class);
	}
	
	public void testSendText(){
		Intent intent = new Intent();
		intent.putExtra(IntentReaderActivity.TEXT_KEY, "some string" );
		intent.putExtra(IntentReaderActivity.TRANSFORM_KEY, IntentReaderActivity.NORMAL);
		setActivityIntent(intent);
		
		IntentReaderActivity activity = (IntentReaderActivity)getActivity();
		assertEquals("some string", activity.getText());
	}
	
	public void testReverseText(){
		Intent intent = new Intent();
		intent.putExtra(IntentReaderActivity.TEXT_KEY, "some string" );
		intent.putExtra(IntentReaderActivity.TRANSFORM_KEY, IntentReaderActivity.REVERSE);
		setActivityIntent(intent);
		
		IntentReaderActivity activity = (IntentReaderActivity)getActivity();
		assertEquals("gnirts emos", activity.getText());
	}
	
	public void testDoubleText(){
		Intent intent = new Intent();
		intent.putExtra(IntentReaderActivity.TEXT_KEY, "some string" );
		intent.putExtra(IntentReaderActivity.TRANSFORM_KEY, IntentReaderActivity.DOUBLE);
		setActivityIntent(intent);
		
		IntentReaderActivity activity = (IntentReaderActivity)getActivity();
		assertEquals("some stringsome string", activity.getText());
	}
	
	public void testDisplayText(){
		Intent intent = new Intent();
		intent.putExtra(IntentReaderActivity.TEXT_KEY, "some string" );
		setActivityIntent(intent);
		
		IntentReaderActivity activity = (IntentReaderActivity)getActivity();
		assertEquals("some string", activity.getView().getText());
	}
	
	public void testDefaultMsg(){
		Intent intent = new Intent();
		setActivityIntent(intent);
		
		IntentReaderActivity activity = (IntentReaderActivity)getActivity();
		assertEquals(IntentReaderActivity.DEFAULT, activity.getView().getText());
	}
	
	public void testDisplayText2() throws Throwable{
		IntentReaderActivity activity = (IntentReaderActivity)getActivity();
		runTestOnUiThread(new Runnable() {
			
			public void run() {
				getActivity().getView().setText("some string");
			}
		});
		
		assertEquals("some string", activity.getView().getText());
	}
	
	public void testTextViewVisible(){
		IntentReaderActivity activity = (IntentReaderActivity)getActivity();
		ViewAsserts.assertOnScreen(activity.getWindow().getDecorView(), activity.getView());
	}
}
