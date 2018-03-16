package demo.binarystar.zcc.com.binarystar.domain;

import com.zcc.binarystar.core.Command;

/**
 * Created by cc on 2018/3/16.
 */

public class TestUseCase extends Command<String, String, Integer> {

    public TestUseCase() {
    }

    @Override
    protected void runCommand(String input) {
        if (input == null) {
            getResponseCallBack().onFailed(-100);
        } else {
            getResponseCallBack().onSuccess(input);
        }
    }
}
