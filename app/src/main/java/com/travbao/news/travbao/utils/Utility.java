package com.travbao.news.travbao.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.hardware.display.DisplayManager;
import android.net.Uri;
import android.os.BatteryManager;
import android.os.Build;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.design.widget.TabLayout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


import static android.content.Context.POWER_SERVICE;

/**
 * Created by zhangfei on 8/30/16.
 */
public class Utility {


    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public static boolean isCharging(Context context) {
        Intent intent = context.registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        int plugged = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
        return plugged == BatteryManager.BATTERY_PLUGGED_AC || plugged == BatteryManager.BATTERY_PLUGGED_USB;
    }

    public static String formatTimeGap(long milliseconds) {
        long min = milliseconds / 1000 / 60;
        if (min <= 1) {
            return "1 min";
        }
        if (min < 60) {
            return min + " min";
        }
        long hour = min / 60;
        if (hour < 24) {
            return hour + " hour";
        }
        long day = hour / 24;
        return day + " day";
    }

    public static String formatDate(long paramLong) {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date(paramLong));
    }

    public static String formatDate2(long paramLong) {
        return new SimpleDateFormat("yyyyMMdd").format(new Date(paramLong));
    }

    public static String formatHoroscopeDate(String dateStr) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        try {
            Date date = format.parse(dateStr);
            DateFormat df = new SimpleDateFormat("MMMM dd , yyyy", Locale.ENGLISH);
            return df.format(date);
        } catch (Exception e) {
            return null;
        }
    }

    public static String formatHoroscopeDateMonth(String dateStr) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
        try {
            Date date = format.parse(dateStr);
            DateFormat df = new SimpleDateFormat("MMMM , yyyy", Locale.ENGLISH);
            return df.format(date);
        } catch (Exception e) {
            return null;
        }
    }

    public static String toUpperCaseFirstOne(String s) {
        if (Character.isUpperCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
    }

    public static String toLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }

    public static String formatSize(long paramLong) {
        StringBuffer str1 = new StringBuffer();
        if (paramLong >= 1073741824L) {
            double d1 = paramLong;
            double d2 = 1073741824L;
            String str2 = String.valueOf(formatDecimal(d1 / d2));
            return str1.append(str2).append("GB").toString();
        }
        if (paramLong >= 1048576L) {
            double d3 = paramLong;
            double d4 = 1048576L;
            String str3 = String.valueOf(formatDecimal(d3 / d4));
            return str1.append(str3).append("MB").toString();
        }
        if (paramLong >= 1048L) {
            double d5 = paramLong;
            double d6 = 1024L;
            String str4 = String.valueOf(formatDecimal(d5 / d6));
            return str1.append(str4).append("KB").toString();
        }
        return str1.append(String.valueOf(paramLong)).append("B").toString();
    }

    public static String[] formatSizeToArray(long paramLong) {
        String[] res = new String[2];
        if (paramLong >= 1073741824L) {
            double d1 = paramLong;
            double d2 = 1073741824L;
            String str2 = String.valueOf(formatDecimal(d1 / d2));
            res[0] = str2;
            res[1] = "GB";
        } else if (paramLong >= 1048576L) {
            double d3 = paramLong;
            double d4 = 1048576L;
            String str3 = String.valueOf(formatDecimal(d3 / d4));
            res[0] = str3;
            res[1] = "MB";
        } else if (paramLong >= 1048L) {
            double d5 = paramLong;
            double d6 = 1024L;
            String str4 = String.valueOf(formatDecimal(d5 / d6));
            res[0] = str4;
            res[1] = "KB";
        } else {
            res[0] = String.valueOf(paramLong);
            res[1] = "B";
        }
        return res;
    }

    public static String formatDecimal(double paramDouble) {
        try {
            BigDecimal bd = new BigDecimal(paramDouble).setScale(1, 5);
            if (bd == null) {
                return "unknown";
            }
            return bd.toString();
        } catch (Exception e) {
            return "unknown";
        }
    }

    public static void formatTextStyleWithSizeAndColor(Context context, TextView tv, float size, String text1, String text, int colorId) {
        if (tv == null || context == null) {
            return;
        }
        if (!TextUtils.isEmpty(text)) {
            SpannableString ss1 = new SpannableString(text);
            int index = text.indexOf(text1);
            if (index == -1) {
                return;
            }
            if (!TextUtils.isEmpty(ss1) && size > 0) {
                ss1.setSpan(new RelativeSizeSpan(size), index, index + text1.length(), 0);
            }
            SpannableStringBuilder builder = new SpannableStringBuilder(ss1);
            ForegroundColorSpan color1 = new ForegroundColorSpan(context.getResources().getColor(colorId));
            builder.setSpan(color1, index, index + text1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            tv.setText(builder);
        }
    }

    public static void formatTextStyleWithSizeColorAndStyle(Context context, TextView tv, float size, String text1, String text, int colorId, int style) {
        if (tv == null || context == null) {
            return;
        }
        if (!TextUtils.isEmpty(text)) {
            SpannableString ss1 = new SpannableString(text);
            int index = text.indexOf(text1);
            if (index == -1) {
                return;
            }
            if (!TextUtils.isEmpty(ss1) && size > 0) {
                ss1.setSpan(new RelativeSizeSpan(size), index, index + text1.length(), 0);
                ss1.setSpan(new StyleSpan(style), index, index + text1.length(), 0);

            }
            SpannableStringBuilder builder = new SpannableStringBuilder(ss1);
            ForegroundColorSpan color1 = new ForegroundColorSpan(context.getResources().getColor(colorId));
            builder.setSpan(color1, index, index + text1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            tv.setText(builder);
        }
    }


    public static Bitmap drawable2Bitmap(Drawable drawable) {
        try {
            BitmapDrawable bd = (BitmapDrawable) drawable;
            Bitmap bitmap = bd.getBitmap();
            return bitmap;
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean isGooglePlayExist(Context context) {
        PackageInfo packageInfo = null;
        try {
            packageInfo = context.getPackageManager().getPackageInfo("com.android.vending", 0);
        } catch (PackageManager.NameNotFoundException e) {
            packageInfo = null;
            e.printStackTrace();
        }
        return packageInfo != null;
    }

    public static boolean isFacebookExist(Context context) {
        PackageInfo packageInfo = null;
        try {
            packageInfo = context.getPackageManager().getPackageInfo("com.facebook.katana", 0);
        } catch (PackageManager.NameNotFoundException e) {
            packageInfo = null;
            e.printStackTrace();
        }
        return packageInfo != null;
    }




    public static String getAppVersionName(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static int getAppVersionCode(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static void installAPKByFile(Context context, final File apk) {
        try {
            Intent install_intent = new Intent(Intent.ACTION_VIEW);
            install_intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Uri uri = Uri.fromFile(apk);
            install_intent.setDataAndType(uri, "application/vnd.android.package-archive");
            context.startActivity(install_intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int[] getScreenSize(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(dm);

        int[] size = new int[2];
        size[0] = dm.widthPixels;
        size[1] = dm.heightPixels;

        return size;
    }

    public static void openURI(Context context, String uri) {
        try {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
            browserIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(browserIntent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void hideSoftKey(Activity activity) {
        if (activity == null) {
            return;
        }
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        try {
            imm.hideSoftInputFromWindow(activity.getWindow().getCurrentFocus().getWindowToken(), 0);
        } catch (NullPointerException e) {
        }
    }

    public static void showSoftKey(Activity activity, View view) {
        if (activity == null) {
            return;
        }
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        try {
            imm.showSoftInput(view, 0);
        } catch (NullPointerException e) {
        }
    }

    public static CharSequence getBlackText(String result) {
        if (TextUtils.isEmpty(result)) {
            return result;
        }
        SpannableString spannableString = new SpannableString(result);
        spannableString.setSpan(new ForegroundColorSpan(Color.BLACK), 0, spannableString.length(), 0);
        return spannableString;
    }


    public static boolean isSameDay(long timeStamp1, long timeStamp2) {
        try {
            SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
            return fmt.format(timeStamp1).equals(fmt.format(timeStamp2));
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isScrrenOn(Context context) {
        if (Build.VERSION.SDK_INT >= 20) {
            // If you use API20 or more:
            DisplayManager dm = (DisplayManager) context.getSystemService(Context.DISPLAY_SERVICE);
            for (Display display : dm.getDisplays()) {
                if (display.getState() != Display.STATE_OFF) {
                    return true;
                }
            }
            return false;
        } else {
            // If you use less than API20:
            PowerManager powerManager = (PowerManager) context.getSystemService(POWER_SERVICE);
            return powerManager.isScreenOn();
        }
    }

    public static float getBatteryLevel(Context context) {
        Intent batteryIntent = context.getApplicationContext().registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        int level = batteryIntent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = batteryIntent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

        // Error checking that probably isn't needed but I added just in case.
        if (level == -1 || scale == -1) {
            return 50.0f;
        }

        return ((float) level / (float) scale) * 100.0f;
    }

//    public static void setLanguageLocale(Context ctx, String language, String country) {
//        if (ctx == null || TextUtils.isEmpty(language))
//            return;
//        try {
//            SharedPreferences sp = ctx.getSharedPreferences(Utility.APP_NAME, 0);
//            sp.edit().putString(Constants.KEY_LANGUAGE_LOCALE, language)
//                    .putString(Constants.KEY_LANGUAGE_COUNTRY, country)
//                    .commit();
//        } catch (Exception e) {
//        }
//    }
//
//    public static Locale getLanguageLocale(Context ctx) {
//        if (ctx == null)
//            return null;
//        try {
//            SharedPreferences sp = ctx.getSharedPreferences(Utility.APP_NAME, 0);
//            String language = sp.getString(Constants.KEY_LANGUAGE_LOCALE, Constants.LANGUAGE_LOCALE_DEFAULT);
//            if (!TextUtils.isEmpty(language) && !language.equalsIgnoreCase(Constants.LANGUAGE_LOCALE_DEFAULT)) {
//                String country = sp.getString(Constants.KEY_LANGUAGE_COUNTRY, "");
//                return new Locale(language, country);
//            }
//        } catch (Exception e) {
//        }
//        return null;
//    }
//
//    public static Locale getSettingLocale(Context ctx) {
//        if (ctx == null)
//            return Locale.getDefault();
//        try {
//            Configuration conf = ctx.getResources().getConfiguration();
//            return conf.locale;
//        } catch (Exception e) {
//        }
//        return Locale.getDefault();
//    }
//
//    public static void updateLanguageLocale(Context context) {
//        if (context == null)
//            return;
//        try {
//            Locale selectedLocal = getLanguageLocale(context);
//            if (selectedLocal != null) {
//                Resources res = context.getResources();
//                DisplayMetrics dm = res.getDisplayMetrics();
//                Configuration conf = res.getConfiguration();
//                conf.locale = selectedLocal;
//                res.updateConfiguration(conf, dm);
//            }
//        } catch (Exception e) {
//        }
//    }



    public static void deleteNotification(Context ctx, int notifyId) {
        String ns = Context.NOTIFICATION_SERVICE;
        NotificationManager nMgr = (NotificationManager) ctx.getSystemService(ns);
        nMgr.cancel(notifyId);
    }






    public static boolean isAmazonInstalled(Context context) {
        PackageInfo packageInfo = null;

        try {
            packageInfo = context.getPackageManager().getPackageInfo("com.amazon.mShop.android.shopping", 0);
        } catch (PackageManager.NameNotFoundException var3) {
            packageInfo = null;
            var3.printStackTrace();
        }

        return packageInfo != null;
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static boolean isServiceRunning(Context mContext, String className) {
        boolean isRunning = false;
        ActivityManager activityManager = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
        if (activityManager == null) {
            return false;
        }
        List<ActivityManager.RunningServiceInfo> serviceList = activityManager.getRunningServices(Integer.MAX_VALUE);
        if (serviceList == null || (serviceList.size() < 1)) {
            return false;
        }
        for (int i = 0; i < serviceList.size(); i++) {
            if (serviceList.get(i).service.getClassName().equals(className) == true) {
                isRunning = true;
                break;
            }
        }
        return isRunning;
    }

    public static void hideInputMethodManager(Activity activity) {
        if (activity == null) {
            return;
        }
        View view = activity.getCurrentFocus();
        if (view == null) {
            return;
        }
        IBinder binder = view.getWindowToken();
        if (binder == null) {
            return;
        }
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(binder, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public static void setIndicator(TabLayout tabs, int leftDip, int rightDip) {
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        if (tabStrip != null) {
            tabStrip.setAccessible(true);
            LinearLayout llTab = null;
            try {
                llTab = (LinearLayout) tabStrip.get(tabs);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
            int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());

            if (llTab != null) {
                for (int i = 0; i < llTab.getChildCount(); i++) {
                    View child = llTab.getChildAt(i);
                    child.setPadding(0, 0, 0, 0);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
                    params.leftMargin = left;
                    params.rightMargin = right;
                    child.setLayoutParams(params);
                    child.invalidate();
                }
            }
        }
    }

}