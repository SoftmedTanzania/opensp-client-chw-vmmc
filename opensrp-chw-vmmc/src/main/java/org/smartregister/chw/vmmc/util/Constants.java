package org.smartregister.chw.vmmc.util;

public interface Constants {

    int REQUEST_CODE_GET_JSON = 2244;
    String ENCOUNTER_TYPE = "encounter_type";
    String STEP_ONE = "step1";
    String STEP_TWO = "step2";
    String VMMC_VISIT_GROUP = "vmmc_visit_group";


    interface JSON_FORM_EXTRA {
        String JSON = "json";
        String ENCOUNTER_TYPE = "encounter_type";
    }

    interface EVENT_TYPE {
        String VMMC_CONFIRMATION = "Vmmc Confirmation";
        String VMMC_PROCEDURE = "Vmmc Procedure";
        String VMMC_DISCHARGE = "Vmmc Discharge";
        String VMMC_FOLLOW_UP_VISIT = "Vmmc Follow-up Visit";
        String VOID_EVENT = "Void Event";

    }

    interface FORMS {
        String VMMC_REGISTRATION = "vmmc_confirmation";
        String VMMC_FOLLOW_UP_VISIT = "vmmc_followup_visit";
        String VMMC_NOTIFIABLE = "vmmc_notifiable_adverse_events";

    }

    interface VMMC_FOLLOWUP_FORMS {
        String MEDICAL_HISTORY = "vmmc_medical_history";
        String PHYSICAL_EXAMINATION = "vmmc_physical_examination";
        String HTS = "vmmc_hts";
        String CONSENT_FORM = "vmmc_consent_form";
        String MC_PROCEDURE = "vmmc_mc_procedure";
        String POST_OP = "vmmc_post_op";
        String DISCHARGE = "vmmc_discharge";
        String FIRST_VITAL_SIGN = "vmmc_first_vital";
        String SECOND_VITAL_SIGN = "vmmc_second_vital";
    }

    interface TABLES {
        String VMMC_CONFIRMATION = "ec_vmmc_confirmation";
        String VMMC_FOLLOW_UP = "ec_vmmc_follow_up_visit";
        String VMMC_PROCEDURE = "ec_vmmc_procedure";

    }

    interface ACTIVITY_PAYLOAD {
        String BASE_ENTITY_ID = "BASE_ENTITY_ID";
        String FAMILY_BASE_ENTITY_ID = "FAMILY_BASE_ENTITY_ID";
        String ACTION = "ACTION";
        String VMMC_FORM_NAME = "VMMC_FORM_NAME";
        String MEMBER_PROFILE_OBJECT = "MemberObject";
        String EDIT_MODE = "editMode";
        String PROFILE_TYPE = "profile_type";


    }

    interface ACTIVITY_PAYLOAD_TYPE {
        String REGISTRATION = "REGISTRATION";
        String FOLLOW_UP_VISIT = "FOLLOW_UP_VISIT";
    }

    interface CONFIGURATION {
        String VMMC_CONFIRMATION = "vmmc_confirmation";
    }

    interface VMMC_MEMBER_OBJECT {
        String MEMBER_OBJECT = "memberObject";
    }

    interface PROFILE_TYPES {
        String VMMC_PROFILE = "vmmc_profile";
        String VMMC_PROCEDURE = "vmmc_procedure";

    }

}