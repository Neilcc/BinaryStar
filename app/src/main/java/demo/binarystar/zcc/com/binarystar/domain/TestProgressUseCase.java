package demo.binarystar.zcc.com.binarystar.domain;

import com.zcc.binarystar.core.ProgressCommand;

/**
 * Created by cc on 2018/3/19.
 */

public class TestProgressUseCase extends ProgressCommand<String, String, String, Integer> {

    @Override
    protected void runCommand(String input) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                for (int i = 0; i < 100; i++) {
                    try {
                        sleep(400);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        getResponseCallBack().onFailed("failed thread interrupted");
                        return;
                    }
                    getProgressCallback().onProgress(i);
                }
                getResponseCallBack().onSuccess("finished progress");
            }
        }.start();

    }

}
