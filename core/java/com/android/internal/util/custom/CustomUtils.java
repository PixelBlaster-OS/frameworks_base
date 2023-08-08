/*
 * Copyright (C) 2023 PixelBlaster-OS
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

package com.android.internal.util.custom;

import android.app.ActivityManager;
import android.app.IActivityManager;
import android.content.Context;
import android.os.AsyncTask;

import com.android.internal.R;

public class CustomUtils {

    public static void restartApp(String appName, Context context) {
        new RestartAppTask(appName, context).execute();
    }

    private static class RestartAppTask extends AsyncTask<Void, Void, Void> {
        private Context mContext;
        private String mApp;

        public RestartAppTask(String appName, Context context) {
            super();
            mContext = context;
            mApp = appName;
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                ActivityManager am =
                        (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
                IActivityManager ams = ActivityManager.getService();
                for (ActivityManager.RunningAppProcessInfo app: am.getRunningAppProcesses()) {
                    if (mApp.equals(app.processName)) {
                        ams.killApplicationProcess(app.processName, app.uid);
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

}