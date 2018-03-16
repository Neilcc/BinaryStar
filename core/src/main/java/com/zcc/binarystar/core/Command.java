package com.zcc.binarystar.core;

import com.zcc.binarystar.core.executor.BinaryTaskExecutor;

/**
 * Created by cc on 2018/3/16.
 */

public abstract class Command<T, SR, FR> {

    protected T input;
    protected ICommandResp<SR, FR> iCommandResp;

    public Command(T input) {
        this.input = input;
    }

    protected abstract void runCommand(T input);

    public ICommandResp<SR, FR> getResponseCallBack() {

        return iCommandResp;
    }

    public void execute(ICommandResp<SR, FR> resp) {
        this.iCommandResp = new CommandRespWrapper<>(resp);
        BinaryTaskExecutor.getInstance().executeOnSubThread(new Runnable() {
            @Override
            public void run() {
                runCommand(input);
            }
        });
    }

    public interface ICommandResp<SR, FR> {

        void onSuccess(SR successRet);

        void onFailed(FR failedRet);
    }

    public static class CommandRespWrapper<SR, FR> implements ICommandResp<SR, FR> {

        ICommandResp<SR, FR> realResp;

        public CommandRespWrapper(ICommandResp<SR, FR> realResp) {
            this.realResp = realResp;
        }

        @Override
        public void onSuccess(final SR successRet) {
            if (BinaryTaskExecutor.isMainThread()) {
                realResp.onSuccess(successRet);
                return;
            }
            BinaryTaskExecutor.getInstance().executeOnMainThread(new Runnable() {
                @Override
                public void run() {
                    realResp.onSuccess(successRet);
                }
            });
        }

        @Override
        public void onFailed(final FR failedRet) {
            if (BinaryTaskExecutor.isMainThread()) {
                realResp.onFailed(failedRet);
                return;
            }
            BinaryTaskExecutor.getInstance().executeOnMainThread(new Runnable() {
                @Override
                public void run() {
                    realResp.onFailed(failedRet);
                }
            });
        }
    }

}
