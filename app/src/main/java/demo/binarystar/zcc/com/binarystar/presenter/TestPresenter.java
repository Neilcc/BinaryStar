package demo.binarystar.zcc.com.binarystar.presenter;

import com.zcc.binarystar.core.Command;
import com.zcc.binarystar.core.ProgressCommand;

import demo.binarystar.zcc.com.binarystar.TestContractView;
import demo.binarystar.zcc.com.binarystar.domain.TestProgressUseCase;
import demo.binarystar.zcc.com.binarystar.domain.TestUseCase;

/**
 * Created by cc on 2018/3/16.
 */

public class TestPresenter {
    TestContractView testContractView;
    TestUseCase testUseCase;
    TestProgressUseCase testProgressUseCase;

    public TestPresenter(TestContractView testContractView) {
        this.testContractView = testContractView;
        testUseCase = new TestUseCase();
        testProgressUseCase = new TestProgressUseCase();
    }

    public void doTestTask(String input) {
        testUseCase.execute(input, new Command.ICommandResp<String, Integer>() {
            @Override
            public void onSuccess(String successRet) {
                testContractView.operationOne(successRet);
            }

            @Override
            public void onFailed(Integer failedRet) {
                testContractView.operationTwo(failedRet);
            }
        });
    }

    public void doProgressTask(String input) {
        testProgressUseCase.execute(input, new Command.ICommandResp<String, String>() {
            @Override
            public void onSuccess(String successRet) {
                testContractView.operationOne(successRet);
            }

            @Override
            public void onFailed(String failedRet) {
                testContractView.operationOne(failedRet);
            }
        }, new ProgressCommand.IOnProgressCallBack<Integer>() {
            @Override
            public void onProgress(Integer data) {
                testContractView.showProgress(data);
            }
        });

    }
}
