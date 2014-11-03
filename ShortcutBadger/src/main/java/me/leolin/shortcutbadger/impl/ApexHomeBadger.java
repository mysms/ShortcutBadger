package me.leolin.shortcutbadger.impl;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import me.leolin.shortcutbadger.ShortcutBadgeException;
import me.leolin.shortcutbadger.ShortcutBadger;

/**
 * @author Gernot Pansy
 */
public class ApexHomeBadger extends ShortcutBadger {

    public static final String INTENT_UPDATE_COUNTER = "com.anddoes.launcher.COUNTER_CHANGED";
    public static final String PACKAGENAME = "package";
    public static final String COUNT = "count";
    public static final String CLASS = "class";

    public ApexHomeBadger(Context context) {
        super(context);
    }

    @Override
    protected void executeBadge(int badgeCount) throws ShortcutBadgeException {

        String packageName = getContextPackageName();

        PackageManager packageManager = mContext.getPackageManager();
        String className = packageManager.getLaunchIntentForPackage(packageName).getComponent().getClassName();

        Intent intent = new Intent(INTENT_UPDATE_COUNTER);
        intent.putExtra(PACKAGENAME, packageName);
        intent.putExtra(COUNT, badgeCount);
        intent.putExtra(CLASS, className);
        mContext.sendBroadcast(intent);
    }
}
