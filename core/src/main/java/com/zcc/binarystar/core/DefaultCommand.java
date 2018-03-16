package com.zcc.binarystar.core;

/**
 * Created by cc on 2018/3/16.
 */

public abstract class DefaultCommand<T> extends Command<T> {

    public DefaultCommand(T input) {
        super(input);
    }

    @Override
    public void runCommand(T input) {

    }


}
