package io.gitjournal.git_bindings;

import android.os.AsyncTask;

public class GitPushTask extends AsyncTask<String, Void, Void> {
    private final static String TAG = "GitPush";
    private AnyThreadResult result;

    public GitPushTask(AnyThreadResult _result) {
        result = _result;
    }

    protected Void doInBackground(String... params) {
        String cloneDirPath = params[0];
        final String publicKeyPath = params[1];
        final String privateKeyPath = params[2];

        Git git = new Git();
        git.setSshKeys(publicKeyPath, privateKeyPath, "");
        String errorStr = git.push(cloneDirPath);
        if (!errorStr.isEmpty()) {
            result.error("FAILED", errorStr, null);
            return null;
        }

        result.success(null);
        return null;
    }
}
