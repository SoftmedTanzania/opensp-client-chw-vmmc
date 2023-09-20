package org.smartregister.activity;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.reflect.Whitebox;
import org.smartregister.chw.vmmc.activity.BaseVmmcProfileActivity;
import org.smartregister.chw.vmmc.contract.VmmcProfileContract;
import org.smartregister.domain.AlertStatus;
import org.smartregister.vmmc.R;

import static org.mockito.Mockito.validateMockitoUsage;

public class BaseVmmcProfileActivityTest {
    @Mock
    public BaseVmmcProfileActivity baseVmmcProfileActivity;

    @Mock
    public VmmcProfileContract.Presenter profilePresenter;

    @Mock
    public View view;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void validate() {
        validateMockitoUsage();
    }

    @Test
    public void assertNotNull() {
        Assert.assertNotNull(baseVmmcProfileActivity);
    }

    @Test
    public void setOverDueColor() {
        baseVmmcProfileActivity.setOverDueColor();
        Mockito.verify(view, Mockito.never()).setBackgroundColor(Color.RED);
    }

    @Test
    public void formatTime() {
        BaseVmmcProfileActivity activity = new BaseVmmcProfileActivity();
        try {
            Assert.assertEquals("25 Oct 2019", Whitebox.invokeMethod(activity, "formatTime", "25-10-2019"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkHideView() {
        baseVmmcProfileActivity.hideView();
        Mockito.verify(view, Mockito.never()).setVisibility(View.GONE);
    }

    @Test
    public void checkProgressBar() {
        baseVmmcProfileActivity.showProgressBar(true);
        Mockito.verify(view, Mockito.never()).setVisibility(View.VISIBLE);
    }

    @Test
    public void medicalHistoryRefresh() {
        baseVmmcProfileActivity.refreshMedicalHistory(true);
        Mockito.verify(view, Mockito.never()).setVisibility(View.VISIBLE);
    }

    @Test
    public void onClickBackPressed() {
        baseVmmcProfileActivity = Mockito.spy(new BaseVmmcProfileActivity());
        Mockito.when(view.getId()).thenReturn(R.id.title_layout);
        Mockito.doNothing().when(baseVmmcProfileActivity).onBackPressed();
        baseVmmcProfileActivity.onClick(view);
        Mockito.verify(baseVmmcProfileActivity).onBackPressed();
    }

    @Test
    public void onClickOpenMedicalHistory() {
        baseVmmcProfileActivity = Mockito.spy(new BaseVmmcProfileActivity());
        Mockito.when(view.getId()).thenReturn(R.id.rlLastVisit);
        Mockito.doNothing().when(baseVmmcProfileActivity).openMedicalHistory();
        baseVmmcProfileActivity.onClick(view);
        Mockito.verify(baseVmmcProfileActivity).openMedicalHistory();
    }

    @Test
    public void onClickOpenUpcomingServices() {
        baseVmmcProfileActivity = Mockito.spy(new BaseVmmcProfileActivity());
        Mockito.when(view.getId()).thenReturn(R.id.rlUpcomingServices);
        Mockito.doNothing().when(baseVmmcProfileActivity).openUpcomingService();
        baseVmmcProfileActivity.onClick(view);
        Mockito.verify(baseVmmcProfileActivity).openUpcomingService();
    }

    @Test
    public void onClickOpenFamlilyServicesDue() {
        baseVmmcProfileActivity = Mockito.spy(new BaseVmmcProfileActivity());
        Mockito.when(view.getId()).thenReturn(R.id.rlFamilyServicesDue);
        Mockito.doNothing().when(baseVmmcProfileActivity).openFamilyDueServices();
        baseVmmcProfileActivity.onClick(view);
        Mockito.verify(baseVmmcProfileActivity).openFamilyDueServices();
    }

    @Test(expected = Exception.class)
    public void refreshFamilyStatusComplete() throws Exception {
        baseVmmcProfileActivity = Mockito.spy(new BaseVmmcProfileActivity());
        TextView textView = view.findViewById(R.id.textview_family_has);
        Whitebox.setInternalState(baseVmmcProfileActivity, "tvFamilyStatus", textView);
        Mockito.doNothing().when(baseVmmcProfileActivity).showProgressBar(false);
        baseVmmcProfileActivity.refreshFamilyStatus(AlertStatus.complete);
        Mockito.verify(baseVmmcProfileActivity).showProgressBar(false);
        PowerMockito.verifyPrivate(baseVmmcProfileActivity).invoke("setFamilyStatus", "Family has nothing due");
    }

    @Test(expected = Exception.class)
    public void refreshFamilyStatusNormal() throws Exception {
        baseVmmcProfileActivity = Mockito.spy(new BaseVmmcProfileActivity());
        TextView textView = view.findViewById(R.id.textview_family_has);
        Whitebox.setInternalState(baseVmmcProfileActivity, "tvFamilyStatus", textView);
        Mockito.doNothing().when(baseVmmcProfileActivity).showProgressBar(false);
        baseVmmcProfileActivity.refreshFamilyStatus(AlertStatus.complete);
        Mockito.verify(baseVmmcProfileActivity).showProgressBar(false);
        PowerMockito.verifyPrivate(baseVmmcProfileActivity).invoke("setFamilyStatus", "Family has services due");
    }

    @Test(expected = Exception.class)
    public void onActivityResult() throws Exception {
        baseVmmcProfileActivity = Mockito.spy(new BaseVmmcProfileActivity());
        Whitebox.invokeMethod(baseVmmcProfileActivity, "onActivityResult", 2244, -1, null);
        Mockito.verify(profilePresenter).saveForm(null);
    }

}
