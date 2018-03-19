package demo.binarystar.zcc.com.binarystar;

import com.zcc.binarystar.core.IBaseContractView;

/**
 * Created by cc on 2018/3/16.
 */

public interface TestContractView extends IBaseContractView {

    public void operationOne(String param);

    public void operationTwo(Integer code);

    public void showProgress(Integer data);

}
