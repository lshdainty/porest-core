package com.lshdainty.porest.common.type;

/**
 * 화면에 표시되는 타입의 공통 인터페이스
 * 메시지 키와 정렬 순서를 제공합니다.
 */
public interface DisplayType {
    /**
     * 다국어 메시지 키를 반환합니다.
     * @return 메시지 키
     */
    String getMessageKey();

    /**
     * 정렬 순서를 반환합니다.
     * @return 정렬 순서
     */
    Long getOrderSeq();
}
