package co.martinbrown.example.simplegesture;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.widget.Toast;

public class SimpleGestureDetectorExampleActivity extends Activity {

    GestureDetector detector;

    class MyGestureDetector extends SimpleOnGestureListener {
        @Override
        public boolean onDoubleTap(MotionEvent e) {

            Toast.makeText(getApplicationContext(), "You double tapped", Toast.LENGTH_SHORT).show();

            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                float velocityY) {

            float abs = e1.getY() - e2.getY();
            if (Math.abs(e1.getY() - e2.getY()) > 250)
                return false;

            if (e1.getX() - e2.getX() > 120
                    && Math.abs(velocityX) > 200) {
                Toast.makeText(getApplicationContext(), "You swiped right to left, " + abs, Toast.LENGTH_SHORT).show();

            }

            else if (e2.getX() - e1.getX() > 120
                    && Math.abs(velocityX) > 200) {
                Toast.makeText(getApplicationContext(), "You swiped left to right, " + abs, Toast.LENGTH_SHORT).show();
            }

            return false;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        detector = new GestureDetector(this, new MyGestureDetector());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (detector.onTouchEvent(event))
            return true;
        else
            return false;
    }
}