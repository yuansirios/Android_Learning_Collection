package com.yuan.android_learning_collection.EditText;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.yuan.android_learning_collection.R;

public class EditTextActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edittext_activity_layout);


        final int line_unselect = R.drawable.edittext_underline_unselected;
        final int line_select = R.drawable.edittext_underline_selected;

        final EditText ed1 = (EditText) findViewById(R.id.editText1);
        final EditText ed2 = (EditText) findViewById(R.id.editText2);

        /**初始化EditText,默认都为未选中状态**/
        ed1.setBackgroundResource(line_unselect);
        ed2.setBackgroundResource(line_unselect);

        ed1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    System.out.println("EditText1获得焦点");
                    ed1.setBackgroundResource(line_select);

                } else {
                    System.out.println("EditText1失去焦点");
                    ed1.setBackgroundResource(line_unselect);
                }
            }
        });

        /**第二个EditText的焦点监听事件**/
        ed2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    System.out.println("EditText2获得焦点");
                    ed2.setBackgroundResource(line_select);

                } else {
                    System.out.println("EditText2失去焦点");
                    ed2.setBackgroundResource(line_unselect);
                }
            }
        });
    }

    //点击输入框意外的区域，隐藏键盘
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(ev);
        }
// 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }

    public  boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = { 0, 0 };
//获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
// 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

}
