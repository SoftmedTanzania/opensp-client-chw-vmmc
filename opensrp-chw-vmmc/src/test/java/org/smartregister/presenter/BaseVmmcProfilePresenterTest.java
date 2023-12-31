package org.smartregister.presenter;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.smartregister.chw.vmmc.contract.VmmcProfileContract;
import org.smartregister.chw.vmmc.domain.MemberObject;
import org.smartregister.chw.vmmc.presenter.BaseVmmcProfilePresenter;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class BaseVmmcProfilePresenterTest {

    @Mock
    private VmmcProfileContract.View view = Mockito.mock(VmmcProfileContract.View.class);

    @Mock
    private VmmcProfileContract.Interactor interactor = Mockito.mock(VmmcProfileContract.Interactor.class);

    @Mock
    private MemberObject memberObject = new MemberObject();

    private BaseVmmcProfilePresenter profilePresenter = new BaseVmmcProfilePresenter(view, interactor, memberObject);


    @Test
    public void fillProfileDataCallsSetProfileViewWithDataWhenPassedMemberObject() {
        profilePresenter.fillProfileData(memberObject);
        verify(view).setProfileViewWithData();
    }

    @Test
    public void fillProfileDataDoesntCallsSetProfileViewWithDataIfMemberObjectEmpty() {
        profilePresenter.fillProfileData(null);
        verify(view, never()).setProfileViewWithData();
    }

    @Test
    public void vmmcTestDatePeriodIsLessThanSeven() {
        profilePresenter.recordVmmcButton("");
        verify(view).hideView();
    }

    @Test
    public void vmmcTestDatePeriodGreaterThanTen() {
        profilePresenter.recordVmmcButton("OVERDUE");
        verify(view).setOverDueColor();
    }

    @Test
    public void vmmcTestDatePeriodIsMoreThanFourteen() {
        profilePresenter.recordVmmcButton("EXPIRED");
        verify(view).hideView();
    }

    @Test
    public void refreshProfileBottom() {
        profilePresenter.refreshProfileBottom();
        verify(interactor).refreshProfileInfo(memberObject, profilePresenter.getView());
    }

    @Test
    public void saveForm() {
        profilePresenter.saveForm(null);
        verify(interactor).saveRegistration(null, view);
    }
}
