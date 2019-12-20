package io.gitjournal.git_bindings;

import android.os.AsyncTask;

public class GitPullTask extends AsyncTask<String, Void, Void> {
    private final static String TAG = "GitPull";
    private AnyThreadResult result;

    public GitPullTask(AnyThreadResult _result) {
        result = _result;
    }

    protected Void doInBackground(String... params) {
        String cloneDirPath = params[0];
        final String publicKeyPath = params[1];
        final String privateKeyPath = params[2];
        final String authorName = params[3];
        final String authorEmail = params[4];

        Git git = new Git();
        git.setSshKeys(publicKeyPath, privateKeyPath, "");
        String errorStr = git.pull(cloneDirPath, authorName, authorEmail);
        if (!errorStr.isEmpty()) {
            result.error("FAILED", errorStr, null);
            return null;
        }

        result.success(null);
        return null;
    }
}
