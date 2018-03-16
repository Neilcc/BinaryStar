package demo.binarystar.zcc.com.binarystar.presenter;

import com.zcc.binarystar.core.Command;

import demo.binarystar.zcc.com.binarystar.TestContractView;
import demo.binarystar.zcc.com.binarystar.domain.TestUseCase;

/**
 * Created by cc on 2018/3/16.
 */

public class TestPresenter {
    TestContractView testContractView;
    TestUseCase testUseCase;

    public TestPresenter(TestContractView testContractView) {
        this.testContractView = testContractView;
        testUseCase = new TestUseCase();
    }

    public void doTestTask(String input){
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
}
