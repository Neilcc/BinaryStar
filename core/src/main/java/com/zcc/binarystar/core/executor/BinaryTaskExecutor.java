/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zcc.binarystar.core.executor;

import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.concurrent.Executor;


public class BinaryTaskExecutor implements TaskExecutor {
    private static volatile BinaryTaskExecutor sInstance;
    @NonNull
    private static final Executor sMainThreadExecutor = new Executor() {
        @Override
        public void execute(@NonNull Runnable command) {
            getInstance().executeOnMainThread(command);
        }
    };
    @NonNull
    private static final Executor sSubThreadExecutor = new Executor() {
        @Override
        public void execute(@NonNull Runnable command) {
            getInstance().executeOnSubThread(command);
        }
    };
    @NonNull
    private TaskExecutor mDelegate;
    @NonNull
    private TaskExecutor mDefaultTaskExecutor;

    private BinaryTaskExecutor() {
        mDefaultTaskExecutor = new DefaultTaskExecutor();
        mDelegate = mDefaultTaskExecutor;
    }

    /**
     * Returns an instance of the task executor.
     *
     * @return The singleton BinaryTaskExecutor.
     */
    public static BinaryTaskExecutor getInstance() {
        if (sInstance != null) {
            return sInstance;
        }
        synchronized (BinaryTaskExecutor.class) {
            if (sInstance == null) {
                sInstance = new BinaryTaskExecutor();
            }
        }
        return sInstance;
    }

    public static boolean isMainThread() {
        return Looper.getMainLooper().getThread().getId() == Thread.currentThread().getId();
    }

    public void setDelegate(@Nullable TaskExecutor taskExecutor) {
        mDelegate = taskExecutor == null ? mDefaultTaskExecutor : taskExecutor;
    }

    public Executor getMainThreadExecutor() {
        return sMainThreadExecutor;
    }

    public Executor getSubThreadExecutor() {
        return sSubThreadExecutor;
    }

    @Override
    public void executeOnMainThread(Runnable command) {
        mDelegate.executeOnMainThread(command);
    }

    @Override
    public void executeOnSubThread(Runnable command) {
        mDelegate.executeOnSubThread(command);
    }
}
