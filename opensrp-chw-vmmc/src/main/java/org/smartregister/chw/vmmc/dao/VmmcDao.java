package org.smartregister.chw.vmmc.dao;

import org.apache.commons.lang3.StringUtils;
import org.smartregister.chw.vmmc.domain.MemberObject;
import org.smartregister.dao.AbstractDao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class VmmcDao extends AbstractDao {

    public static Date getVmmcTestDate(String baseEntityID) {
        String sql = "select vmmc_test_date from ec_vmmc_confirmation where base_entity_id = '" + baseEntityID + "'";

        DataMap<Date> dataMap = cursor -> getCursorValueAsDate(cursor, "vmmc_test_date", getNativeFormsDateFormat());

        List<Date> res = readData(sql, dataMap);
        if (res == null || res.size() != 1)
            return null;

        return res.get(0);
    }

    public static String getGentialExamination(String baseEntityId) {
        String sql = "SELECT genital_examination FROM ec_vmmc_services p " +
                " WHERE p.entity_id = '" + baseEntityId + "' ORDER BY last_interacted_with DESC LIMIT 1";

        DataMap<String> dataMap = cursor -> getCursorValue(cursor, "genital_examination");

        List<String> res = readData(sql, dataMap);
        if (res != null && res.size() != 0 && res.get(0) != null) {
            return res.get(0);
        }
        return "";
    }

    public static String getAnyComplaints(String baseEntityId) {
        String sql = "SELECT any_complaints FROM ec_vmmc_services p " +
                " WHERE p.entity_id = '" + baseEntityId + "' ORDER BY last_interacted_with DESC LIMIT 1";

        DataMap<String> dataMap = cursor -> getCursorValue(cursor, "any_complaints");

        List<String> res = readData(sql, dataMap);
        if (res != null && res.size() != 0 && res.get(0) != null) {
            return res.get(0);
        }
        return "";
    }

    public static String getDiagnosed(String baseEntityId) {
        String sql = "SELECT client_diagnosed FROM ec_vmmc_services p " +
                " WHERE p.entity_id = '" + baseEntityId + "' ORDER BY last_interacted_with DESC LIMIT 1";

        DataMap<String> dataMap = cursor -> getCursorValue(cursor, "client_diagnosed");

        List<String> res = readData(sql, dataMap);
        if (res != null && res.size() != 0 && res.get(0) != null) {
            return res.get(0);
        }
        return "";
    }

    public static String getAnyComplicationsPreviousSurgicalProcedure(String baseEntityId) {
        String sql = "SELECT complications_previous_surgical FROM ec_vmmc_services p " +
                " WHERE p.entity_id = '" + baseEntityId + "' ORDER BY last_interacted_with DESC LIMIT 1";

        DataMap<String> dataMap = cursor -> getCursorValue(cursor, "complications_previous_surgical");

        List<String> res = readData(sql, dataMap);
        if (res != null && res.size() != 0 && res.get(0) != null) {
            return res.get(0);
        }
        return "";
    }

    public static String getSymptomsHematologicalDiseaseValue(String baseEntityId) {
        String sql = "SELECT hematological_disease FROM ec_vmmc_services p " +
                " WHERE p.entity_id = '" + baseEntityId + "' ORDER BY last_interacted_with DESC LIMIT 1";

        DataMap<String> dataMap = cursor -> getCursorValue(cursor, "hematological_disease");

        List<String> res = readData(sql, dataMap);
        if (res != null && res.size() != 0 && res.get(0) != null) {
            return res.get(0);
        }
        return "";
    }

    public static String getKnownAllergiesValue(String baseEntityId) {
        String sql = "SELECT known_allergies FROM ec_vmmc_services p " +
                " WHERE p.entity_id = '" + baseEntityId + "' ORDER BY last_interacted_with DESC LIMIT 1";

        DataMap<String> dataMap = cursor -> getCursorValue(cursor, "known_allergies");

        List<String> res = readData(sql, dataMap);
        if (res != null && res.size() != 0 && res.get(0) != null) {
            return res.get(0);
        }
        return "";
    }

    public static String getHivTestResult(String baseEntityId) {
        String sql = "SELECT hiv_result FROM ec_vmmc_services p " +
                " WHERE p.entity_id = '" + baseEntityId + "' ORDER BY last_interacted_with DESC LIMIT 1";

        DataMap<String> dataMap = cursor -> getCursorValue(cursor, "hiv_result");

        List<String> res = readData(sql, dataMap);
        if (res != null && res.size() != 0 && res.get(0) != null) {
            return res.get(0);
        }
        return "";
    }
    public static String getViralLoad(String baseEntityId) {
        String sql = "SELECT hiv_viral_load_text FROM ec_vmmc_services p " +
                " WHERE p.entity_id = '" + baseEntityId + "' ORDER BY last_interacted_with DESC LIMIT 1";

        DataMap<String> dataMap = cursor -> getCursorValue(cursor, "hiv_viral_load_text");

        List<String> res = readData(sql, dataMap);
        if (res != null && res.size() != 0 && res.get(0) != null) {
            return res.get(0);
        }
        return "";
    }

    public static String getTypeForBloodGlucoseTest(String baseEntityId) {
        String sql = "SELECT type_of_blood_for_glucose_test FROM ec_vmmc_services p " +
                " WHERE p.entity_id = '" + baseEntityId + "' ORDER BY last_interacted_with DESC LIMIT 1";

        DataMap<String> dataMap = cursor -> getCursorValue(cursor, "type_of_blood_for_glucose_test");

        List<String> res = readData(sql, dataMap);
        if (res != null && res.size() != 0 && res.get(0) != null) {
            return res.get(0);
        }
        return "";
    }

    public static String getBloodGlucoseTest(String baseEntityId) {
        String sql = "SELECT blood_for_glucose FROM ec_vmmc_services p " +
                " WHERE p.entity_id = '" + baseEntityId + "' ORDER BY last_interacted_with DESC LIMIT 1";

        DataMap<String> dataMap = cursor -> getCursorValue(cursor, "blood_for_glucose");

        List<String> res = readData(sql, dataMap);
        if (res != null && res.size() != 0 && res.get(0) != null) {
            return res.get(0);
        }
        return "";
    }

    public static Date getVmmcFollowUpVisitDate(String baseEntityID) {
        String sql = "SELECT eventDate FROM event where eventType ='Vmmc Follow-up Visit' AND baseEntityId ='" + baseEntityID + "'";

        DataMap<Date> dataMap = cursor -> getCursorValueAsDate(cursor, "eventDate", getNativeFormsDateFormat());

        List<Date> res = readData(sql, dataMap);
        if (res == null || res.size() != 1)
            return null;

        return res.get(0);
    }

    public static MemberObject getVmmcMember(String baseEntityID) {
        String sql = "select m.base_entity_id,\n" +
                "       m.unique_id,\n" +
                "       m.relational_id,\n" +
                "       m.dob,\n" +
                "       m.first_name,\n" +
                "       m.middle_name,\n" +
                "       m.last_name,\n" +
                "       m.gender,\n" +
                "       m.phone_number,\n" +
                "       m.other_phone_number,\n" +
                "       f.first_name     family_name,\n" +
                "       f.primary_caregiver,\n" +
                "       f.family_head,\n" +
                "       f.village_town,\n" +
                "       fh.first_name    family_head_first_name,\n" +
                "       fh.middle_name   family_head_middle_name,\n" +
                "       fh.last_name     family_head_last_name,\n" +
                "       fh.phone_number  family_head_phone_number,\n" +
                "       ancr.is_closed   anc_is_closed,\n" +
                "       pncr.is_closed   pnc_is_closed,\n" +
                "       pcg.first_name   pcg_first_name,\n" +
                "       pcg.last_name    pcg_last_name,\n" +
                "       pcg.middle_name  pcg_middle_name,\n" +
                "       pcg.phone_number pcg_phone_number,\n" +
                "       mr.*\n" +
                "from ec_family_member m\n" +
                "         inner join ec_family f on m.relational_id = f.base_entity_id\n" +
                "         inner join ec_vmmc_confirmation mr on mr.base_entity_id = m.base_entity_id\n" +
                "         left join ec_family_member fh on fh.base_entity_id = f.family_head\n" +
                "         left join ec_family_member pcg on pcg.base_entity_id = f.primary_caregiver\n" +
                "         left join ec_anc_register ancr on ancr.base_entity_id = m.base_entity_id\n" +
                "         left join ec_pregnancy_outcome pncr on pncr.base_entity_id = m.base_entity_id\n" +
                "where m.base_entity_id = '" + baseEntityID + "' ";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());

        DataMap<MemberObject> dataMap = cursor -> {
            MemberObject memberObject = new MemberObject();

            memberObject.setFirstName(getCursorValue(cursor, "first_name", ""));
            memberObject.setMiddleName(getCursorValue(cursor, "middle_name", ""));
            memberObject.setLastName(getCursorValue(cursor, "last_name", ""));
            memberObject.setAddress(getCursorValue(cursor, "village_town"));
            memberObject.setGender(getCursorValue(cursor, "gender"));
            memberObject.setUniqueId(getCursorValue(cursor, "unique_id", ""));
            memberObject.setDob(getCursorValue(cursor, "dob"));
            memberObject.setFamilyBaseEntityId(getCursorValue(cursor, "relational_id", ""));
            memberObject.setRelationalId(getCursorValue(cursor, "relational_id", ""));
            memberObject.setPrimaryCareGiver(getCursorValue(cursor, "primary_caregiver"));
            memberObject.setFamilyName(getCursorValue(cursor, "family_name", ""));
            memberObject.setPhoneNumber(getCursorValue(cursor, "phone_number", ""));
            memberObject.setVmmcTestDate(getCursorValueAsDate(cursor, "vmmc_test_date", df));
            memberObject.setBaseEntityId(getCursorValue(cursor, "base_entity_id", ""));
            memberObject.setFamilyHead(getCursorValue(cursor, "family_head", ""));
            memberObject.setFamilyHeadPhoneNumber(getCursorValue(cursor, "pcg_phone_number", ""));
            memberObject.setFamilyHeadPhoneNumber(getCursorValue(cursor, "family_head_phone_number", ""));
            memberObject.setAncMember(getCursorValue(cursor, "anc_is_closed", ""));
            memberObject.setPncMember(getCursorValue(cursor, "pnc_is_closed", ""));

            String familyHeadName = getCursorValue(cursor, "family_head_first_name", "") + " "
                    + getCursorValue(cursor, "family_head_middle_name", "");

            familyHeadName =
                    (familyHeadName.trim() + " " + getCursorValue(cursor, "family_head_last_name", "")).trim();
            memberObject.setFamilyHeadName(familyHeadName);

            String familyPcgName = getCursorValue(cursor, "pcg_first_name", "") + " "
                    + getCursorValue(cursor, "pcg_middle_name", "");

            familyPcgName =
                    (familyPcgName.trim() + " " + getCursorValue(cursor, "pcg_last_name", "")).trim();
            memberObject.setPrimaryCareGiverName(familyPcgName);

            return memberObject;
        };

        List<MemberObject> res = readData(sql, dataMap);
        if (res == null || res.size() != 1)
            return null;

        return res.get(0);
    }


    public static void closeVmmcMemberFromRegister(String baseEntityID) {
        String sql = "update ec_vmmc_confirmation set is_closed = 1 where base_entity_id = '" + baseEntityID + "'";
        updateDB(sql);
    }

    public static boolean isRegisteredForVmmc(String baseEntityID) {
//        String sql = "SELECT count(p.base_entity_id) count FROM ec_vmmc_confirmation p " +
//                "WHERE p.base_entity_id = '" + baseEntityID + "' AND p.is_closed = 0 AND p.vmmc  = 1 " +
//                "AND datetime('NOW') <= datetime(p.last_interacted_with/1000, 'unixepoch', 'localtime','+15 days')";

        String sql = "SELECT count(p.base_entity_id) count FROM ec_vmmc_confirmation p " +
                "WHERE p.base_entity_id = '" + baseEntityID + "' AND p.is_closed = 0 ";

        DataMap<Integer> dataMap = cursor -> getCursorIntValue(cursor, "count");

        List<Integer> res = readData(sql, dataMap);
        if (res == null || res.size() != 1)
            return false;

        return res.get(0) > 0;
    }

    public static Integer getVmmcFamilyMembersCount(String familyBaseEntityId) {
        String sql = "SELECT count(emc.base_entity_id) count FROM ec_vmmc_confirmation emc " +
                "INNER Join ec_family_member fm on fm.base_entity_id = emc.base_entity_id " +
                "WHERE fm.relational_id = '" + familyBaseEntityId + "' AND fm.is_closed = 0 " +
                "AND emc.is_closed = 0 AND emc.vmmc = 1";

        DataMap<Integer> dataMap = cursor -> getCursorIntValue(cursor, "count");

        List<Integer> res = readData(sql, dataMap);
        if (res == null || res.size() == 0)
            return 0;
        return res.get(0);
    }

    public static MemberObject getMember(String baseEntityID) {
        String sql = "select m.base_entity_id , m.unique_id , m.relational_id , m.dob , m.first_name , m.middle_name , m.last_name , m.gender , m.phone_number , m.other_phone_number , f.first_name family_name ,f.primary_caregiver , f.family_head , f.village_town ,fh.first_name family_head_first_name , fh.middle_name family_head_middle_name , fh.last_name family_head_last_name, fh.phone_number family_head_phone_number , ancr.is_closed anc_is_closed, pncr.is_closed pnc_is_closed, pcg.first_name pcg_first_name , pcg.last_name pcg_last_name , pcg.middle_name pcg_middle_name , pcg.phone_number  pcg_phone_number , mr.* from ec_family_member m inner join ec_family f on m.relational_id = f.base_entity_id inner join ec_vmmc_confirmation mr on mr.base_entity_id = m.base_entity_id left join ec_family_member fh on fh.base_entity_id = f.family_head left join ec_family_member pcg on pcg.base_entity_id = f.primary_caregiver left join ec_anc_register ancr on ancr.base_entity_id = m.base_entity_id left join ec_pregnancy_outcome pncr on pncr.base_entity_id = m.base_entity_id where m.base_entity_id ='" + baseEntityID + "' ";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());

        DataMap<MemberObject> dataMap = cursor -> {
            MemberObject memberObject = new MemberObject();

            memberObject.setFirstName(getCursorValue(cursor, "first_name", ""));
            memberObject.setMiddleName(getCursorValue(cursor, "middle_name", ""));
            memberObject.setLastName(getCursorValue(cursor, "last_name", ""));
            memberObject.setAddress(getCursorValue(cursor, "village_town"));
            memberObject.setGender(getCursorValue(cursor, "gender"));
            memberObject.setUniqueId(getCursorValue(cursor, "unique_id", ""));
            memberObject.setAge(getCursorValue(cursor, "dob"));
            memberObject.setFamilyBaseEntityId(getCursorValue(cursor, "relational_id", ""));
            memberObject.setRelationalId(getCursorValue(cursor, "relational_id", ""));
            memberObject.setPrimaryCareGiver(getCursorValue(cursor, "primary_caregiver"));
            memberObject.setFamilyName(getCursorValue(cursor, "family_name", ""));
            memberObject.setPhoneNumber(getCursorValue(cursor, "phone_number", ""));
            memberObject.setVmmcTestDate(getCursorValueAsDate(cursor, "vmmc_test_date", df));
            memberObject.setBaseEntityId(getCursorValue(cursor, "base_entity_id", ""));
            memberObject.setFamilyHead(getCursorValue(cursor, "family_head", ""));
            memberObject.setFamilyHeadPhoneNumber(getCursorValue(cursor, "pcg_phone_number", ""));
            memberObject.setFamilyHeadPhoneNumber(getCursorValue(cursor, "family_head_phone_number", ""));
            memberObject.setAncMember(getCursorValue(cursor, "anc_is_closed", ""));
            memberObject.setPncMember(getCursorValue(cursor, "pnc_is_closed", ""));

            String familyHeadName = getCursorValue(cursor, "family_head_first_name", "") + " "
                    + getCursorValue(cursor, "family_head_middle_name", "");

            familyHeadName =
                    (familyHeadName.trim() + " " + getCursorValue(cursor, "family_head_last_name", "")).trim();
            memberObject.setFamilyHeadName(familyHeadName);

            String familyPcgName = getCursorValue(cursor, "pcg_first_name", "") + " "
                    + getCursorValue(cursor, "pcg_middle_name", "");

            familyPcgName =
                    (familyPcgName.trim() + " " + getCursorValue(cursor, "pcg_last_name", "")).trim();
            memberObject.setPrimaryCareGiverName(familyPcgName);

            return memberObject;
        };

        List<MemberObject> res = readData(sql, dataMap);
        if (res == null || res.size() != 1)
            return null;

        return res.get(0);
    }

    public static boolean isPrEPInitiated(String baseEntityId) {
        String sql = "SELECT prep_status FROM ec_prep_register p " +
                " WHERE p.base_entity_id = '" + baseEntityId + "' AND p.is_closed = 0 ";

        DataMap<String> dataMap = cursor -> getCursorValue(cursor, "prep_status");

        List<String> res = readData(sql, dataMap);
        if (res != null && res.size() != 0 && res.get(0) != null) {
            return StringUtils.isNotBlank(res.get(0)) && !(res.get(0).equalsIgnoreCase("not_initiated") || res.get(0).equalsIgnoreCase("discontinued_quit"));
        }
        return false;
    }

    public static String getPrepInitiationDate(String baseEntityId) {
        String sql = "SELECT prep_initiation_date FROM ec_prep_register p " +
                " WHERE p.base_entity_id = '" + baseEntityId + "' AND p.is_closed = 0 ";

        DataMap<String> dataMap = cursor -> getCursorValue(cursor, "prep_initiation_date");

        List<String> res = readData(sql, dataMap);
        if (res != null && res.size() != 0 && res.get(0) != null) {
            return res.get(0);
        }
        return null;
    }
}
