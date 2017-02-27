package com.stratom.rosleak;

import org.ros.concurrent.CancellableLoop;
import org.ros.namespace.GraphName;
import org.ros.node.ConnectedNode;
import org.ros.node.Node;
import org.ros.node.NodeMain;
import org.ros.node.topic.Publisher;

import std_msgs.Header;

/**
 * Created by Dan.Ambrosio on 2/27/2017.
 */

public class Heartbeat implements NodeMain {

    private byte[] leakArray;
    public Heartbeat() {
        leakArray = new byte[10*1024*1024];
    }

    @Override
    public GraphName getDefaultNodeName() {
        return GraphName.of("heartbeat");
    }

    @Override
    public void onStart(ConnectedNode connectedNode) {

        final Publisher<Header> publisher = connectedNode.newPublisher("heartbeat", Header._TYPE);

        connectedNode.executeCancellableLoop(new CancellableLoop() {

            private int sequenceNumber;
            private std_msgs.Header msg;

            @Override
            protected void setup() {

                sequenceNumber = 0;
                msg = publisher.newMessage();
                msg.setFrameId("header");

                super.setup();
            }

            @Override
            protected void loop() throws InterruptedException {
                msg.setSeq(sequenceNumber);
                publisher.publish(msg);
                sequenceNumber++;
                Thread.sleep(1000);
            }
        });
    }

    @Override
    public void onShutdown(Node node) {

    }

    @Override
    public void onShutdownComplete(Node node) {

    }

    @Override
    public void onError(Node node, Throwable throwable) {

    }
}
