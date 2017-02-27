package com.stratom.rosleak;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.ros.android.RosActivity;
import org.ros.node.NodeMainExecutor;

public class Main extends RosActivity {

    public Main() {
        super("ROSLeak", "ROSLeak");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button myButton = (Button) findViewById(R.id.button);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main.this, Second.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void init(NodeMainExecutor nodeMainExecutor) {
        //Do Nothing Here
    }
}
