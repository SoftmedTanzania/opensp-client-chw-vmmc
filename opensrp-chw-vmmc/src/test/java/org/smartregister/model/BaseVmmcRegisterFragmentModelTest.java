package org.smartregister.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.smartregister.chw.vmmc.model.BaseVmmcRegisterFragmentModel;
import org.smartregister.configurableviews.model.RegisterConfiguration;
import org.smartregister.configurableviews.model.View;
import org.smartregister.configurableviews.model.ViewConfiguration;

import java.util.HashSet;
import java.util.Set;

public class BaseVmmcRegisterFragmentModelTest {

    @Mock
    private BaseVmmcRegisterFragmentModel baseVmmcRegisterFragmentModel;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDefaultRegisterConfiguration() {
        RegisterConfiguration configuration = new RegisterConfiguration();
        Mockito.when(baseVmmcRegisterFragmentModel.defaultRegisterConfiguration())
                .thenReturn(configuration);
        Assert.assertEquals(configuration, baseVmmcRegisterFragmentModel.defaultRegisterConfiguration());
    }

    @Test
    public void testGetViewConfiguration() {
        ViewConfiguration viewConfiguration = new ViewConfiguration();
        Mockito.when(baseVmmcRegisterFragmentModel.getViewConfiguration(Mockito.anyString()))
                .thenReturn(viewConfiguration);
        Assert.assertEquals(viewConfiguration, baseVmmcRegisterFragmentModel.getViewConfiguration(Mockito.anyString()));
    }

    @Test
    public void testGetRegisterActiveColumns() {
        Set<View> views = new HashSet<View>();
        Mockito.when(baseVmmcRegisterFragmentModel.getRegisterActiveColumns(Mockito.anyString()))
                .thenReturn(views);
        Assert.assertEquals(views, baseVmmcRegisterFragmentModel.getRegisterActiveColumns(Mockito.anyString()));
    }

    @Test
    public void testCountSelect() {
        String countString = "0";
        Mockito.when(baseVmmcRegisterFragmentModel.countSelect(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(countString);
        Assert.assertEquals(countString, baseVmmcRegisterFragmentModel.countSelect(Mockito.anyString(), Mockito.anyString()));
    }

    @Test
    public void testMainSelect() {
        String countString = "mainSelect";
        Mockito.when(baseVmmcRegisterFragmentModel.mainSelect(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(countString);
        Assert.assertEquals(countString, baseVmmcRegisterFragmentModel.mainSelect(Mockito.anyString(), Mockito.anyString()));
    }

}
