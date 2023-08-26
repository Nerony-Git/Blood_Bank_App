package com.george.blood_bank_app.runners;

import com.george.blood_bank_app.modelImpl.AdminPageTest;
import com.george.blood_bank_app.modelImpl.DonorPageTest;
import com.george.blood_bank_app.modelImpl.HomepageTest;
import org.graphwalker.java.test.Executor;
import org.graphwalker.java.test.Result;
import org.graphwalker.java.test.TestExecutor;
import org.graphwalker.websocket.WebSocketServer;

import java.io.IOException;

public class WebSocketApplication {
    public static void main(String[] args) throws IOException {
        Executor executor = new TestExecutor(HomepageTest.class, DonorPageTest.class, AdminPageTest.class);

        WebSocketServer server = new WebSocketServer(8887, executor.getMachine());
        server.start();

        Result result = executor.execute(true);
        if (result.hasErrors()) {
            for (String error : result.getErrors()) {
                System.out.println(error);
            }
        }
        System.out.println("Done: [" + result.getResults().toString(2) + "]");
    }
}
