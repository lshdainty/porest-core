package com.lshdainty.porest.common.util;

import com.lshdainty.porest.common.exception.ErrorCode;
import com.lshdainty.porest.common.exception.InvalidValueException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class PorestTime {

    private static final Logger log = LoggerFactory.getLogger(PorestTime.class);

    // 유틸리티 클래스는 인스턴스화 방지
    private PorestTime() {}

    /**
     * startDate와 endDate 순서 체크 함수<br>
     * startDate > endDate : true<br>
     * startDate <= endDate : false
     *
     * @param start 시작시간(LocalDateTime)
     * @param end 종료시간(LocalDateTime)
     * @return boolean
     */
    public static boolean isAfterThanEndDate(LocalDateTime start, LocalDateTime end) {
        return start.isAfter(end);
    }

    /**
     * startDate, endDate 사이의<br>
     * 모든 날짜들의 목록을 반환
     *
     * @param start 시작시간(LocalDateTime)
     * @param end 종료시간(LocalDateTime)
     * @param messageResolver MessageResolver
     * @return start ~ end 사이의 모든 날짜들
     */
    public static List<LocalDate> getBetweenDates(LocalDateTime start, LocalDateTime end) {
        if (start.isAfter(end)) {
            log.warn("Start date is after end date. start: {}, end: {}", start, end);
            throw new InvalidValueException(ErrorCode.INVALID_DATE_RANGE);
        }
        log.debug("Getting dates between {} and {}", start, end);
        return start.toLocalDate().datesUntil(end.toLocalDate().plusDays(1))
                .collect(Collectors.toList());
    }

    /**
     * 시작, 끝 기간에 해당하는 모든 날짜 중<br>
     * 사용자가 선택한 요일에 해당하는 모든 날짜를 반환하는 함수
     *
     * @param start 시작시간(LocalDateTime)
     * @param end 종료시간(LocalDateTime)
     * @param daysOfWeek int로 된 요일 리스트 (1 월요일 ~ 7 일요일)
     * @param messageResolver MessageResolver
     * @return 요일에 해당하는 모든 날짜들
     */
    public static List<LocalDate> getBetweenDatesByDayOfWeek(LocalDateTime start, LocalDateTime end, int[] daysOfWeek) {
        log.debug("Getting dates between {} and {} for days of week: {}", start, end, daysOfWeek);
        List<DayOfWeek> targetDays = new ArrayList<>();
        for (int day : daysOfWeek) {
            if (day < 1 || day > 7) {
                log.warn("Invalid day of week: {}", day);
                throw new InvalidValueException(ErrorCode.INVALID_PARAMETER);
            }
            targetDays.add(DayOfWeek.of(day));
        }

        List<LocalDate> dates = new ArrayList<>();
        LocalDate checkDay = start.toLocalDate();
        while (!checkDay.isAfter(end.toLocalDate())) {
            if (targetDays.contains(checkDay.getDayOfWeek())) {
                dates.add(checkDay);
            }
            checkDay = checkDay.plusDays(1);
        }

        return dates;
    }

    /**
     * 원본 날짜 집합에서 특정 날짜 집합을 더한 후<br>
     * List로 변환하여 반환
     *
     * @param sourceDates 원본 날짜 리스트들
     * @param targetDates 추가할 날짜 리스트들
     * @return source + target을 한 날짜 리스트 반환
     */
    public static List<LocalDate> addAllDates(List<LocalDate> sourceDates, List<LocalDate> targetDates) {
        Set<LocalDate> sourceSet = new HashSet<>(sourceDates);
        Set<LocalDate> targetSet = new HashSet<>(targetDates);
        sourceSet.addAll(targetSet);
        return sourceSet.stream()
                .map(LocalDate::from)
                .collect(Collectors.toList());
    }

    /**
     * 원본 날짜 집합에서 특정 날짜 집합을 뺀 후<br>
     * List로 변환하여 반환
     *
     * @param sourceDates 원본 날짜 리스트들
     * @param targetDates 제외할 날짜 리스트들
     * @return source - target을 한 날짜 리스트 반환
     */
    public static List<LocalDate> removeAllDates(List<LocalDate> sourceDates, List<LocalDate> targetDates) {
        Set<LocalDate> sourceSet = new HashSet<>(sourceDates);
        Set<LocalDate> targetSet = new HashSet<>(targetDates);
        sourceSet.removeAll(targetSet);
        return sourceSet.stream()
                .map(LocalDate::from)
                .collect(Collectors.toList());
    }

    /**
     * List의 시간 값 중에<br>
     * 가장 큰 시간 값을 반환하는 함수
     *
     * @param dateTimes 시간 리스트
     * @param messageResolver MessageResolver
     * @return LocalDateTime 타입의 가장 큰 시간
     */
    public static LocalDateTime findMaxDateTime(List<LocalDateTime> dateTimes) {
        if (dateTimes == null || dateTimes.isEmpty()) {
            log.warn("DateTime list is null or empty");
            throw new InvalidValueException(ErrorCode.INVALID_PARAMETER);
        }

        log.debug("Finding max datetime from {} entries", dateTimes.size());
        return dateTimes.stream()
                .max(LocalDateTime::compareTo)
                .orElseThrow(() -> {
                    log.error("Failed to find max datetime");
                    return new InvalidValueException(ErrorCode.INVALID_PARAMETER);
                });
    }

    /**
     * List의 시간 값 중에<br>
     * 가장 작은 시간 값을 반환하는 함수
     *
     * @param dateTimes 시간 리스트
     * @param messageResolver MessageResolver
     * @return LocalDateTime 타입의 가장 작은 시간
     */
    public static LocalDateTime findMinDateTime(List<LocalDateTime> dateTimes) {
        if (dateTimes == null || dateTimes.isEmpty()) {
            log.warn("DateTime list is null or empty");
            throw new InvalidValueException(ErrorCode.INVALID_PARAMETER);
        }

        log.debug("Finding min datetime from {} entries", dateTimes.size());
        return dateTimes.stream()
                .min(LocalDateTime::compareTo)
                .orElseThrow(() -> {
                    log.error("Failed to find min datetime");
                    return new InvalidValueException(ErrorCode.INVALID_PARAMETER);
                });
    }
}
