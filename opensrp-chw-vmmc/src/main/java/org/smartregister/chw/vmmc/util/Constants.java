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
        String VMMC_ENROLLMENT = "Vmmc Enrollment";
        String VMMC_SERVICES = "Vmmc Services";
        String VMMC_PROCEDURE = "Vmmc Procedure";
        String VMMC_DISCHARGE = "Vmmc Discharge";
        String VMMC_FOLLOW_UP_VISIT = "Vmmc Follow-up Visit";
        String VMMC_NOTIFIABLE_EVENTS = "VMMC Notifiable Adverse Events";
        String VOID_EVENT = "Void Event";

    }

    interface FORMS {
        String VMMC_REGISTRATION = "vmmc_enrollment";
        String VMMC_FOLLOW_UP_VISIT = "vmmc_followup_visit";
        String VMMC_NOTIFIABLE = "vmmc_notifiable_adverse_events";

    }

    interface VMMC_FOLLOWUP_FORMS {
        String MEDICAL_HISTORY = "vmmc_service_medical_history";
        String PHYSICAL_EXAMINATION = "vmmc_service_physical_examination";
        String HTS = "vmmc_service_hts";
        String CONSENT_FORM = "vmmc_procedure_consent_form";
        String MC_PROCEDURE = "vmmc_procedure_mc_procedure";
        String POST_OP = "vmmc_post_op";
        String DISCHARGE = "vmmc_discharge";
        String FIRST_VITAL_SIGN = "vmmc_post_op_first_vital";
        String SECOND_VITAL_SIGN = "vmmc_post_op_second_vital";
    }

    interface TABLES {
        String VMMC_ENROLLMENT = "ec_vmmc_enrollment";
        String VMMC_FOLLOW_UP = "ec_vmmc_follow_up_visit";
        String VMMC_NOTIFIABLE_EVENT = "ec_vmmc_notifiable_ae";
        String VMMC_PROCEDURE = "ec_vmmc_procedure";
        String VMMC_SERVICE = "ec_vmmc_services";
        String VMMC_DISCHARGE = "ec_vmmc_post_op_and_discharge";

    }

    interface ACTIVITY_PAYLOAD {
        String BASE_ENTITY_ID = "BASE_ENTITY_ID";
        String FAMILY_BASE_ENTITY_ID = "FAMILY_BASE_ENTITY_ID";
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
        String VMMC_ENROLLMENT = "vmmc_enrollment";
    }

    interface VMMC_MEMBER_OBJECT {
        String MEMBER_OBJECT = "memberObject";
    }

    interface PROFILE_TYPES {
        String VMMC_PROFILE = "vmmc_profile";
        String VMMC_PROCEDURE = "vmmc_procedure";

    }

}