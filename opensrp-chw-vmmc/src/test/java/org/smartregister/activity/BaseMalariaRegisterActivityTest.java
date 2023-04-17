package org.smartregister.activity;

import android.content.Intent;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.reflect.Whitebox;
import org.smartregister.chw.vmmc.activity.BaseVmmcRegisterActivity;

public class BaseVmmcRegisterActivityTest {
    @Mock
    public Intent data;
    @Mock
    private BaseVmmcRegisterActivity baseVmmcRegisterActivity = new BaseVmmcRegisterActivity();

    @Test
    public void assertNotNull() {
        Assert.assertNotNull(baseVmmcRegisterActivity);
    }

    @Test
    public void testFormConfig() {
        Assert.assertNull(baseVmmcRegisterActivity.getFormConfig());
    }

    @Test
    public void checkIdentifier() {
        Assert.assertNotNull(baseVmmcRegisterActivity.getViewIdentifiers());
    }

    @Test(expected = Exception.class)
    public void onActivityResult() throws Exception {
        Whitebox.invokeMethod(baseVmmcRegisterActivity, "onActivityResult", 2244, -1, data);
        Mockito.verify(baseVmmcRegisterActivity.presenter()).saveForm(null);
    }

}
