package org.smartregister.chw.vmmc.contract;

import org.jetbrains.annotations.Nullable;
import org.smartregister.chw.vmmc.domain.MemberObject;
import org.smartregister.domain.AlertStatus;

import java.util.Date;

public interface VmmcProfileContract {
    interface View extends InteractorCallBack {

        void setProfileViewWithData();

        void setOverDueColor();

        void openMedicalHistory();

        void openUpcomingService();

        void openFamilyDueServices();

        void showProgressBar(boolean status);

        void recordAnc(MemberObject memberObject);

        void recordPnc(MemberObject memberObject);

        void hideView();

        void openFollowupVisit();

    }

    interface Presenter {

        void fillProfileData(@Nullable MemberObject memberObject);

        void saveForm(String jsonString);

        @Nullable
        View getView();

        void refreshProfileBottom();

        void recordVmmcButton(String visitState);
    }

    interface Interactor {

        void refreshProfileInfo(MemberObject memberObject, InteractorCallBack callback);

        void saveRegistration(String jsonString, final VmmcProfileContract.InteractorCallBack callBack);
    }


    interface InteractorCallBack {

        void refreshMedicalHistory(boolean hasHistory);

        void refreshUpComingServicesStatus(String service, AlertStatus status, Date date);

        void refreshFamilyStatus(AlertStatus status);

        void startVmmcNotifiableForm(String baseEntityId);

        void startVmmcFollowUp(String baseEntityId);

    }
}