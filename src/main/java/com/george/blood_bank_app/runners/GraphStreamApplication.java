package com.george.blood_bank_app.runners;

import com.george.blood_bank_app.modelImpl.AdminPageTest;
import com.george.blood_bank_app.modelImpl.DonorPageTest;
import com.george.blood_bank_app.modelImpl.HomepageTest;
import com.george.blood_bank_app.observers.GraphStreamObserver;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.ui.view.Viewer;
import org.graphstream.ui.view.ViewerPipe;
import org.graphwalker.core.event.Observer;
import org.graphwalker.java.test.Executor;
import org.graphwalker.java.test.Result;
import org.graphwalker.java.test.TestExecutor;

import java.io.IOException;

public class GraphStreamApplication {
    public static void main(String[] args) throws IOException {
        System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
        System.setProperty("org.graphstream.ui", "org.graphstream.ui.j2dviewer.util.Display");
        Graph graph = new MultiGraph("GraphWalker MeetUp");
        /*graph.display(true);*/
        Executor executor = new TestExecutor(HomepageTest.class, DonorPageTest.class, AdminPageTest.class);
        Observer observer = new GraphStreamObserver(graph);
        executor.getMachine().addObserver(observer);

        Result result = executor.execute(true);
        if (result.hasErrors()) {
            for (String error : result.getErrors()) {
                System.out.println(error);
            }
        }
        System.out.println("Done: [" + result.getResults().toString(2) + "]");

        // Create a viewer for the graph
        Viewer viewer = graph.display();
        ViewerPipe viewerPipe = viewer.newViewerPipe();
        viewerPipe.addAttributeSink(graph);

        // Make sure the program doesn't exit immediately
        viewerPipe.pump();
    }
}
