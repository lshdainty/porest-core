package com.lshdainty.porest.common.controller;

import com.lshdainty.porest.common.controller.dto.TypesDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Tag(name = "Types", description = "공통 타입 조회 API")
public interface TypesApi {

    @Operation(
            summary = "Enum 타입 값 조회",
            description = """
                    시스템에서 사용하는 Enum 타입의 값들을 조회합니다.

                    지원하는 타입:
                    - grant-method: 휴가 부여 방식
                    - repeat-unit: 반복 단위
                    - vacation-time: 휴가 시간 유형
                    - vacation-type: 휴가 유형
                    - effective-type: 유효기간 발효일 타입
                    - expiration-type: 유효기간 만료일 타입
                    - approval-status: 승인 상태
                    - grant-status: 부여 상태
                    - schedule-type: 일정 유형
                    - holiday-type: 휴일 유형
                    - origin-company-type: 출신 회사 유형
                    - system-type: 시스템 유형
                    """
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "타입 값 조회 성공",
                    content = @Content(schema = @Schema(implementation = TypesDto.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "존재하지 않는 타입 이름"
            )
    })
    @GetMapping("/api/v1/types/{enumName}")
    ApiResponse<List<TypesDto>> getEnumValues(
            @Parameter(
                    description = "조회할 Enum 타입 이름",
                    example = "vacation-type",
                    required = true
            )
            @PathVariable String enumName
    );
}
