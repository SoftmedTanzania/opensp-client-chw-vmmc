package org.smartregister.chw.vmmc.util;

public interface Constants {

    int REQUEST_CODE_GET_JSON = 2244;
    String ENCOUNTER_TYPE = "encounter_type";
    String STEP_ONE = "step1";
    String STEP_TWO = "step2";

    interface JSON_FORM_EXTRA {
        String JSON = "json";
        String ENCOUNTER_TYPE = "encounter_type";
    }

    interface EVENT_TYPE {
        String VMMC_CONFIRMATION = "Vmmc Confirmation";
        String VMMC_FOLLOW_UP_VISIT = "Vmmc Follow-up Visit";
    }

    interface FORMS {
        String VMMC_REGISTRATION = "vmmc_confirmation";
        String VMMC_FOLLOW_UP_VISIT = "vmmc_followup_visit";
    }

    interface TABLES {
        String VMMC_CONFIRMATION = "ec_vmmc_confirmation";
        String VMMC_FOLLOW_UP = "ec_vmmc_follow_up_visit";
    }

    interface ACTIVITY_PAYLOAD {
        String BASE_ENTITY_ID = "BASE_ENTITY_ID";
        String FAMILY_BASE_ENTITY_ID = "FAMILY_BASE_ENTITY_ID";
        String ACTION = "ACTION";
        String VMMC_FORM_NAME = "VMMC_FORM_NAME";

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

}