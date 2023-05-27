package org.smartregister.chw.vmmc.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.json.JSONArray;
import org.json.JSONObject;
import org.smartregister.chw.vmmc.VmmcLibrary;
import org.smartregister.chw.vmmc.contract.VmmcProfileContract;
import org.smartregister.chw.vmmc.custom_views.BaseVmmcFloatingMenu;
import org.smartregister.chw.vmmc.dao.VmmcDao;
import org.smartregister.chw.vmmc.domain.MemberObject;
import org.smartregister.chw.vmmc.domain.Visit;
import org.smartregister.chw.vmmc.interactor.BaseVmmcProfileInteractor;
import org.smartregister.chw.vmmc.presenter.BaseVmmcProfilePresenter;
import org.smartregister.chw.vmmc.util.Constants;
import org.smartregister.chw.vmmc.util.VmmcJsonFormUtils;
import org.smartregister.chw.vmmc.util.VmmcUtil;
import org.smartregister.chw.vmmc.util.VmmcVisitsUtil;
import org.smartregister.domain.AlertStatus;
import org.smartregister.helper.ImageRenderHelper;
import org.smartregister.vmmc.R;
import org.smartregister.view.activity.BaseProfileActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import timber.log.Timber;


public class BaseVmmcProfileActivity extends BaseProfileActivity implements VmmcProfileContract.View, VmmcProfileContract.InteractorCallBack {
    protected MemberObject memberObject;
    protected VmmcProfileContract.Presenter profilePresenter;
    protected CircleImageView imageView;
    protected TextView textViewName;
    protected TextView textViewGender;
    protected TextView textViewLocation;
    protected TextView textViewUniqueID;
    protected TextView textViewRecordVmmc;
    protected TextView textViewProcedureVmmc;
    protected TextView textViewNotifiableVmmc;
    protected TextView textViewDischargeVmmc;
    protected TextView textViewFollowUpVmmc;
    protected TextView textViewRecordAnc;
    protected TextView textview_positive_date;
    protected View view_last_visit_row;
    protected View view_most_due_overdue_row;
    protected View view_family_row;
    protected View view_positive_date_row;
    protected RelativeLayout rlLastVisit;
    protected RelativeLayout rlUpcomingServices;
    protected RelativeLayout rlFamilyServicesDue;
    protected RelativeLayout visitStatus;
    protected ImageView imageViewCross;
    protected TextView textViewUndo;
    protected RelativeLayout rlVmmcPositiveDate;
    private TextView tvUpComingServices;
    private TextView tvFamilyStatus;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM", Locale.getDefault());
    protected TextView textViewVisitDone;
    protected RelativeLayout visitDone;
    protected LinearLayout recordVisits;
    protected TextView textViewVisitDoneEdit;
    protected TextView textViewRecordAncNotDone;

    protected String profileType;

    private ProgressBar progressBar;
    protected BaseVmmcFloatingMenu baseVmmcFloatingMenu;

    public static void startProfileActivity(Activity activity, String baseEntityId) {
        Intent intent = new Intent(activity, BaseVmmcProfileActivity.class);
        intent.putExtra(Constants.ACTIVITY_PAYLOAD.BASE_ENTITY_ID, baseEntityId);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreation() {
        setContentView(R.layout.activity_vmmc_profile);
        Toolbar toolbar = findViewById(R.id.collapsing_toolbar);
        setSupportActionBar(toolbar);
        String baseEntityId = getIntent().getStringExtra(Constants.ACTIVITY_PAYLOAD.BASE_ENTITY_ID);
        profileType = getIntent().getStringExtra(Constants.ACTIVITY_PAYLOAD.PROFILE_TYPE);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            Drawable upArrow = getResources().getDrawable(R.drawable.ic_arrow_back_white_24dp);
            upArrow.setColorFilter(getResources().getColor(R.color.text_blue), PorterDuff.Mode.SRC_ATOP);
            actionBar.setHomeAsUpIndicator(upArrow);
        }

        toolbar.setNavigationOnClickListener(v -> BaseVmmcProfileActivity.this.finish());
        appBarLayout = this.findViewById(R.id.collapsing_toolbar_appbarlayout);
        if (Build.VERSION.SDK_INT >= 21) {
            appBarLayout.setOutlineProvider(null);
        }

        textViewName = findViewById(R.id.textview_name);
        textViewGender = findViewById(R.id.textview_gender);
        textViewLocation = findViewById(R.id.textview_address);
        textViewUniqueID = findViewById(R.id.textview_id);
        view_last_visit_row = findViewById(R.id.view_last_visit_row);
        view_most_due_overdue_row = findViewById(R.id.view_most_due_overdue_row);
        view_family_row = findViewById(R.id.view_family_row);
        view_positive_date_row = findViewById(R.id.view_positive_date_row);
        imageViewCross = findViewById(R.id.tick_image);
        tvUpComingServices = findViewById(R.id.textview_name_due);
        tvFamilyStatus = findViewById(R.id.textview_family_has);
        textview_positive_date = findViewById(R.id.textview_positive_date);
        rlLastVisit = findViewById(R.id.rlLastVisit);
        rlUpcomingServices = findViewById(R.id.rlUpcomingServices);
        rlFamilyServicesDue = findViewById(R.id.rlFamilyServicesDue);
        rlVmmcPositiveDate = findViewById(R.id.rlVmmcPositiveDate);
        textViewVisitDone = findViewById(R.id.textview_visit_done);
        visitStatus = findViewById(R.id.record_visit_not_done_bar);
        visitDone = findViewById(R.id.visit_done_bar);
        recordVisits = findViewById(R.id.record_visits);
        progressBar = findViewById(R.id.progress_bar);
        textViewRecordAncNotDone = findViewById(R.id.textview_record_anc_not_done);
        textViewVisitDoneEdit = findViewById(R.id.textview_edit);
        textViewRecordVmmc = findViewById(R.id.textview_record_vmmc);
        textViewProcedureVmmc = findViewById(R.id.textview_procedure_vmmc);
        textViewNotifiableVmmc = findViewById(R.id.textview_notifiable_vmmc);
        textViewDischargeVmmc = findViewById(R.id.textview_discharge_vmmc);
        textViewFollowUpVmmc = findViewById(R.id.textview_followup_vmmc);
        textViewRecordAnc = findViewById(R.id.textview_record_anc);
        textViewUndo = findViewById(R.id.textview_undo);
        imageView = findViewById(R.id.imageview_profile);

        textViewRecordAncNotDone.setOnClickListener(this);
        textViewVisitDoneEdit.setOnClickListener(this);
        rlLastVisit.setOnClickListener(this);
        rlUpcomingServices.setOnClickListener(this);
        rlFamilyServicesDue.setOnClickListener(this);
        rlVmmcPositiveDate.setOnClickListener(this);
        textViewRecordVmmc.setOnClickListener(this);
        textViewProcedureVmmc.setOnClickListener(this);
        textViewNotifiableVmmc.setOnClickListener(this);
        textViewDischargeVmmc.setOnClickListener(this);
        textViewFollowUpVmmc.setOnClickListener(this);
        textViewRecordAnc.setOnClickListener(this);
        textViewUndo.setOnClickListener(this);

        imageRenderHelper = new ImageRenderHelper(this);
        memberObject = VmmcDao.getMember(baseEntityId);
        initializePresenter();
        profilePresenter.fillProfileData(memberObject);
        setupViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setupViews();
    }

    @Override
    protected void onResumption() {
        super.onResumption();
        setupViews();
    }

    @Override
    protected void setupViews() {
        initializeFloatingMenu();
        recordAnc(memberObject);
        recordPnc(memberObject);
        setupButtons();
    }

    protected void setupButtons() {

        Visit confirmationVisit = null;
        Visit procedureVisit = null;
        Visit dischargeVisit = null;
        Visit followUpVisit = null;
        Visit notifiableVisit = null;


        try{
            confirmationVisit = VmmcLibrary.getInstance().visitRepository().getLatestVisit(memberObject.getBaseEntityId(), Constants.EVENT_TYPE.VMMC_SERVICES);
            Log.d("vmmc-conf", confirmationVisit.getVisitType());

            VmmcVisitsUtil.manualProcessVisit(confirmationVisit);

            JSONObject jsonObject = new JSONObject(confirmationVisit.getJson());
            JSONArray obs = jsonObject.getJSONArray("obs");
            JSONObject checkObj = obs.getJSONObject(4);
            JSONArray value = checkObj.getJSONArray("values");

            if (confirmationVisit.getVisitType().equalsIgnoreCase(Constants.EVENT_TYPE.VMMC_SERVICES) && value.get(0).toString().equalsIgnoreCase("yes")){
                textViewRecordVmmc.setVisibility(View.GONE);
                textViewProcedureVmmc.setVisibility(View.VISIBLE);
                textViewDischargeVmmc.setVisibility(View.GONE);
                textViewNotifiableVmmc.setVisibility(View.GONE);
            }
            else {
                Snackbar.make(textViewDischargeVmmc,"Clear MC Procedure",Snackbar.LENGTH_LONG).show();
            }

        }catch (Exception e){
            Log.d("vmmc-error-conf", e.getMessage());
        }

        try {
            procedureVisit = VmmcLibrary.getInstance().visitRepository().getLatestVisit(memberObject.getBaseEntityId(), Constants.EVENT_TYPE.VMMC_PROCEDURE);
            Log.d("vmmc-proc", procedureVisit.getVisitType());

            VmmcVisitsUtil.manualProcessVisit(procedureVisit);

            JSONObject jsonObject = new JSONObject(procedureVisit.getJson());
            JSONArray obs = jsonObject.getJSONArray("obs");
            JSONObject checkObj = obs.getJSONObject(0);
            JSONArray value = checkObj.getJSONArray("values");

            JSONObject checkMcProcedure = obs.getJSONObject(3);
            JSONArray valueMcProcedure = checkMcProcedure.getJSONArray("values");

            if (procedureVisit.getVisitType().equalsIgnoreCase(Constants.EVENT_TYPE.VMMC_PROCEDURE) && value.get(0).toString().equalsIgnoreCase("yes")
                    && valueMcProcedure.get(0).toString().equalsIgnoreCase("yes")){
                textViewRecordVmmc.setVisibility(View.GONE);
                textViewProcedureVmmc.setVisibility(View.GONE);
                textViewDischargeVmmc.setVisibility(View.VISIBLE);
                textViewNotifiableVmmc.setVisibility(View.VISIBLE);
            }
        }

        catch (Exception e){
            Log.d("vmmc-error-proc", e.getMessage());
        }

        try {
            dischargeVisit = VmmcLibrary.getInstance().visitRepository().getLatestVisit(memberObject.getBaseEntityId(), Constants.EVENT_TYPE.VMMC_DISCHARGE);
            Log.d("vmmc-proc", dischargeVisit.getVisitType());

            VmmcVisitsUtil.manualProcessVisit(dischargeVisit);

            if (dischargeVisit.getVisitType().equalsIgnoreCase(Constants.EVENT_TYPE.VMMC_DISCHARGE)){
                textViewRecordVmmc.setVisibility(View.GONE);
                textViewProcedureVmmc.setVisibility(View.GONE);
                textViewDischargeVmmc.setVisibility(View.GONE);
                textViewFollowUpVmmc.setVisibility(View.VISIBLE);
                textViewNotifiableVmmc.setVisibility(View.VISIBLE);
            }
        }

        catch (Exception e){
            Log.d("vmmc-error-proc", e.getMessage());
        }

        try {
            followUpVisit = VmmcLibrary.getInstance().visitRepository().getLatestVisit(memberObject.getBaseEntityId(), Constants.EVENT_TYPE.VMMC_FOLLOW_UP_VISIT);
            VmmcVisitsUtil.manualProcessVisit(followUpVisit);
        }
        catch (Exception e){
            Log.d("vmmc-error-proc", e.getMessage());
        }

        try {
            notifiableVisit = VmmcLibrary.getInstance().visitRepository().getLatestVisit(memberObject.getBaseEntityId(), Constants.EVENT_TYPE.VMMC_NOTIFIABLE_EVENTS);
            VmmcVisitsUtil.manualProcessVisit(notifiableVisit);
        }
        catch (Exception e){
            Log.d("vmmc-error-proc", e.getMessage());
        }
    }


    @Override
    public void recordAnc(MemberObject memberObject) {
        //implement
    }

    @Override
    public void recordPnc(MemberObject memberObject) {
        //implement
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.title_layout) {
            onBackPressed();
        } else if (id == R.id.rlLastVisit) {
            this.openMedicalHistory();
        } else if (id == R.id.rlUpcomingServices) {
            this.openUpcomingService();
        } else if (id == R.id.rlFamilyServicesDue) {
            this.openFamilyDueServices();
        }
        else if (id == R.id.textview_record_vmmc) {
            this.openFollowupVisit();
        }
        else if (id == R.id.textview_procedure_vmmc) {
//            this.openFollowupVisit();
        }
        else if (id == R.id.textview_discharge_vmmc) {
//            this.openFollowupVisit();
        }
        else if (id == R.id.textview_notifiable_vmmc) {
            this.startVmmcNotifiableForm(memberObject.getBaseEntityId());
        }
        else if (id == R.id.textview_followup_vmmc) {
            this.startVmmcFollowUp(memberObject.getBaseEntityId());
        }
    }

    @Override
    public void startVmmcNotifiableForm(String baseEntityId) {
        //implement
    }

    @Override
    public void startVmmcFollowUp(String baseEntityId) {
        //implement
    }

    @Override
    protected void initializePresenter() {
        showProgressBar(true);
        profilePresenter = new BaseVmmcProfilePresenter(this, new BaseVmmcProfileInteractor(), memberObject);
        fetchProfileData();
        profilePresenter.refreshProfileBottom();
    }

    public void initializeFloatingMenu() {
        if (StringUtils.isNotBlank(memberObject.getPhoneNumber())) {
            baseVmmcFloatingMenu = new BaseVmmcFloatingMenu(this, memberObject);
            baseVmmcFloatingMenu.setGravity(Gravity.BOTTOM | Gravity.RIGHT);
            LinearLayout.LayoutParams linearLayoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);
            addContentView(baseVmmcFloatingMenu, linearLayoutParams);
        }
    }

    @Override
    public void hideView() {
        textViewRecordVmmc.setVisibility(View.GONE);
        textViewProcedureVmmc.setVisibility(View.GONE);
    }

    @Override
    public void openFollowupVisit() {
        //Implement in application
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void setProfileViewWithData() {
        int age = new Period(new DateTime(memberObject.getAge()), new DateTime()).getYears();
        textViewName.setText(String.format("%s %s %s, %d", memberObject.getFirstName(),
                memberObject.getMiddleName(), memberObject.getLastName(), age));
        textViewGender.setText(VmmcUtil.getGenderTranslated(this, memberObject.getGender()));
        textViewLocation.setText(memberObject.getAddress());
        textViewUniqueID.setText(memberObject.getUniqueId());

        if (StringUtils.isNotBlank(memberObject.getFamilyHead()) && memberObject.getFamilyHead().equals(memberObject.getBaseEntityId())) {
            findViewById(R.id.family_vmmc_head).setVisibility(View.GONE);
        }
        if (StringUtils.isNotBlank(memberObject.getPrimaryCareGiver()) && memberObject.getPrimaryCareGiver().equals(memberObject.getBaseEntityId())) {
            findViewById(R.id.primary_vmmc_caregiver).setVisibility(View.GONE);
        }
        if (memberObject.getVmmcTestDate() != null) {
            textview_positive_date.setText(getString(R.string.vmmc_positive) + " " + formatTime(memberObject.getVmmcTestDate()));
        }
    }

    @Override
    public void setOverDueColor() {
        textViewRecordVmmc.setBackground(getResources().getDrawable(R.drawable.record_btn_selector_overdue));
        textViewProcedureVmmc.setBackground(getResources().getDrawable(R.drawable.record_btn_selector_overdue));

    }

    @Override
    protected ViewPager setupViewPager(ViewPager viewPager) {
        return null;
    }

    @Override
    protected void fetchProfileData() {
        //fetch profile data
    }

    @Override
    public void showProgressBar(boolean status) {
        progressBar.setVisibility(status ? View.VISIBLE : View.GONE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void refreshMedicalHistory(boolean hasHistory) {
        showProgressBar(false);
        rlLastVisit.setVisibility(hasHistory ? View.VISIBLE : View.GONE);
    }

    @Override
    public void refreshUpComingServicesStatus(String service, AlertStatus status, Date date) {
        showProgressBar(false);
        if (status == AlertStatus.complete)
            return;
        view_most_due_overdue_row.setVisibility(View.GONE);
        rlUpcomingServices.setVisibility(View.GONE);

        if (status == AlertStatus.upcoming) {
            tvUpComingServices.setText(VmmcUtil.fromHtml(getString(R.string.vaccine_service_upcoming, service, dateFormat.format(date))));
        } else {
            tvUpComingServices.setText(VmmcUtil.fromHtml(getString(R.string.vaccine_service_due, service, dateFormat.format(date))));
        }
    }

    @Override
    public void refreshFamilyStatus(AlertStatus status) {
        showProgressBar(false);
        if (status == AlertStatus.complete) {
            setFamilyStatus(getString(R.string.family_has_nothing_due));
        } else if (status == AlertStatus.normal) {
            setFamilyStatus(getString(R.string.family_has_services_due));
        } else if (status == AlertStatus.urgent) {
            tvFamilyStatus.setText(VmmcUtil.fromHtml(getString(R.string.family_has_service_overdue)));
        }
    }

    private void setFamilyStatus(String familyStatus) {
        view_family_row.setVisibility(View.VISIBLE);
        rlFamilyServicesDue.setVisibility(View.GONE);
        tvFamilyStatus.setText(familyStatus);
    }

    @Override
    public void openMedicalHistory() {
        //implement
    }

    @Override
    public void openUpcomingService() {
        //implement
    }

    @Override
    public void openFamilyDueServices() {
        //implement
    }

    @Nullable
    private String formatTime(Date dateTime) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");
            return formatter.format(dateTime);
        } catch (Exception e) {
            Timber.d(e);
        }
        return null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.REQUEST_CODE_GET_JSON && resultCode == RESULT_OK) {
            profilePresenter.saveForm(data.getStringExtra(Constants.JSON_FORM_EXTRA.JSON));
            finish();
        }
    }
}
