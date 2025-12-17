package com.lshdainty.porest.common.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 메시지 키 중앙 관리
 * messages.properties의 모든 키를 enum으로 관리하여 타입 안전성 보장
 */
@Getter
@RequiredArgsConstructor
public enum MessageKey {

    // ========================================
    // NOT FOUND (조회 실패)
    // ========================================
    NOT_FOUND_USER("error.notfound.user"),
    NOT_FOUND_VACATION("error.notfound.vacation"),
    NOT_FOUND_VACATION_HISTORY("error.notfound.vacation.history"),
    NOT_FOUND_VACATION_USAGE("error.notfound.vacation.usage"),
    NOT_FOUND_VACATION_POLICY("error.notfound.vacation.policy"),
    NOT_FOUND_USER_VACATION_POLICY("error.notfound.user.vacation.policy"),
    NOT_FOUND_VACATION_GRANT("error.notfound.vacation.grant"),
    NOT_FOUND_VACATION_APPROVAL("error.notfound.vacation.approval"),
    NOT_FOUND_SCHEDULE("error.notfound.schedule"),
    NOT_FOUND_HOLIDAY("error.notfound.holiday"),
    NOT_FOUND_DUES("error.notfound.dues"),
    NOT_FOUND_COMPANY("error.notfound.company"),
    NOT_FOUND_DEPARTMENT("error.notfound.department"),
    NOT_FOUND_USER_DEPARTMENT("error.notfound.user.department"),
    NOT_FOUND_MIN("error.notfound.min"),
    NOT_FOUND_MAX("error.notfound.max"),
    NOT_FOUND_TYPE("error.notfound.type"),
    NOT_FOUND_WORK_HISTORY("error.notfound.work.history"),
    NOT_FOUND_WORK_CODE("error.notfound.work.code"),
    NOT_FOUND_WORK_CODE_PARENT("error.notfound.work.code.parent"),
    NOT_FOUND_ROLE("error.notfound.role"),
    NOT_FOUND_PERMISSION("error.notfound.permission"),
    NOT_FOUND_INVITATION("error.notfound.invitation"),

    // ========================================
    // VALIDATE (검증 오류)
    // ========================================
    VALIDATE_PARAMETER_NULL("error.validate.parameter.null"),
    VALIDATE_WORK_CODE_REQUIRED("error.work.code.required"),
    VALIDATE_YEAR_MONTH_REQUIRED("error.work.year.month.required"),
    VALIDATE_YEAR_REQUIRED("error.validate.year.required"),
    VALIDATE_DAY_OF_WEEK("error.validate.dayOfWeek"),
    VALIDATE_START_AFTER_END("error.validate.startIsAfterThanEnd"),
    VALIDATE_WORKTIME_START_END("error.validate.worktime.startEndTime"),
    VALIDATE_EXPIRY_BEFORE_NOW("error.validate.expiry.isBeforeThanNow"),
    VALIDATE_NOT_ENOUGH_REMAIN_TIME("error.validate.notEnoughRemainTime"),
    VALIDATE_DELETE_BEFORE_NOW("error.validate.delete.isBeforeThanNow"),
    VALIDATE_DUPLICATE_COMPANY("error.validate.duplicate.company"),
    VALIDATE_DUPLICATE_USER_ID("error.validate.duplicate.userId"),
    VALIDATE_DEPARTMENT_EXISTS("error.validate.notnull.department"),
    VALIDATE_HAS_CHILDREN_DEPARTMENT("error.validate.has.children.department"),
    VALIDATE_DIFFERENT_COMPANY("error.validate.different.company"),
    VALIDATE_SELF_PARENT("error.validate.self.parent"),
    VALIDATE_CIRCULAR_REFERENCE("error.validate.circular.reference"),
    VALIDATE_MAIN_DEPARTMENT_EXISTS("error.validate.main.department.already.exists"),
    VALIDATE_NOT_PENDING_USER("error.validate.not.pending.user"),
    VALIDATE_ALREADY_DELETED_USER_VACATION_POLICY("error.validate.already.deleted.user.vacation.policy"),
    VALIDATE_ALREADY_DELETED_VACATION_POLICY("error.validate.already.deleted.vacation.policy"),
    VALIDATE_ALREADY_DELETED_VACATION_USAGE("error.validate.already.deleted.vacation.usage"),
    VALIDATE_CANNOT_DELETE_VACATION_POLICY("error.validate.cannot.delete.vacation.policy"),
    VALIDATE_EXPIRED_INVITATION("error.validate.expired.invitation"),
    VALIDATE_DUPLICATE_WORK_CODE("error.duplicate.workcode"),
    VALIDATE_INVALID_WORK_CODE_PARENT_SELF("error.invalid.workcode.parent.self"),

    // ========================================
    // VACATION (휴가 관련)
    // ========================================
    VACATION_NOT_MANUAL_GRANT_POLICY("error.validate.vacation.notManualGrantPolicy"),
    VACATION_GRANT_TIME_REQUIRED("error.validate.vacation.grantTimeRequired"),
    VACATION_GRANT_EXPIRY_DATE_REQUIRED("error.validate.vacation.grantExpiryDateRequired"),
    VACATION_GRANT_DATE_AFTER_EXPIRY("error.validate.vacation.grantDateAfterExpiryDate"),
    VACATION_ALREADY_DELETED("error.validate.vacation.alreadyDeleted"),
    VACATION_NOT_ACTIVE_GRANT("error.validate.vacation.notActiveGrant"),
    VACATION_PARTIALLY_USED_GRANT("error.validate.vacation.partiallyUsedGrant"),
    VACATION_START_TIME_REQUIRED("error.validate.vacation.startTimeRequired"),
    VACATION_END_TIME_REQUIRED("error.validate.vacation.endTimeRequired"),
    VACATION_END_TIME_AFTER_START("error.validate.vacation.endTimeAfterStartTime"),
    VACATION_GRANT_TIME_NOT_DEFINED("error.validate.vacation.grantTimeNotDefined"),
    VACATION_CANNOT_CALCULATE_GRANT_TIME("error.validate.vacation.cannotCalculateGrantTime"),
    VACATION_USER_GRANT_TIME_REQUIRED("error.validate.vacation.userGrantTimeRequired"),
    VACATION_USER_GRANT_TIME_POSITIVE("error.validate.vacation.userGrantTimePositive"),
    VACATION_NOT_AUTHORIZED_REQUESTER("error.validate.vacation.notAuthorizedRequester"),
    VACATION_NOT_ON_REQUEST_POLICY("error.validate.vacation.notOnRequestPolicy"),
    VACATION_POLICY_NOT_ASSIGNED("error.validate.vacation.policyNotAssigned"),
    VACATION_REQUEST_REASON_REQUIRED("error.validate.vacation.requestReasonRequired"),
    VACATION_APPROVER_REQUIRED("error.validate.vacation.approverRequired"),
    VACATION_APPROVER_COUNT_MISMATCH("error.validate.vacation.approverCountMismatch"),
    VACATION_DUPLICATE_APPROVER("error.validate.vacation.duplicateApprover"),
    VACATION_APPROVER_NOT_DEPARTMENT_HEAD("error.validate.vacation.approverNotDepartmentHead"),
    VACATION_NOT_AUTHORIZED_APPROVER("error.validate.vacation.notAuthorizedApprover"),
    VACATION_ALREADY_PROCESSED("error.validate.vacation.alreadyProcessed"),
    VACATION_PREVIOUS_APPROVAL_REQUIRED("error.validate.vacation.previousApprovalRequired"),
    VACATION_REJECTION_REASON_REQUIRED("error.validate.vacation.rejectionReasonRequired"),
    VACATION_CANNOT_CANCEL_AFTER_APPROVAL("error.validate.vacation.cannotCancelAfterApproval"),

    // ========================================
    // VACATION POLICY (휴가 정책)
    // ========================================
    VACATION_POLICY_NAME_REQUIRED("vacation.policy.name.required"),
    VACATION_POLICY_NAME_DUPLICATE("vacation.policy.name.duplicate"),
    VACATION_POLICY_GRANT_TIME_REQUIRED("vacation.policy.grantTime.required"),
    VACATION_POLICY_GRANT_TIME_POSITIVE("vacation.policy.grantTime.positive"),
    VACATION_POLICY_MANUAL_SCHEDULE_UNNECESSARY("vacation.policy.manual.schedule.unnecessary"),
    VACATION_POLICY_REPEAT_UNIT_REQUIRED("vacation.policy.repeatUnit.required"),
    VACATION_POLICY_REPEAT_INTERVAL_POSITIVE("vacation.policy.repeatInterval.positive"),
    VACATION_POLICY_REPEAT_INTERVAL_TOO_LARGE("vacation.policy.repeatInterval.tooLarge"),
    VACATION_POLICY_FIRST_GRANT_DATE_REQUIRED("vacation.policy.firstGrantDate.required"),
    VACATION_POLICY_MONTH_INVALID("vacation.policy.month.invalid"),
    VACATION_POLICY_DAY_INVALID("vacation.policy.day.invalid"),
    VACATION_POLICY_YEARLY_MONTH_REQUIRED("vacation.policy.yearly.monthRequired"),
    VACATION_POLICY_MONTHLY_MONTH_NOT_ALLOWED("vacation.policy.monthly.monthNotAllowed"),
    VACATION_POLICY_QUARTERLY_MONTH_NOT_ALLOWED("vacation.policy.quarterly.monthNotAllowed"),
    VACATION_POLICY_HALF_MONTH_NOT_ALLOWED("vacation.policy.half.monthNotAllowed"),
    VACATION_POLICY_DAILY_MONTH_DAY_NOT_ALLOWED("vacation.policy.daily.monthDayNotAllowed"),
    VACATION_POLICY_MAX_GRANT_COUNT_REQUIRED("vacation.policy.maxGrantCount.required"),
    VACATION_POLICY_MAX_GRANT_COUNT_POSITIVE("vacation.policy.maxGrantCount.positive"),
    VACATION_POLICY_MAX_GRANT_COUNT_UNNECESSARY("vacation.policy.maxGrantCount.unnecessary"),
    VACATION_POLICY_EFFECTIVE_TYPE_REQUIRED("vacation.policy.effectiveType.required"),
    VACATION_POLICY_EXPIRATION_TYPE_REQUIRED("vacation.policy.expirationType.required"),
    VACATION_POLICY_FLEXIBLE_GRANT_REQUIRED("vacation.policy.isFlexibleGrant.required"),
    VACATION_POLICY_GRANT_TIME_UNNECESSARY("vacation.policy.grantTime.unnecessary"),
    VACATION_POLICY_MINUTE_GRANT_YN_REQUIRED("vacation.policy.minuteGrantYn.required"),

    // ========================================
    // FILE (파일 관련)
    // ========================================
    FILE_REGISTER_HOLIDAY("error.file.registHoliday"),
    FILE_NOT_FOUND("error.file.notfound"),
    FILE_READ("error.file.read"),
    FILE_COPY("error.file.copy"),
    FILE_MOVE("error.file.move"),

    // ========================================
    // PERMISSION & ROLE (권한 및 역할)
    // ========================================
    ROLE_ALREADY_EXISTS("error.validate.role.already.exists"),
    PERMISSION_ALREADY_EXISTS("error.permission.already.exists"),

    // ========================================
    // COMMON (공통)
    // ========================================
    COMMON_SUCCESS("error.common.success"),
    COMMON_INVALID_INPUT("error.common.invalid.input"),
    COMMON_UNAUTHORIZED("error.common.unauthorized"),
    COMMON_FORBIDDEN("error.common.forbidden"),
    COMMON_NOT_FOUND("error.common.not.found"),
    COMMON_404("error.common.404"),
    COMMON_INTERNAL_SERVER("error.common.internal.server"),

    ;

    private final String key;
}
