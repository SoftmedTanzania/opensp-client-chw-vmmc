package org.smartregister.application;

import android.util.Log;

import org.smartregister.Context;
import org.smartregister.CoreLibrary;
import org.smartregister.commonregistry.CommonFtsObject;
import org.smartregister.configurableviews.ConfigurableViewsLibrary;
import org.smartregister.vmmc.BuildConfig;
import org.smartregister.chw.vmmc.VmmcLibrary;
import org.smartregister.repository.SampleRepository;
import org.smartregister.chw.vmmc.util.DBConstants;
import org.smartregister.utils.SampleConstants;
import org.smartregister.receiver.SyncStatusBroadcastReceiver;
import org.smartregister.repository.Repository;
import org.smartregister.repository.UniqueIdRepository;
import org.smartregister.view.activity.DrishtiApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SampleApplication extends DrishtiApplication {
    private static final String TAG = SampleApplication.class.getCanonicalName();

    private static CommonFtsObject commonFtsObject;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
        context = Context.getInstance();
        context.updateApplicationContext(getApplicationContext());
        context.updateCommonFtsObject(createCommonFtsObject());

        //Initialize Modules
        CoreLibrary.init(context);
        ConfigurableViewsLibrary.init(context);
        VmmcLibrary.init(context, getRepository(), BuildConfig.VERSION_CODE, BuildConfig.DATABASE_VERSION);

        SyncStatusBroadcastReceiver.init(this);

        //Auto login by default
        String password = "pwd";
        context.session().start(context.session().lengthInMilliseconds());
        context.configuration().getDrishtiApplication().setPassword(password);
        context.session().setPassword(password);

    }

    @Override
    public void logoutCurrentUser() {
    }

    public static synchronized SampleApplication getInstance() {
        return (SampleApplication) mInstance;
    }

    @Override
    public Repository getRepository() {
        try {
            if (repository == null) {
                repository = new SampleRepository(getInstance().getApplicationContext(), context);
            }
        } catch (UnsatisfiedLinkError e) {
            Log.e(TAG, e.getMessage(), e);
        }
        return repository;
    }


    public static CommonFtsObject createCommonFtsObject() {
        if (commonFtsObject == null) {
            commonFtsObject = new CommonFtsObject(getFtsTables());
            for (String ftsTable : commonFtsObject.getTables()) {
                commonFtsObject.updateSearchFields(ftsTable, getFtsSearchFields(ftsTable));
                commonFtsObject.updateSortFields(ftsTable, getFtsSortFields(ftsTable));
            }
        }
        return commonFtsObject;
    }

    private static String[] getFtsTables() {
        return new String[]{SampleConstants.TABLE_NAME.FAMILY, SampleConstants.TABLE_NAME.FAMILY_MEMBER};
    }

    private static String[] getFtsSearchFields(String tableName) {
        if (tableName.equals(SampleConstants.TABLE_NAME.FAMILY)) {
            return new String[]{DBConstants.KEY.BASE_ENTITY_ID, DBConstants.KEY.VILLAGE_TOWN, DBConstants.KEY.FIRST_NAME,
                    DBConstants.KEY.LAST_NAME, DBConstants.KEY.UNIQUE_ID};
        } else if (tableName.equals(SampleConstants.TABLE_NAME.FAMILY_MEMBER)) {
            return new String[]{DBConstants.KEY.BASE_ENTITY_ID, DBConstants.KEY.FIRST_NAME, DBConstants.KEY.MIDDLE_NAME,
                    DBConstants.KEY.LAST_NAME, DBConstants.KEY.UNIQUE_ID};
        }
        return null;
    }

    private static String[] getFtsSortFields(String tableName) {
        if (tableName.equals(SampleConstants.TABLE_NAME.FAMILY)) {
            return new String[]{DBConstants.KEY.LAST_INTERACTED_WITH, DBConstants.KEY.DATE_REMOVED};
        } else if (tableName.equals(SampleConstants.TABLE_NAME.FAMILY_MEMBER)) {
            return new String[]{DBConstants.KEY.DOB, DBConstants.KEY.DOD, DBConstants.KEY
                    .LAST_INTERACTED_WITH, DBConstants.KEY.DATE_REMOVED};
        }
        return null;
    }
}