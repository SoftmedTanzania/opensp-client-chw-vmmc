package org.smartregister.chw.malaria.contract;

import android.content.Context;

import org.ei.drishti.dto.AlertStatus;
import org.jetbrains.annotations.Nullable;
import org.smartregister.chw.malaria.domain.MemberObject;

import java.util.Date;

public interface MalariaProfileContract {
    interface View extends InteractorCallBack {

        void setProfileViewWithData();

        void hideView();

        void setDueColor();

        void setOverDueColor();

        void openMedicalHistory();

        void openUpcomingService();

        void openFamilyDueServices();

        void showProgressBar(boolean status);
    }

    interface Presenter {

        void fillProfileData(@Nullable MemberObject memberObject);

        @Nullable
        View getView();

        void refreshProfileBottom();

        void recordMalariaFollowUp(Context context);

        void recordMalariaButton(int days);
    }

    interface Interactor {

        void refreshProfileInfo(MemberObject memberObject, InteractorCallBack callback);

    }

    interface InteractorCallBack {

        void refreshMedicalHistory(boolean hasHistory);

        void refreshUpComingServicesStatus(String service, AlertStatus status, Date date);

        void refreshFamilyStatus(AlertStatus status);
    }
}