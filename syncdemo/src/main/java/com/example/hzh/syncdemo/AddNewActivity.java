/*******************************************************************************
 * Copyright (c) 2012 Manning
 * See the file license.txt for copying permission.
 ******************************************************************************/
package com.example.hzh.syncdemo;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.hzh.syncdemo.dao.TodoDAO;
import com.example.hzh.syncdemo.model.Todo;

public class AddNewActivity extends Activity {
  private EditText mTitle;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.add_todo);
    mTitle = (EditText) findViewById(R.id.add_todo_edittext);
  }

  public void addNew(View v) {
    String title = mTitle.getText().toString().trim();
    if (title != null && title.length() != 0) {
      Todo todo = new Todo();
      todo.setTitle(title);

      TodoDAO.getInstance().addNewTodo(getContentResolver(), todo);

      finish();
    }
  }

}
