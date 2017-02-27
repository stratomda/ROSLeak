package com.stratom.rosleak;

import android.os.Bundle;
import android.util.Log;

import org.ros.address.InetAddressFactory;
import org.ros.android.RosActivity;
import org.ros.node.NodeConfiguration;
import org.ros.node.NodeMainExecutor;

public class Second extends RosActivity {

    private final String LOG_TAG = getClass().getSimpleName();

    private NodeMainExecutor mNodeMainExecutor;
    private NodeConfiguration mNodeConfiguration;

    private Heartbeat mHeartbeat;

    public Second() {
        super("ROSLeak", "ROSLeak");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Log.d(LOG_TAG, "Creating a new Heartbeat");
        mHeartbeat = new Heartbeat();

    }

    @Override
    protected void init(NodeMainExecutor nodeMainExecutor) {
        mNodeMainExecutor = nodeMainExecutor;
        mNodeConfiguration = NodeConfiguration.
                newPublic(InetAddressFactory.newNonLoopback().getHostAddress(),getMasterUri());

        if(mNodeMainExecutor != null)
            mNodeMainExecutor.execute(mHeartbeat, mNodeConfiguration);
    }

    @Override
    protected void onDestroy() {

        if(mNodeMainExecutor != null)
        {
            if(mHeartbeat != null)
            {
                Log.d(LOG_TAG, "Shutting down Heartbeat");
                mNodeMainExecutor.shutdownNodeMain(mHeartbeat);
            }

        }

        super.onDestroy();
    }
}
